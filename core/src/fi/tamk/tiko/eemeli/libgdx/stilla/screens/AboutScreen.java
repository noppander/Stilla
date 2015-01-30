package fi.tamk.tiko.eemeli.libgdx.stilla.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fi.tamk.tiko.eemeli.libgdx.stilla.ImageHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.StillaGame;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler.Musics;
import fi.tamk.tiko.eemeli.libgdx.stilla.menu.CloudButton;

/**
 * About screen of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class AboutScreen implements Screen {
	
	// The variables from main class.
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private StillaGame stillagame;
	
	// Texture regions.
	private TextureRegion backgroundTexture;
	private TextureRegion wellTexture;
	private TextureRegion buttonTexture;
	private TextureRegion aboutTexture;
	
	
	private ImageHandler imageHandler = new ImageHandler();
	
	
//	private Skin skin = new Skin (Gdx.files.internal("Skin/CustomSkin/uiskin.json"));
//	TextButton btn = new TextButton("Start game", skin);
	
	private Music menuMusic;
	
	/**
	 * Constructor
	 * 
	 * @param game
	 * @param batch
	 * @param camera
	 */
	public AboutScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {

		
			// Get the batch.
			this.batch = batch;
			
			// Get the camera.
			this.camera = camera;
			
			// Get the game.
			stillagame = game;
			
			// Initialize the textures.
			initalizeTextures();
			
			// Nulls the input processor so buttons in menu can't be pressed
			Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		// Background color.
		Gdx.gl.glClearColor(0, 162, 232, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Updating the camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// Begins the batch
		batch.begin();
		
		batch.draw(backgroundTexture, 0, 0);
		batch.draw(wellTexture, 0, 0);
		batch.draw(aboutTexture, 240/2 - aboutTexture.getRegionWidth()/2,  320/2 - aboutTexture.getRegionHeight()/2);
		
		
		
		batch.end();
		
		checkInput();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	    
	    StillaGame.viewport.update(width, height);
	    Gdx.app.log("Viewport size after", ""  + StillaGame.viewport.getWorldHeight() + StillaGame.viewport.getWorldWidth());
		
	    
	    camera.update();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		wellTexture.getTexture().dispose();
		
	}
	
	/**
	 * Initializes the textures used in this screen.
	 */
	public void initalizeTextures() {
		
		// Initialize the textures.
		wellTexture = imageHandler.getImage("Menu/WellStates/wellState0");
		backgroundTexture = imageHandler.getImage("Menu/BG/background1");
		buttonTexture = imageHandler.getImage("Menu/menucloud");
		aboutTexture = imageHandler.getImage("Menu/about");
		
		Gdx.app.log("Menuscreen,  initializeTexures" , "Textures initialized");
		Gdx.app.log("Are any of these null?" , "" + wellTexture);
		
	}
	
	public void checkInput() {
		
		// Back button for pc.
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			ScreenHandler.getInstance().ShowMenu();
		}
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			ScreenHandler.getInstance().ShowMenu();
		}
	}

}
