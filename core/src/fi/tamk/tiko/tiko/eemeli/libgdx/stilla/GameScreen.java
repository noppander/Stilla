package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import sun.security.ssl.Debug;

import Objects.NormalCloud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameScreen implements Screen {
	
	StillaGame stillagame;
	
	private Player player;
	
	private BGHandler bghandler;
	
	private ObjectHandler objecthandler;
	
	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	
	private Tools tools = new Tools();
	
	private GameObject gameobject;
	private ArrayList<GameObject> gameobjectList = new ArrayList<GameObject>();
	
	private int index = 0;
	
	private int number;
	
	float timeSinceSpawn = 0;
	
	// The declaration of the texturemaps.
	
	

	
	public GameScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {

		// Get the batch
		this.batch = batch;
		
		// Get the camera
		this.camera = camera;
		
		stillagame = game;
		
		// Creates new player giving it this class, so it can access the values
		player = new Player(game);
		
		// New BGHandler
		bghandler = new BGHandler(game.worldWidth, game.worldHeight);
		
		// New objecthandler
		//objecthandler = new ObjectHandler();
		
		// Initializing the sprites.
		//objecthandler.initializeSprites();
		
		//objecthandler.fillObjectArray();
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		// Background color.
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Updating the camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// Begins the batch
		batch.begin();
		
		bghandler.moveBackground(1f);
		bghandler.drawBackground(batch);
		
		player.DrawMySprite(batch, delta, stillagame.screenLeftSide, stillagame.screenRightSide);
		
		//objecthandler.RunTheGame();
		
		//objecthandler.drawSprites(batch);
		
		SpawnObject();
		
		
		if (gameobjectList != null) {
			for (int i = 0; i < gameobjectList.size(); i++) {
				gameobjectList.get(i).DrawMySprite();
			}
			
		}
		
		
		// Ends the batch
		batch.end();
		
		//Gdx.app.log("GameScreen", "Drawing the graphics in gamescreen class");
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		
	}
	
	public void SpawnObject() {
		
		// Add time to counting variable
		timeSinceSpawn += Gdx.graphics.getDeltaTime();
		
		if (timeSinceSpawn > 2f) {
			
		
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
				
				switch (tools.rand(0, 3)) {
				
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
			}
			
			// Reset the spawning time
			timeSinceSpawn = 0;
			
			// Adding the game object to the list.
			gameobjectList.add(index, gameobject = new NormalCloud(spawnPosX, 50, batch));

			index++;
			
			Debug.println("GameScreen", "Listin pituus" + gameobjectList.size());
			
			Gdx.app.log("GameScreen", "5 sekuntia kulunut, pilvi spawnattu");
		}
	
	}
	
	public void LoadTheSprites () {
		
	}

}
