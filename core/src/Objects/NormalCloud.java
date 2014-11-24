package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fi.tamk.tiko.tiko.eemeli.libgdx.stilla.GameObject;

public class NormalCloud implements GameObject{
	
	private int posX = 0;
	private int posY = 0;
	
	private SpriteBatch batch;
	
	private Sprite sprite;
	
	// Declare the variables
	private Animation animation;
	private Texture spriteSheet;
	private TextureRegion[] animationFrames;
	private TextureRegion currentFrame;
	
	// The number of the frames used in walking animation
	public static final int ANIMATION_FRAMES = 6;
	
	// Declaration of the state time given value 0.
	float stateTime = 0;
	
	public NormalCloud(int posX, int posY, SpriteBatch batch) {
		// TODO Auto-generated constructor stub
		
		this.posX = posX;
		this.posY = posY;
		
		this.batch = batch;
		
		spriteSheet = new Texture(Gdx.files.internal("Clouds/normalcloud.png"));
		
		// Array we use to save the animation frames.
		animationFrames = new TextureRegion[ANIMATION_FRAMES];
		
		// The size of a one sprite.
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 58, 38);
		
		// Temp variable used to format walkFrames array
		int index = 0;
		for (int i = 0; i < ANIMATION_FRAMES; i++) {
			
			// Saving frames to walkFrames array.
			animationFrames[index++] = tmp[0][i];
			
			//System.out.println("walkFrames taulukon pituus: " + walkFrames.length);
			//System.out.println("Tässä tulostellaan indexi numeroa: " + index);
			//System.out.println("Tähän vielä framejen määriä: " + WALK_FRAMES);
		}
		
		// Creates the animation and gives it the framerate.
		animation = new Animation(0.1f, animationFrames);
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		
		
		//
		
	}

	@Override
	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void DrawMySprite() {
		
		float delta = Gdx.graphics.getDeltaTime();
		
		// State time variable used to changing frames.
		stateTime += delta;
		
		currentFrame = animation.getKeyFrame(stateTime,true);
		
		//System.out.println("tmp: " + tmp);
		
		// Creates new sprite with current frame so it can be managed.
		sprite = new Sprite (currentFrame);
		
		//posY += ySpeed * delta;
		
		
		//Gdx.app.log("PosY", "" +  posY);
		
		// Sprite definitions
		sprite.setPosition(posX, posY);
		sprite.setOriginCenter();
		
		//sprite.setSize(24, 48);
		// Set the position of the sprite.
		
		// Checking the inputs
		
		//Gdx.app.log("Player", "Pystyy liikkua ja xSpeed on: " + xSpeed);
		//Gdx.app.log("Player", "pysty liikkua ja posX on: " + posX);
		
		
		
		// Moving the sprite forward and backward
		posY += 200 * delta;
		
		// Draw the sprite to the screen.
		sprite.draw(batch);
		
		//Gdx.app.log("NormalCloud", "" +  delta);
		//Gdx.app.log("Normal Cloud", " This clouds posX: " + posX + " and posY: " + posY);
	}

}
