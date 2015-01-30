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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
 * Options screen of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class OptionsScreen implements Screen {
	
	// The variables from main class.
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private StillaGame stillagame;
	
	// Texture regions.
	private TextureRegion backgroundTexture;
	private TextureRegion wellTexture;
	private TextureRegion buttonTexture;
	private TextureRegion music_text;
	
	private ImageHandler imageHandler = new ImageHandler();
	
	private MusicHandler musichandler = MusicHandler.getInstance();
	
	private Table table;
	private Stage stage;
	
	private TextButton music_btn;
	private TextButton control_btn;
	
	private TextureRegion menuTitle;
	
	
//	private Skin skin = new Skin (Gdx.files.internal("Skin/CustomSkin/uiskin.json"));
//	TextButton btn = new TextButton("Start game", skin);
	
	/**
	 * Constructor
	 * 
	 * @param game
	 * @param batch
	 * @param camera
	 */
	public OptionsScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {

		
			// Get the batch.
			this.batch = batch;
			
			// Get the camera.
			this.camera = camera;
			
			// new table
			table = new Table();
			table.setFillParent(true);
			
			if (StillaGame.debug == true) {
				table.setDebug(true);
			}
			
			// Get the game.
			stillagame = game;
			
			// Initialize the ImageHandler for Initialize Textures to use.
//			imageHandler.initialize();
			
			// Initialize the textures.
			initalizeTextures();
		
			
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
		
		
		batch.draw(menuTitle, (240/2) - (menuTitle.getRegionWidth() / 2), 260);
		
		
		batch.end();
		
		stage.draw();
		
		checkInput();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	    
		stage.setViewport(StillaGame.viewport);
	    Gdx.app.log("Viewport size before", "" + StillaGame.viewport.getWorldHeight() + StillaGame.viewport.getWorldWidth());
		
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
		
	}
	
	/**
	 * Initializes the textures used in this screen.
	 */
	public void initalizeTextures() {
		
		// Initialize the textures.
		wellTexture = imageHandler.getImage("Menu/WellStates/wellState0");
		backgroundTexture = imageHandler.getImage("Menu/BG/background1");
		buttonTexture = imageHandler.getImage("Menu/menucloud");
		menuTitle = imageHandler.getImage("Menu/options");
		
		
		Gdx.app.log("Menuscreen,  initializeTexures" , "Textures initialized");
		Gdx.app.log("Are any of these null?" , "" + wellTexture);
		
		Skin skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
		
		stage = new Stage();
		
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
		
		music_btn = new TextButton("NULL", skin);
		
		if (MusicHandler.musicOn == true) {
			music_btn.setText("ON");
		} else {
			music_btn.setText("OFF");
		}

		
		music_btn.addListener(new ClickListener() {
             @Override
             public void clicked(InputEvent event, float x, float y) {
            	 
                 MusicHandler.musicOn = !MusicHandler.musicOn;
                 
                 StillaGame.prefs.putBoolean("musicOn", MusicHandler.musicOn);
     			 StillaGame.prefs.flush();
                 
                 // If we change setting, it checks should we play it or not.
                 musichandler.checkToggle(Musics.MENU);
             }
       });
		
		
		control_btn = new TextButton("NULL", skin);
		
		if (GameScreen.screenInput == true) {
			control_btn.setText("ON SCREEN");
		} else {
			control_btn.setText("TILTING");
		}

		
		control_btn.addListener(new ClickListener() {
             @Override
             public void clicked(InputEvent event, float x, float y) {
            	 
            	 GameScreen.screenInput = !GameScreen.screenInput;
                 
             }
       });
		
		table.add(new Label("Music and sfx", skin));
		table.add(music_btn);
		table.row();
		table.add(new Label("Controls", skin));
		table.add(control_btn);
	
	}
	
	/**
	 * Checks the Inputs in this screen.
	 */
	public void checkInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			ScreenHandler.getInstance().ShowMenu();
		}
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			ScreenHandler.getInstance().ShowMenu();
		}
		
		// Checks and changes the text on button.
		if (MusicHandler.musicOn == true) {
			music_btn.setText("ON");
		} else {
			music_btn.setText("OFF");
		}
		
		// Checks and changes the text on button.
		if (GameScreen.screenInput == true) {
			control_btn.setText("ON SCREEN");
		} else {
			control_btn.setText("TILTING");
		}
		
		Gdx.app.log("Check input", "Musichandler musicOn = " + MusicHandler.musicOn);
		
	}

}
