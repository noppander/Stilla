package fi.tamk.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Handles loading and using the images.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class ImageHandler {
	
	private TextureAtlas atlas;
	
	/**
	 * Constructor
	 */
	public ImageHandler() {
		if (atlas == null) {
			atlas = new TextureAtlas (Gdx.files.internal("Atlas/StillaAtlas.atlas"));
		}
	}
	
	/**
	 * Returns the asked region.
	 * 
	 * @param name
	 * @return
	 */
	public TextureRegion getImage (String name){
		
		AtlasRegion region = atlas.findRegion(name);
		
		return region;
	}
	
	/**
	 * Disposes the Atlas.
	 */
	public void disposeAtlas() {
		atlas.dispose();
		
	}
}
