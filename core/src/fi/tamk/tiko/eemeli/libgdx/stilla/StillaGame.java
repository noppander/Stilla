package fi.tamk.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fi.tamk.tiko.eemeli.libgdx.stilla.fonts.FontManager;
import fi.tamk.tiko.eemeli.libgdx.stilla.game.HeightCalculator;
import fi.tamk.tiko.eemeli.libgdx.stilla.game.ScoreCalculator;
import fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects.GameObject;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.*;

/**
 * The main class of the game.
 * 
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 * 
 */
public class StillaGame extends Game {

	// Shape debug variable
	public static boolean debug = false;

	// Batch where drawing is done.
	SpriteBatch batch;

	// Declaring camera
	private OrthographicCamera camera;

	// Debug camera
	private OrthographicCamera debugCamera;

	// The viewport.
	public static Viewport viewport;

	// The width and height of the world.
	public float worldWidth = 240;
	public float worldHeight = 320;

	// Left and right side of the screen used to define movable area
	public float screenLeftSide = 0;
	public float screenRightSide = 240;
	
	private float fps;

	// Boolean defining that the game is running.
	private boolean gameIsOn = true;

	private Player player;

	private BGHandler bghandler;

	private ScreenHandler sh = ScreenHandler.getInstance();
	private HeightCalculator hc = HeightCalculator.getInstance();
	private ScoreCalculator sc = ScoreCalculator.getInstance();

	// New bitmap font
	BitmapFont font38;

	// Preferences for saving.
	public static Preferences prefs;

	// private ObjectHandler objecthandler;

	/**
	 * Creation.
	 */
	@Override
	public void create() {

		// Load a save file or create a one.
		getSaveFile();

		// Catch back key.
		Gdx.input.setCatchBackKey(true);

		// Update totalscore to Scorecalculator.
		sc.updateTotalScore(prefs.getInteger("totalscore"));

		// Update MusicOn to GameScreen.
		MusicHandler.musicOn = prefs.getBoolean("musicOn");

		Gdx.app.log("Total score updated in ScoreCalculator",
				"" + sc.getTotalScore());

		// Create a new camera
		camera = new OrthographicCamera();

		// Sets the camera to desired resolution. In this case 240x320.
		camera.setToOrtho(false, worldWidth, worldHeight);

		// Create new debug camera.
		debugCamera = new OrthographicCamera();

		// Sets the camera to desired resolution. In this case 240x320.
		debugCamera.setToOrtho(false, worldWidth + 500, worldHeight + 500);

		// Updates the camera
		camera.update();

		// Creates new fit viewport so the game will keep the ratio
		viewport = new FitViewport(worldWidth, worldHeight, camera);

		// What is this doing here
		// Creates new player giving it this class, so it can access the values
		// player = new Player(this, camera);

		// Creates new spritebatch.
		batch = new SpriteBatch();

		// New objecthandler
		// objecthandler = new ObjectHandler();

		// Initializing the sprites.
		// objecthandler.initializeSprites();

		// objecthandler.fillObjectArray();

		// this.setScreen(new GameScreen(this, batch, camera));
		// this.setScreen(new MenuScreen(this, batch, camera));

		// Initialize the ScreenHandler.
		ScreenHandler.getInstance().Initialize(this, batch, camera);

		ScreenHandler.getInstance().ShowMenu();

		Gdx.app.log("assda", "" + this + batch + camera);

		font38 = FontManager.getInstance().Font(38);

	}

	/**
	 * Rendering.
	 */
	@Override
	public void render() {
		super.render();

		batch.setProjectionMatrix(debugCamera.combined);

		batch.begin();

		fps = 1f / Gdx.graphics.getDeltaTime();
		
		//Gdx.app.log("Stilla Game", "Is music playing?" + MusicHandler.getInstance().checkIfPlaying());
		
		if (MusicHandler.getInstance().checkIfPlaying() == false) {
			MusicHandler.getInstance().playMusic();
		}

//		if (StillaGame.debug == true) {
//
//			font38.drawMultiLine(batch, "Total Score: " + sc.getTotalScore()
//					+ "\n" + "Score: " + sc.GetScore() + "\n" + "Height: "
//					+ hc.getInstance().getHeight() + "\n" + "Fps: " + fps
//					+ "\n" + "SpawnInterval: " + GameScreen.spawnInterval
//					+ "\n" + "Lives: " + GameScreen.lives + "\n"
//					+ "GameObject Speed: " + GameScreen.gameobjectSpeed + "\n",
//					100, 300);
//		}
//
//		if (sh.getCurrentScreen().equals("MENU")) {
//
//			font38.draw(batch, "Total Score: " + sc.getTotalScore(),
//					(debugCamera.viewportWidth / 10) * 3,
//					(debugCamera.viewportHeight / 10f) * 9.7f);
//
//		} else if (sh.getCurrentScreen().equals("GAME")) {
//			font38.draw(batch, "Height: " + hc.getHeight(),
//					(debugCamera.viewportWidth / 10) * 3,
//					(debugCamera.viewportHeight / 10f) * 9.5f);
//
//			font38.draw(batch, "Score: " + sc.GetScore(),
//					(debugCamera.viewportWidth / 20),
//					(debugCamera.viewportHeight / 20f));
//
//			font38.draw(batch, "Lives: " + GameScreen.lives,
//					((debugCamera.viewportWidth / 10) * 4),
//					(debugCamera.viewportHeight / 20f));
//
//			font38.draw(batch, "Stars: " + GameScreen.starCount,
//					((debugCamera.viewportWidth / 10) * 7),
//					(debugCamera.viewportHeight / 20f));
//		} else if (sh.getCurrentScreen().equals("GAMEOVER")) {
//
//			font38.draw(batch, "Score: " + sc.GetScore(),
//					((debugCamera.viewportWidth / 10) * 3),
//					(debugCamera.viewportHeight / 10f) * 7);
//
//			font38.draw(batch, "Stars: " + GameScreen.starCount + " * "
//					+ "100pts = " + GameScreen.starCount * 100,
//					((debugCamera.viewportWidth / 10) * 3),
//					(debugCamera.viewportHeight / 10f) * 6);
//
//			font38.draw(batch, "Total Score: " + sc.getTotalScore(),
//					((debugCamera.viewportWidth / 10) * 3),
//					(debugCamera.viewportHeight / 10f) * 5);
//
//			font38.draw(batch, "WELL DONE!",
//					((debugCamera.viewportWidth / 10) * 3),
//					(debugCamera.viewportHeight / 10f) * 4);
//
//			font38.draw(batch, "Want to play again?",
//					((debugCamera.viewportWidth / 10) * 3),
//					(debugCamera.viewportHeight / 10f) * 3);
//
//		} else {
//
//		}

		// Gdx.app.log("Current Screen", "" + this.getScreen().toString());
		// Gdx.app.log("StillaGame", "" + fps);

		batch.end();

		// Gdx.app.log("viewport","" + viewport);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		viewport.update(width, height);

		super.resize(width, height);
	}

	/**
	 * Loads the save file if there is one and if there isn't creates one.
	 */
	public void getSaveFile() {

		Gdx.app.log("Is there preferences",
				"" + Gdx.app.getPreferences("SaveFile"));

		if (Gdx.app.getPreferences("SaveFile") == null) {
			Gdx.app.log("StillaGame", "alustetaan totalscore preferenssi");
			prefs.putInteger("totalscore", 0);
			prefs.putBoolean("musicOn", true);
			prefs.flush();
		} else {
			prefs = Gdx.app.getPreferences("SaveFile");
		}

	}

}
