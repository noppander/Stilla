package fi.tamk.tiko.eemeli.libgdx.stilla;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects.GameObject;

/**
 * Handles music of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class MusicHandler {
	
	public static float masterVolume = 0.1f;
	
	public static boolean musicOn = true;
	
	public String musicID = "menu";
	
	private Music currentMusic;
	
	private ArrayList<Music> musicList = new ArrayList<Music>();
	
	/**
	 * The Musics enums used to define what music we are controlling.
	 * 
	 * @author Eemeli
	 *
	 */
	public enum Musics {
		MENU, GAME, GAME2, GAME3, GAMEOVERWIN, GAMEOVERLOSE
	}
	
	/**
	 * Constructor
	 */
	public MusicHandler() {
		
	}
	
	public static MusicHandler instance;
	
	/**
	 * Instance getter.
	 * @return
	 */
	public static MusicHandler getInstance () {
		
		if (instance == null) {
			instance = new MusicHandler();
		}
		
		return instance;
	}
	
	/**
	 * Initializes the musics and adds them to the list.
	 */
	public void initialize () {
		
		currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/menuMusic.mp3"));
		currentMusic.setLooping(true);
		
	}
	
	public void disposeMusic() {
		
		currentMusic.dispose();
		
	}
	/**
	 * Plays the selected music.
	 * 
	 * @param a
	 */
	public void playMusic(Musics a) {
		
		currentMusic.stop();
		
		if (currentMusic != null) {
			currentMusic.dispose();
		}
		
		if (MusicHandler.musicOn == true) {
			switch(a) {
			case GAME:
				musicID = "game";
				currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/gameMusic1.mp3"));
				currentMusic.play();
				break;
			case GAME2:
				musicID = "game";
				currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/gameMusic2.mp3"));
				currentMusic.play();
				break;
			case GAME3:
				musicID = "game";
				currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/gameMusic3.mp3"));
				currentMusic.play();
				break;
			case MENU:
				musicID = "menu";
				currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/menuMusic.mp3"));
				currentMusic.play();
				break;
			case GAMEOVERWIN:
				musicID = "gameover";
				currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/gameOverWin.mp3"));
				currentMusic.play();
				break;
			case GAMEOVERLOSE:
				musicID = "gameover";
				currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/gameOverLose.mp3"));
				currentMusic.play();
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Plays the selected music.
	 * 
	 * @param a
	 */
	public void playMusic() {
		
		currentMusic.play();
		
	}
	
	/**
	 * Stops all of the musics added in the list.
	 */
	public void stopMusic() {
		
			
			currentMusic.stop();
			currentMusic.dispose();

	}
	
	/**
	 * Sets volume for every music in the list.
	 * 
	 * @param b
	 */
	public void setVolume(float v) {
	
			currentMusic.setVolume(v);
		
	}
	
	/**
	 * With this function you can check whether or not some music is playing.
	 * @param a
	 * @return
	 */
	public boolean checkIfPlaying() {
		
		boolean b = false;
		
		b = currentMusic.isPlaying();
		
		return b;
	}
	
	/**
	 * Checks if the music is turned on in options menu and starts playing the song.
	 * 
	 * @param a
	 */
	public void checkToggle(Musics a) {
		
		if (MusicHandler.musicOn == false) {
			this.stopMusic();
		} else {
			playMusic(a);
		}
		
	}
}
