package fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * A GameObject Instance.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class ThunderCloud extends GameObject{
	
	// The number of the frames used in this particular animation.
	private static final int ANIMATION_FRAMES = 5;
	
	// The size of the one frame. Used also in collision detection.
	private static final int FRAME_WIDTH = 81;
	private static final int FRAME_HEIGHT = 61;
	
	// The ID so object can be identified.
	private static final String OBJECT_ID = "THUNDER_CLOUD";
	
	// The sounds effect when hit.
		Sound sound  = Gdx.audio.newSound(Gdx.files.internal("Music/sfx/thundercloud.wav"));;
	
	/**
	 * Constructor
	 * 
	 * @param posX
	 * @param posY
	 * @param batch
	 * @param spriteSheet
	 */
	public ThunderCloud(int posX, int posY, TextureRegion spriteSheet, SpriteBatch batch) {
		
		super(spriteSheet, ANIMATION_FRAMES, FRAME_WIDTH, FRAME_HEIGHT);
		
		// Set the positions.
		this.posX = posX;
		this.posY = posY;
		
		SetHitbox(58, 38);
		SetHitboxPos(posX, posY);
		
		setSound(sound);
	}
	
	public Rectangle GetHitbox() {
	return super.GetHitbox();
	}

	@Override
	public void DrawMySprite(SpriteBatch batch, float speed) {
		super.DrawMySprite(batch, speed);
	}

	@Override
	public void SetHitbox(int width, int height) {
		// TODO Auto-generated method stub
		
		objRect.setSize(width, height);
		
	}
	
	@Override
	public void SetHitboxPos(int x, int y) {
		// TODO Auto-generated method stub
		
		objRect.setPosition(x + 10, y + 20);
		
	}

	@Override
	public void SetCollisionBitMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetCollisionBitMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetObjectID() {
		return OBJECT_ID;
	}
	
	@Override
	public void setSound(Sound s) {
		// TODO Auto-generated method stub
		super.setSound(s);
	}

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		super.playSound();
	}

}
