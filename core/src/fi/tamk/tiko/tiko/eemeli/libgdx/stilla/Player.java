package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

	public class Player {

		// Declare the variables
		private Animation idleAnimation;
		private Texture idleSheet;
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

		float panForce = 0;
		
		Sound jump;
		
		private float rotation = 0;
		private float alpha = 1;
		
		private int characterBonus;
		private int characterLives;
		
		
		
		// GOING TO BE STATIC VARIABLE
		// Defines what character we are using.
		private int characterInUse = 4;
		
		// Declaration of the main game class
		StillaGame game;
		
		// Boolean which controls the movement of the sprite.
		private boolean movingPossible = true;
		
		// Declaring Inputhandler
		InputHandler inputhandler;
		
		public Player(StillaGame game) {
			
			this.game = game;
			
			posX = game.worldWidth / 2;
			posY = game.worldHeight - 20;
			
			
			// Creating object from InputHandler.
			inputhandler = new InputHandler(game);
			
			
			
			switch(characterInUse) {
			
			// Stilla's bonus and lives and image definer. If characterinuse == 1
			case 1:
				
				characterBonus = 1;
				characterLives = 3;
				
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet.png"));
				break;
				
			// Stella's bonus and lives and image definer. If characterinuse == 2
			case 2:
				
				characterBonus = 3;
				characterLives = 1;
				
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet2.png"));
				break;
				
			// Polium's bonus and lives and image definer. If characterinuse == 3
			case 3:
				
				characterBonus = 2;
				characterLives = 3;
				
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet3.png"));
				break;
				
			// Plum's bonus and lives and image definer. If characterinuse == 4
			case 4:
				
				characterBonus = -2;
				characterLives = 2;
				
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet4.png"));
				break;	
		}
			
			// Array we use to save the animation frames.
			idleFrames = new TextureRegion[IDLE_FRAMES];
			
			// The size of a one sprite.
			TextureRegion[][] tmp = TextureRegion.split(idleSheet, 24, 48);
			
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
			
			//jump = Gdx.audio.newSound(Gdx.files.internal("hop.wav"));
			
		}
		
		
		public void DrawMySprite(SpriteBatch batch, float delta, float screenLeftSide, 
				float screenRightSide, boolean gameIsOn) {
			
			debugforfun();
			
			// State time variable used to changing frames.
			stateTime += delta;
			
			currentFrame = idleAnimation.getKeyFrame(stateTime,true);
			
			//System.out.println("tmp: " + tmp);
			
			// Creates new sprite with current frame so it can be managed.
			sprite = new Sprite (currentFrame);
			
			//posY += ySpeed * delta;
			
			
			//Gdx.app.log("PosY", "" +  posY);
			
			// Sprite definitions
			sprite.setCenter(posX, posY);
			sprite.setAlpha(alpha);
			sprite.setOriginCenter();
			
			sprite.setSize(24, 48);
			// Set the position of the sprite.
			
			// Checking the inputs
			
			xSpeed = inputhandler.CheckInput(posX);
			//Gdx.app.log("Player", "Pystyy liikkua ja xSpeed on: " + xSpeed);
			//Gdx.app.log("Player", "pysty liikkua ja posX on: " + posX);
			
			if (inputhandler.CheckArea(posX) == false) {
				if (posX < 120) {
					posX = 21;
				} else {
					posX = 219;
				}
			} 
			
			
			// Moving the sprite forward and backward
			posX += xSpeed * delta;
			
			// Draw the sprite to the screen.
			sprite.draw(batch);
			
		}
		
		/*
		 *
		 * Gives the x position of the sprite.
		 *
		 */
		public float GetX () {
			
			return posX;
		}
		
		public float GetY () {
			
			return posY;
		}
		

		public void debugforfun() {
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet.png"));
				Gdx.app.log("Player", "Hahmovaihdettu");
				
				// Array we use to save the animation frames.
				idleFrames = new TextureRegion[IDLE_FRAMES];
				
				// The size of a one sprite.
				TextureRegion[][] tmp = TextureRegion.split(idleSheet, 24, 48);
				
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
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
				
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet2.png"));
				Gdx.app.log("Player", "Hahmovaihdettu");
				
				// Array we use to save the animation frames.
				idleFrames = new TextureRegion[IDLE_FRAMES];
				
				// The size of a one sprite.
				TextureRegion[][] tmp = TextureRegion.split(idleSheet, 24, 48);
				
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
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet3.png"));
				Gdx.app.log("Player", "Hahmovaihdettu");
				
				// Array we use to save the animation frames.
				idleFrames = new TextureRegion[IDLE_FRAMES];
				
				// The size of a one sprite.
				TextureRegion[][] tmp = TextureRegion.split(idleSheet, 24, 48);
				
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
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
				
				// The spritesheet of the idle droplet.
				idleSheet = new Texture(Gdx.files.internal("Droplets/droplet4.png"));
				Gdx.app.log("Player", "Hahmovaihdettu");
				
				// Array we use to save the animation frames.
				idleFrames = new TextureRegion[IDLE_FRAMES];
				
				// The size of a one sprite.
				TextureRegion[][] tmp = TextureRegion.split(idleSheet, 24, 48);
				
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
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
							

				
			}
			
		}
	}


