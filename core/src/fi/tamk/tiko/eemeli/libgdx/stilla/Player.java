package fi.tamk.tiko.eemeli.libgdx.stilla;

/**
 * The player object which handles it's own movement and actions.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import fi.tamk.tiko.eemeli.libgdx.stilla.screens.GameScreen;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.LevelSelectScreen.CharacterClass;

	public class Player {

		// Declare the variables
		private Animation idleAnimation;
		private TextureRegion idleSheet;
		private TextureRegion[] idleFrames;
		private TextureRegion currentFrame;
		
		// The number of the frames used in walking animation
		public static final int IDLE_FRAMES = 4;
		
		// Declaration of the state time given value 0.
		float stateTime = 0;
		
		// Variable we use to handle the x position of the sprite
		private float posX = 0;
		private float posY = 0;
		
		// The horisontal speed of the object.
		private float xSpeed = 0;
		
		private float ySpeed = 0;
		private float jumpSpeed = 400;
		private float gravity = 10;
		private boolean grounded = false;
		
		private Sprite sprite;
		
		private float PLAYER_WIDTH = 24;
		private float PLAYER_HEIGHT = 48;

		float panForce = 0;
		
		Sound jump;
		
		private float rotation = 0;
		private float alpha = 1;
		
		private int characterBonus;
		private int characterLives;
		
		private Rectangle playerRect = new Rectangle ();
		
		private ImageHandler imagehandler = new ImageHandler();
		
		private CharacterClass cc;
		
		
		// GOING TO BE STATIC VARIABLE
		// Defines what character we are using.
		private int characterInUse = 4;
		
		// Declaration of the main game class
		StillaGame game;
		
		// Boolean which controls the movement of the sprite.
		private boolean movingPossible = true;
		
		// Declaring Inputhandler
		InputHandler inputhandler;
		
		
		/**
		 * Constructor.
		 * 
		 * @param game
		 * @param cc
		 */
		public Player(StillaGame game, CharacterClass cc) {
			
			this.game = game;
			
			this.cc = cc;

			
			// Starting position of Stilla defined.
			posX = game.worldWidth / 2;
			posY = game.worldHeight - PLAYER_HEIGHT * 1.5f;
			
			
			// Creating object from InputHandler.
			inputhandler = new InputHandler(game);
			
			// Initializes the sprites, values and the animations.
			initialize();
			
			
			//jump = Gdx.audio.newSound(Gdx.files.internal("hop.wav"));
			
			playerRect.setSize(PLAYER_WIDTH, PLAYER_HEIGHT / 2);
			
		}
		
		/**
		 * Draws and moves the sprite.
		 * 
		 * @param batch
		 * @param delta
		 * @param screenLeftSide
		 * @param screenRightSide
		 */
		public void DrawMySprite(SpriteBatch batch, float delta, float screenLeftSide, 
				float screenRightSide) {
			
			
			
			// Update the position of the hitbox
			playerRect.setPosition(posX, posY);
			
			// State time variable used to changing frames.
			stateTime += delta;
			
			currentFrame = idleAnimation.getKeyFrame(stateTime,true);
			
			//System.out.println("tmp: " + tmp);
			
			// Creates new sprite with current frame so it can be managed.
			sprite = new Sprite (currentFrame);
			
			//posY += ySpeed * delta;
			
			
			//Gdx.app.log("PosY", "" +  posY);
			
			// Sprite definitions
			sprite.setPosition(posX, posY);
			sprite.setAlpha(alpha);
			sprite.setOriginCenter();
			
			sprite.setSize(24, 48);
			// Set the position of the sprite.
			
			
			
			
			// Checking the inputs
			// xSpeed = inputhandler.checkPlayerInput(posX);
			
			inputhandler.checkDebugInput();
			
			
			
			
			
			//Gdx.app.log("Player", "Pystyy liikkua ja xSpeed on: " + xSpeed);
			//Gdx.app.log("Player", "pysty liikkua ja posX on: " + posX);
			
			

				
			if (posX > 0 && posX < game.worldWidth - 20) {
				
				if (posX < 5) {
					posX = 5;
				} else if (posX > 210) {
					posX = 210;
				}
			} 
			
			
			
			// Moving the sprite forward and backward
			posX += xSpeed * delta;
			
			
			
			
			// Draw the sprite to the screen.
			sprite.draw(batch);
			
		}
		
		/**
		 * Changes the speed of the player and also set the max speed.
		 * @param speed
		 */
		public void changeXSpeed(float speed) {
			
			xSpeed += speed;
			
			if (xSpeed < -150f) {
				xSpeed = -150;
			} else if (xSpeed > 150) {
				xSpeed = 150;
			} else if (speed == 0) {
				xSpeed = 0;
			}
			
			Gdx.app.log("Speed of the player in ChangeXSpeed", "" + xSpeed);
		}
		
		/**
		 * Gives the x position of the sprite.
		 */
		public float GetX () {
			
			return posX;
		}
		
		/**
		 * Gives the y position of the sprite.
		 */
		public float GetY () {
			
			return posY;
		}
		
		/**
		 * Gives the hitbox of the sprite.
		 */
		public Rectangle GetHitbox() {
			
			return playerRect;
		}	
		
		/**
		 * Initializes the characters own variables affecting the gameplay.
		 */
		public void initialize() {
			
			switch (cc) {
			
			case STILLA:
				
				characterBonus = 1;
				GameScreen.lives += 3;
				
				// The spritesheet of the idle droplet.
				idleSheet = imagehandler.getImage("Droplets/droplet");
				
				Gdx.app.log("Player",  "Normal droplet initialized");
				
				break;
				
				
			case STELLA:
				characterBonus = 3;
				GameScreen.lives += 1;
				
				// The spritesheet of the idle droplet.
				idleSheet = imagehandler.getImage("Droplets/droplet2");
				break;
				
			case POLIUM:
				characterBonus = 2;
				GameScreen.lives += 3;
				
				// The spritesheet of the idle droplet.
				idleSheet = imagehandler.getImage("Droplets/droplet3");
				break;
				
			case PLUM:
				
				characterBonus = -2;
				GameScreen.lives += 2;
				
				// The spritesheet of the idle droplet.
				idleSheet = imagehandler.getImage("Droplets/droplet4");
				break;
				
			default:
				
				idleSheet = imagehandler.getImage("Droplets/droplet");
				
				Gdx.app.log("Player",  "Something went wrong with player's intializing.");
				break;
			
			}
			

			// Array we use to save the animation frames.
			idleFrames = new TextureRegion[IDLE_FRAMES];
			
			// The size of a one sprite.
			TextureRegion[][] tmp = idleSheet.split(24, 48);
			
			// Temp variable used to format walkFrames array
			int index = 0;
			for (int i = 0; i < IDLE_FRAMES; i++) {
				
				// Saving frames to walkFrames array.
				idleFrames[index++] = tmp[0][i];
				
				//System.out.println("walkFrames taulukon pituus: " + walkFrames.length);
				//System.out.println("Tässä tulostellaan indexi numeroa: " + index);
				//System.out.println("Tähän vielä framejen määriä: " + WALK_FRAMES);
			}
			
			// Creates the animation and gives it the framerate.
			idleAnimation = new Animation(0.1f, idleFrames);
			
		}
	}


