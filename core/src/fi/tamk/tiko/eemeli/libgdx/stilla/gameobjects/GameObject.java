package fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.GameScreen;

public abstract class GameObject {
	
	// The speed of the objects
	//public static float gameobjectSpeed = 1.4f;
	
	// Position of the object.
	protected int posX = 0;
	protected int posY = 0;
	
	// Sprite of the object.
	protected Sprite sprite;
	
	// Declare the variables
	protected Animation animation;
	protected Texture spriteSheet;
	protected TextureRegion[] animationFrames;
	protected TextureRegion currentFrame;
	
	// The sound effect played when hit
	Sound sound;
	
	// Declaration of the state time given value 0.
	float stateTime = 0;
	
	// The rectangle used in collision.
	Rectangle objRect = new Rectangle();
	
	/**
	 * Constructor. 
	 * 
	 * Gets and saves the individual variables.
	 * 
	 * @param spriteSheet
	 * @param ANIMATION_FRAMES
	 * @param FRAME_WIDTH
	 * @param FRAME_HEIGHT
	 */
	public GameObject(TextureRegion spriteSheet, int ANIMATION_FRAMES, int FRAME_WIDTH, int FRAME_HEIGHT) {
		
		// Array we use to save the animation frames.
		animationFrames = new TextureRegion[ANIMATION_FRAMES];
		
		// The size of a one sprite.
		TextureRegion[][] tmp = spriteSheet.split(FRAME_WIDTH, FRAME_HEIGHT);
		
		// Temp variable used to format walkFrames array
		int index = 0;
		
		for (int i = 0; i < ANIMATION_FRAMES; i++) {
			// Saving frames to walkFrames array.
			animationFrames[index++] = tmp[0][i];
		}
		
		// Creates the animation and gives it the framerate.
		animation = new Animation(0.1f, animationFrames);
		
		// Defining the size of the rectangle.
		objRect.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		
				
	}
	
	/**
	 *  If the hitbox must be set with more accuracy, this is what does it.
	 */
	public abstract void SetHitbox(int width, int height);
	
	public void SetHitboxPos(int posX, int posY) {
		// Objects hitbox
		objRect.setPosition(posX, posY);
	}
	
	/**
	 * Returns the current object's collision rectangle.
	 * 
	 * After this collision is noticed, we increase accuracy.
	 * 
	 * @return
	 */
	public Rectangle GetHitbox() {
		
		return objRect;
	};
	
	/**
	 * Sets the Collision Bitmap from current frame.
	 * 
	 * Used to accurate collision checking
	 * 
	 */
	public abstract void SetCollisionBitMap();
	
	/**
	 * Gets the Collision Bitmap from current frame.
	 * 
	 * Used to accurate collision checking
	 */
	public abstract void GetCollisionBitMap();
	
	/**
	 * Gets the individual object ID used for object's actions.
	 */
	public abstract String GetObjectID();
	
	public int GetPosX() {
		return posX;
	};
	
	public int GetPosY() {
		return posY;
	};
	
	/**
	 * Handles the drawing and moving of the game object sprites.
	 * 
	 * @param batch
	 */
	public void DrawMySprite(SpriteBatch batch, float speed) {
		
		// Get the deltatime.
		float delta = Gdx.graphics.getDeltaTime();
		
		// State time variable used to changing frames.
		stateTime += delta;
		
		currentFrame = animation.getKeyFrame(stateTime,true);
		
		// Creates new sprite with current frame so it can be managed.
		sprite = new Sprite (currentFrame);
		
		// Sprite definitions
		sprite.setPosition(posX, posY);
		sprite.setOriginCenter();
		
		
		
		//sprite.setSize(24, 48);
		// Set the position of the sprite.
		
		// Checking the inputs
		//Gdx.app.log("Player", "Pystyy liikkua ja xSpeed on: " + xSpeed);
		//Gdx.app.log("Player", "pysty liikkua ja posX on: " + posX);
		
		// Moving the sprite forward and backward
		posY += speed * delta * 100;
		
		// Set the hitbox.
		SetHitboxPos(posX, posY);
				
		// Draw the sprite to the screen.
		sprite.draw(batch);
		
		//Gdx.app.log("NormalCloud", "" +  delta);
		//Gdx.app.log("Normal Cloud", " This clouds posX: " + posX + " and posY: " + posY);
	}
	
	public void setSound(Sound s) {
		this.sound = s;
		//sound.setVolume((long) 1.2, MusicHandler.masterVolume);
	}
	
	public void playSound() {
		sound.play();
	}
}
