package fi.tamk.tiko.eemeli.libgdx.stilla.menu;

/**
 * Button used in MainScreen.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class CloudButton {

	private Texture buttonTexture;
	
	public static float xSpeed, ySpeed;
	
	private float posX, posY;
	
	public CloudButton(float posX, float posY) {
		
		this.posX = posX;
		this.posY = posY;
		
		buttonTexture = new Texture(Gdx.files.local("Menu/menucloud.png"));
	}
	
	public void Draw(SpriteBatch batch) {
		posX += xSpeed;
		posY += ySpeed;
		
		batch.draw(buttonTexture, posX, posY);
	}
	
	
}
