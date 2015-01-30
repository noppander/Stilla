package fi.tamk.tiko.eemeli.libgdx.stilla.screens;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import sun.security.ssl.Debug;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import fi.tamk.tiko.eemeli.libgdx.stilla.BGHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.ImageHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.Player;
import fi.tamk.tiko.eemeli.libgdx.stilla.StillaGame;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler.Musics;
import fi.tamk.tiko.eemeli.libgdx.stilla.game.HeightCalculator;
import fi.tamk.tiko.eemeli.libgdx.stilla.game.ScoreCalculator;
import fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects.*;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.LevelSelectScreen.CharacterClass;

/**
 * The main screen of the game where everyhting happens.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class GameScreen implements Screen {
	
	public static boolean screenInput = true;
	public static int starCount = 0;
	public static float spawnInterval;
	public static int lives;
	public static float gameobjectSpeed;
	
	private float spawnIntervalAdder = 0f;
	
	private ScoreCalculator scorecalculator = ScoreCalculator.getInstance();
	
	// Practically worthless
	//private float fallingSpeed = 1f;
	
	private String difficulty;
	
	private Player player;
	
	private BGHandler bghandler;
	
	private CharacterClass cc;
	
	//private ObjectHandler objecthandler;
	
	private ImageHandler imagehandler = new ImageHandler();
	
	private MusicHandler musichandler = MusicHandler.getInstance();
	
	// Default initializing.
	private StillaGame stillagame;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private GameObject gameobject;
	private ArrayList<GameObject> gameobjectList = new ArrayList<GameObject>();
	
	private int index = 0;
	
	private int number;
	
	float timeSinceSpawn = 0;
	
	// The declaration of the texturemaps.
	private TextureRegion normalcloud_texture;
	private TextureRegion watercloud_texture;
	private TextureRegion thundercloud_texture;
	private TextureRegion poisoncloud_texture;
	private TextureRegion icecloud_texture;
	private TextureRegion balloon_texture;
	private TextureRegion star_texture;
	
	// Temporary float for objectspeed.
	private float objTempSpeed;
	
	// Variables for initializing random stuff:
	// The string array we use to define our object later on.
	String ObjectTypes[];
	
	// The int array we use to define the random object.
	int ObjectWeights[];
	
	// The width of the window.
	private int windowWidth;
	
	
	// Vector3 used to receive input from screen.
		private Vector3 inputvec;
		
		// Floats where information about press is saved.
		private float inputX = 0;
		private float inputY = 0;
	
	
	// ShapeRenderer for debugging
	ShapeRenderer shaperenderer = new ShapeRenderer();
	
	private HeightCalculator heightcalculator;
	
	
	

	/**
	 * Constructor.
	 * 
	 * Gets the variables from StillaGame.
	 * 
	 * @param game
	 * @param batch
	 * @param camera
	 * @param cc 
	 */
	public GameScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera, String d, CharacterClass cc) {
		
		this.difficulty = d;
		
		// Get the batch.
		this.batch = batch;
		
		// Get the camera.
		this.camera = camera;
		
		this.cc = cc;
		
		// Get the game.
		stillagame = game;
		
		// Reset score and lives.
		scorecalculator.resetScore();
		resetLives();
		
		// New heightcalculator.
		heightcalculator = HeightCalculator.getInstance();
		
		// Creates new player giving it this class, so it can access the values.
		player = new Player(game, cc);
		
		// New BGHandler.
		bghandler = new BGHandler(game.worldWidth, game.worldHeight, d);
		
		// Initializes the arrays for spawning.
		initialize();
		
		// Loads the textures in the memory so we can use them in objects
		// without taking too much memory.
		LoadTheSprites ();
		
		//speed = 30 * (height/100)

		
					
	}

	/**
	 * Draws the background.
	 * 
	 */
	@Override
	public void render(float delta) {
		
		// Background color.
		Gdx.gl.glClearColor(0, 162, 232, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Updating the camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// Begins the batch
		batch.begin();
		
		bghandler.drawBackground(batch);
		
		// Really important stuff happens here
		
		// Reset the real GameObjectSpeed for next frame.
		gameobjectSpeed = 3 - (heightcalculator.getHeight() / 1000) / 2 ;
		
		// Reset the spawning speed for objects.
		spawnInterval = (0 + (heightcalculator.getHeight() / 3000) * spawnIntervalAdder);
		
		//Gdx.app.log("GameScreen", "Gameobjectspeed at start" + gameobjectSpeed);
		
		// Check player's the input.
		checkInput();
		
		// Draws and moves player sprite.
		player.DrawMySprite(batch, delta, stillagame.screenLeftSide, stillagame.screenRightSide);
		
		if (heightcalculator.height > 300) {
			// Spawns object randomly.
			SpawnObject();
		}
		
		// Runs through objects drawing and moving them.
		RunObjects();
		
		// Move the background according to speed of gameobjects.
		bghandler.moveBackground(gameobjectSpeed);
		
		// Updates score, height and so on.
		updateValues();
		
		// Checks if the game is over.
		checkValues();
		
		
		
		// Debug for drawing only the last game object
		//if (index > 0) gameobjectList.get(index - 1).DrawMySprite();
		
		// Ends the batch
		batch.end();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// EVERYTHING FROM HERE IS DEBUGGING.
		
		if (StillaGame.debug == true) {
			// Debugging with shapeRenderer.
			shaperenderer.begin(ShapeType.Line);
			shaperenderer.setColor(Color.RED);
			shaperenderer.setProjectionMatrix(camera.combined);
			
			for (int i = 0; i < gameobjectList.size(); i++) {
				
				Rectangle rectA = player.GetHitbox();
				Rectangle rectB = gameobjectList.get(i).GetHitbox();
				
				shaperenderer.rect(rectA.x, rectA.y, rectA.width, rectA.height);
				shaperenderer.rect(rectB.x, rectB.y, rectB.width, rectB.height);
		
			}
			
			shaperenderer.end();
			
			//Gdx.app.log("GameScreen", "Drawing the graphics in gamescreen class");
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
		windowWidth = width;
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		imagehandler.disposeAtlas();
		normalcloud_texture.getTexture().dispose();
		Gdx.app.log("GameScreen", "disposing the atlas");
		
		
	}
	
	public void updateValues(){
		
		ScoreCalculator.getInstance().AddScore(1);
		
		
		//GameObject.gameobjectSpeed += heightcalculator.getHeight();
		heightcalculator.changeHeight(-gameobjectSpeed / 2);
		
		
	}
	
	public void checkValues() {
		
		// If height goes under zero, game over.
		if (heightcalculator.getHeight() <= 0) {
			
			// Updates the totalscore.
			scorecalculator.updateTotalScore();
			
			ScreenHandler.getInstance().ShowGameOverWin();
		}
		
		// If lives run out, game over.
		if (lives <= 0) {
			
			ScreenHandler.getInstance().ShowGameOverLose();
			
		}
		
	}
	
	public void checkInput() {
		
		// If we are using onScreen controls
		if (screenInput == true){
			
			
			if(Gdx.input.isTouched()){
				
				inputvec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				
				camera.unproject(inputvec);
				
				inputX = inputvec.x;
				inputY = inputvec.y;
				

				Gdx.app.log("InputHandler", "Screen pressed. X is: " + inputX);
				Gdx.app.log("InputHandler", "Screen pressed. Y is: " + inputY);
				
				Gdx.app.log("InputHandler", "Window width is: " + windowWidth);
				
				if (inputX < 100) {
					Gdx.app.log("GameScreen", "Left side!!!!!!!!!!");
					player.changeXSpeed(-150f);
				} else if (inputX > 140){
					Gdx.app.log("GameScreen", "Right side!!!!!!!!!!");
					player.changeXSpeed(150f);
				} else {
					player.changeXSpeed(0f);
				}
				
					
			} else {
				player.changeXSpeed(0f);
			}
			
		}
		
		// If we are using accelometer.
		if (screenInput == false){
		
			if (Gdx.input.getAccelerometerX() < -0.5 || Gdx.input.getAccelerometerX() > 0.5){
				player.changeXSpeed((Gdx.input.getAccelerometerX() * 50) * -1);
			} else {
				player.changeXSpeed(0f);
			}
			
		}
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			
			musichandler.stopMusic();
			
			Gdx.app.log("Gamescreen", "Pressed back button");
			
			ScreenHandler.getInstance().ShowMenu();
			
		}
		
		
		
	}
	
	public void resetLives() {
		
		starCount = 0;
		
		// Depending on difficulty, formats the array.
		if (difficulty.equals("EASY")) {
			lives = 2;
			spawnIntervalAdder = 1.9f;
			
		} else if (difficulty.equals("MEDIUM")) {
			lives = 1;
			spawnIntervalAdder = 1.8f;
			
		} else if (difficulty.equals("HARD")) {
			ObjectWeights = new int[]{0, 35, 10, 10, 10, 5, 20, 10};
			lives = 0;
			spawnIntervalAdder = 1.7f;
		}
		
	}
	
	/**
	 * Spawns the objects on the screen
	 * 
	 * There is four possible lanes where it spawns. Then it creates object in the
	 * object list and gives it a texture
	 * 
	 */
	public void SpawnObject() {
		
		// Add time to counting variable
		timeSinceSpawn += Gdx.graphics.getDeltaTime();
		
		if (timeSinceSpawn > spawnInterval) {
			
		
			// Lane presets. Equally devided four lanes.
			int CLOUD_LANE_1 = 5;
			int CLOUD_LANE_2 = 65;
			int CLOUD_LANE_3 = 125;
			int CLOUD_LANE_4 = 185;
			
			// The position object is spawned
			int spawnPosX = 0;
			
			boolean assignOk = false;
			
			// Checks that there isn't two clouds spawning on the same lane.
			while (assignOk == false) {
				
				int random = MathUtils.random(0, 3);
				
				switch (random) {
				
					case 0:
						if (number != 0) {
							spawnPosX = CLOUD_LANE_1;
							number = 0;
							assignOk = true;
							break;
						}
						break;
					case 1:
						if (number != 1) {
							spawnPosX = CLOUD_LANE_2;
							number = 1;
							assignOk = true;
							break;
						}
						break;
					case 2:
						if (number != 2) {
							spawnPosX = CLOUD_LANE_3;
							number = 2;
							assignOk = true;
							break;
						}
						break;
					case 3:
						if (number != 3) {
							spawnPosX = CLOUD_LANE_4;
							number = 3;
							assignOk = true;
							break;
						}
						break;	
				}
				
				if (assignOk == false) {
					//Gdx.app.log("GameScreen", "Assign wasn't ok, lag expected. random: " + random);
				} else {
					//Gdx.app.log("GameScreen", "Assign was ok. random: " + random);
				}
			}
			
			// Reset the spawning time
			timeSinceSpawn = 0;
			

			
			// Adding the game object to the list.
			gameobjectList.add(index, RandomObject(spawnPosX, -20));
			
			RandomObject(10,20);

			index++;
			
			Gdx.app.log("GameScreen", "Objekteja kentällä: " + gameobjectList.size());
			
			//Gdx.app.log("GameScreen", "X sekuntia kulunut, pilvi spawnattu");
		}
	
	}
	
	/**
	 * Calculates a random object.
	 * @param spawnPosX
	 * @param spawnPosY
	 * @return
	 */
	public GameObject RandomObject(int spawnPosX, int spawnPosY) {
		
		// Randomizing what object we are spawning.
		int randomObject = MathUtils.random(0, 100);
		
		// Temporary gameobject
		GameObject tempGameObject = null;
		
		int tempWeight = 0;
		
		
		
		for (int i = 0; i < ObjectWeights.length - 1; i++) {
			//Gdx.app.log("GameScreen", "Object weights: " + ObjectWeights[i]);
			
			//Gdx.app.log("GameScreen", "First one: " + ObjectWeights[i]);
			//Gdx.app.log("GameScreen", "Second one: " + ObjectWeights[i+1]);
			
			tempWeight += ObjectWeights[i+1];
			
			//Gdx.app.log("GameScreen", "Randomi on: " + randomObject);
			//Gdx.app.log("GameScreen", "TempWeight: " + tempWeight);
			
			
			if (randomObject <= tempWeight){
				//Gdx.app.log("GameScreen", "Randomi on: " + randomObject + " Ja Spawnattava objekti: " + ObjectTypes[i]);
				
				if (ObjectTypes[i].equals("NORMAL_CLOUD")) {
					tempGameObject = new NormalCloud(spawnPosX, spawnPosY, normalcloud_texture, batch);
				} else if (ObjectTypes[i].equals("THUNDER_CLOUD")) {
					tempGameObject = new ThunderCloud(spawnPosX, spawnPosY, thundercloud_texture, batch);
				} else if (ObjectTypes[i].equals("WATER_CLOUD")) {
					tempGameObject = new WaterCloud(spawnPosX, spawnPosY, watercloud_texture, batch);
				} else if (ObjectTypes[i].equals("STAR")) {
					tempGameObject = new Star(spawnPosX, spawnPosY, star_texture, batch);
				} else if (ObjectTypes[i].equals("BALLOON")) {
					tempGameObject = new Balloon(spawnPosX, spawnPosY, balloon_texture, batch);
				} else if (ObjectTypes[i].equals("POISON_CLOUD")) {
					tempGameObject = new PoisonCloud(spawnPosX, spawnPosY, poisoncloud_texture, batch);
				} else if (ObjectTypes[i].equals("ICE_CLOUD")) {
					tempGameObject = new IceCloud(spawnPosX, spawnPosY, icecloud_texture, batch);
				} 
				
				break;
			}
			
		}
		
		Gdx.app.log("tempgo", "" + tempGameObject);
		return tempGameObject;
		
		
	}
	
	/**
	 * Initializes the weights for spawning.
	 */
	private void initialize() {
		
				// Defining the values in ObjectTypes array.
				ObjectTypes = new String[]{"NORMAL_CLOUD", "THUNDER_CLOUD", "WATER_CLOUD", 
											"STAR", "BALLOON", "POISON_CLOUD", "ICE_CLOUD"};
				// The array where weights are stored.
				ObjectWeights = new int[8];
				
				// Depending on difficulty, formats the array.
				if (difficulty.equals("EASY")) {
					ObjectWeights = new int[]{0, 40, 35, 10, 10, 5, 0, 0};
					
				} else if (difficulty.equals("MEDIUM")) {
					ObjectWeights = new int[]{0, 35, 10, 10, 10, 5, 30, 0};
					
				} else if (difficulty.equals("HARD")) {
					ObjectWeights = new int[]{0, 35, 10, 10, 10, 5, 20, 10};
				}
				
				// Set the height according to difficulty.
				heightcalculator.setHeight(difficulty);
				
	}
	
	/**
	 * Runs through the objects drawing, moving and checking hitboxes from them.
	 */
	public void RunObjects() {
		// This is the loop of the loops, where the magic happens.
				// Draws and checks the collisions of the objects in the list.
				if (gameobjectList != null) {
					
					for (int i = 0; i < gameobjectList.size(); i++) {
						
						float tempSpeed = gameobjectSpeed;
						
						
						
						if (gameobjectList.get(i).GetPosY() > 350) {
							gameobjectList.remove(gameobjectList.get(i));
							index--;
						} else {
							
							// Get player's and object's hitbox.
							Rectangle rectA = player.GetHitbox();
							Rectangle rectB = gameobjectList.get(i).GetHitbox();
							
							if (rectA.overlaps(rectB)) {
								
								
								// If game object is something else than white cloud, remove gameobject.
								if (gameobjectList.get(i).GetObjectID().equals("NORMAL_CLOUD")) {
									
									tempSpeed -= gameobjectSpeed / 2;
									
									heightcalculator.changeHeight(0.5f);
									
									
									
								} else if (gameobjectList.get(i).GetObjectID().equals("THUNDER_CLOUD")) {
									
									if (MusicHandler.musicOn) {
										gameobjectList.get(i).playSound();
									}
									
									gameobjectList.remove(gameobjectList.get(i));
									index--;
									lives--;
									
									tempSpeed = gameobjectSpeed;
									
								} else if (gameobjectList.get(i).GetObjectID().equals("WATER_CLOUD")) {
									
									if (MusicHandler.musicOn) {
										gameobjectList.get(i).playSound();
									}
									
									gameobjectList.remove(gameobjectList.get(i));
									index--;
									
									tempSpeed = gameobjectSpeed;
									
								} else if (gameobjectList.get(i).GetObjectID().equals("STAR")) {
									
									if (MusicHandler.musicOn) {
									gameobjectList.get(i).playSound();
									}
									
									gameobjectList.remove(gameobjectList.get(i));
									index--;
									
									starCount++;
									scorecalculator.AddScore(100);
									
									tempSpeed = gameobjectSpeed;
									
								} else if (gameobjectList.get(i).GetObjectID().equals("BALLOON")) {
									
									if (MusicHandler.musicOn) {
										gameobjectList.get(i).playSound();
									}
									
									gameobjectList.remove(gameobjectList.get(i));
									index--;
									
									heightcalculator.changeHeight(100);
									
									tempSpeed = gameobjectSpeed;
									
									
									
								} else if (gameobjectList.get(i).GetObjectID().equals("POISON_CLOUD")) {
									
									gameobjectList.remove(gameobjectList.get(i));
									index--;
									
									tempSpeed = gameobjectSpeed;
									
								} else if (gameobjectList.get(i).GetObjectID().equals("ICE_CLOUD")) {
									
									if (MusicHandler.musicOn) {
										gameobjectList.get(i).playSound();
									}
									
									gameobjectList.remove(gameobjectList.get(i));
									index--;
									lives--;
									
									tempSpeed = gameobjectSpeed;
									
								}									
							}	
						}
						
						if (gameobjectList.size() >= 1 && gameobjectList.size() > i) {
							
							
							
							// Draw current gameobject if there is one.
							gameobjectList.get(i).DrawMySprite(batch, tempSpeed);
							
						}
						
					}
					
				}
	}
	
	/**
	 * Loads the pictures to the memory
	 * 
	 * To save a memory we load images only once.
	 */
	public void LoadTheSprites () {
		
				
		normalcloud_texture = imagehandler.getImage("Clouds/normalcloud");
		watercloud_texture = imagehandler.getImage("Clouds/watercloud");
		thundercloud_texture = imagehandler.getImage("Clouds/thundercloud");
		poisoncloud_texture = imagehandler.getImage("Clouds/poisoncloud");
		icecloud_texture = imagehandler.getImage("Clouds/icecloud");
		balloon_texture = imagehandler.getImage("Objects/balloon");
		star_texture = imagehandler.getImage("Objects/star");
		
		
	}
	
	

}
