package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
		private float xSpeed = 50;
		
		private float ySpeed = 0;
		private float jumpSpeed = 400;
		private float gravity = 10;
		private boolean grounded = false;
		
		private Sprite sprite;

		float panForce = 0;
		
		Sound jump;
		
		private float rotation = 0;
		private float alpha = 1;
		
		public Player() {
			
			// The spritesheet of the walking image.
			idleSheet = new Texture(Gdx.files.internal("droplet.png"));
			
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
		
		
		public void DrawMySprite(SpriteBatch batch, float delta, 
				int sizeX, int sizeY, float screenLeftSide, 
				float screenRightSide, boolean gameIsOn) {
			
			// State time variable used to changing frames.
			stateTime += delta;
			
			currentFrame = idleAnimation.getKeyFrame(stateTime,true);
			
			//System.out.println("tmp: " + tmp);
			
			// Creates new sprite with current frame so it can be managed.
			sprite = new Sprite (currentFrame);
			
			accelerometerInput ();
			
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && xSpeed > 50) {
				xSpeed -= 3;
			}
			
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && xSpeed < 300) {
				xSpeed += 3;
			}
			
			if (posX > screenLeftSide) {
				
			}
			
			
			
			// Moving the sprite forward
			posX += xSpeed * delta;
			
			//posY += ySpeed * delta;
			
			
			//Gdx.app.log("PosY", "" +  posY);
			
			
				sprite.setPosition(posX, posY);
				sprite.setAlpha(alpha);
			
			sprite.setSize(sizeX, sizeY);
			// Set the position of the sprite.
			
			
			
			
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
		
		public void setSpritePosition (float delta, float screenLeftSide, float screenRightSide) {
			
			
		}
		
		public void accelerometerInput () {
			//Gdx.app.log("Accelerometer", "" + Gdx.input.getAccelerometerY());
			
			if (Gdx.input.getAccelerometerY() > 2.5f) {
				if (xSpeed < 300) {
					xSpeed += 3;
				}
				
			} else if (Gdx.input.getAccelerometerY() < -2.5) {
				if (xSpeed > 50) {
					xSpeed -= 3;
				
				}
			}
			
		}
		
		public void jump(float flingSpeed) {
			panForce = (flingSpeed * 1.5f) * -1;

			Gdx.app.log("Fling", "" + panForce);
		}
	}


