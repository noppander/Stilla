package fi.tamk.tiko.eemeli.libgdx.stilla.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Gives the required font for the user.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class FontManager {

	public static BitmapFont font;
	
	private static FontManager instance;
	
	private FontManager () {
		
	}
	
	/**
	 * Static constructor.
	 * @return
	 */
	public static FontManager getInstance() {
		if (instance == null) {
			instance = new FontManager();
		}
		
		return instance;
	}
	
	/**
	 * The method that gives the desired font to the player.
	 * @param size
	 * @return
	 */
	public BitmapFont Font (int size) {
		
		
		// Parameters needed for freetype font.
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Skin/arial.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		
		return font;
		
	}
}
