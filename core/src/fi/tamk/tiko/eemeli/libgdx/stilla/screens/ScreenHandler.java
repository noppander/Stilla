package fi.tamk.tiko.eemeli.libgdx.stilla.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.StillaGame;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler.Musics;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.LevelSelectScreen.CharacterClass;

/**
 * Screen handler used to change screens of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class ScreenHandler {
	
	private static ScreenHandler instance;
	public static boolean musicOn = true;
	
	private StillaGame game;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private Screen menuScreen;
	private Screen levelSelectScreen;
	private Screen gameScreen;
	private Screen gameOverWinScreen;
	private Screen gameOverLoseScreen;
	private Screen comingSoonScreen;
	private Screen aboutScreen;
	
	private String difficulty;
	private CharacterClass cc;
	
	private MusicHandler musichandler = MusicHandler.getInstance();
	
	private float musicVolume = MusicHandler.masterVolume;
	
	private String currentScreen;
	
	private ScreenHandler () {
		
	}
	
	/**
	 * Static constructor
	 * @return
	 */
	public static ScreenHandler getInstance() {
		if (instance == null) {
			
			instance = new ScreenHandler();
			//Gdx.app.log("ScreenHandler", "New Screenhandler instantiated");
		} else {
			//Gdx.app.log("ScreenHandler", "New Screenhandler not instantiated");
		}
		
		return instance;
	}
	
	/**
	 * Basic initialize made in everywhere.
	 * @param game
	 * @param batch
	 * @param camera
	 */
	public void Initialize (StillaGame game, SpriteBatch batch, OrthographicCamera camera) {
		this.game = game;

		this.batch = batch;

		this.camera = camera;

		// Initialize the musics.
		musichandler.initialize();
	}
	
	/**
	 * Shows the menuscreen.
	 */
	public void ShowMenu() {
		
			
			
			currentScreen = "MENU";
			
			
			menuScreen = new MainMenuScreen(game, batch, camera);
			
			if (musichandler.checkIfPlaying() == false && musicOn == true) {
				// Load the music.
				musichandler.playMusic(Musics.MENU);
				musichandler.setVolume(musicVolume);
				
				Gdx.app.log("Screen Handling", "Menu song playing");
			} else {
				if (!musichandler.musicID.equals("menu")) {
					musichandler.stopMusic();
				}
				Gdx.app.log("Screen Handling", "Menu song NOT playing");
			}
			
			Gdx.app.log("Screen Handling", "Screen changed to MENU");
		
		game.setScreen(menuScreen);
	}
	
	/**
	 * Shows the game screen
	 */
	public void ShowGame(){
		
		// Disposing all screens.
		disposeAllScreens();
		
		currentScreen = "GAME";
		
		gameScreen = new GameScreen(game, batch, camera, difficulty, cc);
		
		game.setScreen(gameScreen);
		
		Gdx.input.setInputProcessor(null);
		
		musichandler.stopMusic();

		if (musicOn) {
		// Depending on difficulty, formats the array.
			if (difficulty.equals("EASY")) {
				musichandler.playMusic(Musics.GAME);
				musichandler.setVolume(musicVolume);
				
			} else if (difficulty.equals("MEDIUM")) {
				musichandler.playMusic(Musics.GAME2);
				musichandler.setVolume(musicVolume);
				
			} else if (difficulty.equals("HARD")) {
				musichandler.playMusic(Musics.GAME3);
				musichandler.setVolume(musicVolume);
			}
		}
		
	}
	
	/**
	 * Shows the gameover screen when won.
	 */
	public void ShowGameOverWin(){
		
		// Disposing all screens.
		disposeAllScreens();
		
		currentScreen = "GAMEOVER";
		
		gameOverWinScreen = new GameOverWinScreen(game, batch, camera);
		
		game.setScreen(gameOverWinScreen);
		
		
	}
	
	/**
	 * Shows the gameover screen when lost.
	 */
	public void ShowGameOverLose(){
		
		// Disposing all screens.
		disposeAllScreens();
		
		gameOverLoseScreen = new GameOverLoseScreen(game, batch, camera);
		
		game.setScreen(gameOverLoseScreen);
		
		
	}
	
	/**
	 * Shows levelselect screen.
	 */
	public void ShowLevelSelect(){
		
		// Disposing all screens.
				disposeAllScreens();
		
		levelSelectScreen = new LevelSelectScreen(game, batch, camera);
		
		game.setScreen(levelSelectScreen);
		
	}
	
	/**
	 * Shows coming soon screen.
	 */
	public void ShowComingSoon() {
		// Disposing all screens.
				disposeAllScreens();
		
		comingSoonScreen = new ComingSoon(game, batch, camera);
		game.setScreen(comingSoonScreen);
	}
	
	/**
	 * Shows about screen
	 */
	public void ShowAbout() {
		// Disposing all screens.
				disposeAllScreens();
				
		menuScreen.dispose();
		
		aboutScreen = new AboutScreen(game, batch, camera);
		
		game.setScreen(aboutScreen);
	}
	
	/**
	 * Shows options
	 */
	public void ShowOptions() {
		// Disposing all screens.
				disposeAllScreens();
				
		game.setScreen(new OptionsScreen(game, batch, camera));
	}
	
	/**
	 * Sets difficulty
	 * @param d
	 */
	public void setDifficulty(String d) {
		this.difficulty = d;
	}

	/**
	 * Sets characterclass
	 * @param cc
	 */
	public void setCharacter(CharacterClass cc) {
		// TODO Auto-generated method stub
		this.cc = cc;
		
	}
	
	public void disposeAllScreens() {
		
		// Disposing if there is one.
		if (gameOverWinScreen != null) {
			gameOverWinScreen.dispose();
		}
	
		if (gameOverLoseScreen != null) {
			gameOverLoseScreen.dispose();
		}
		
		if (aboutScreen != null) {
			aboutScreen.dispose();
		}
		
		if (gameOverWinScreen != null) {
			gameOverWinScreen.dispose();
		}
		
		if (gameOverLoseScreen != null) {
			gameOverLoseScreen.dispose();
		}
		
	}
	
	/**
	 * Gets current screens ID.
	 * @return
	 */
	public String getCurrentScreen() {
		return currentScreen;
	}
	
}
