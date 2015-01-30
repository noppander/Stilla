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
 * MainMenu screen of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class MainMenuScreen implements Screen {
	
	// The variables from main class.
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private StillaGame stillagame;
	
	// Texture regions.
	private TextureRegion backgroundTexture;
	private TextureRegion wellTexture;
	private TextureRegion buttonTexture;
	private TextureRegion menuTitle;
	
	// The buttons
	ImageButton arcademode_btn;
	ImageButton storymode_btn;
	ImageButton options_btn;
	ImageButton achievements_btn;
	ImageButton about_btn;
	
	private Stage mainMenu_stg;
	
	private Table table;
	
	private ImageHandler imageHandler = new ImageHandler();
	
	
	
	/**
	 * Constructor
	 * 
	 * @param game
	 * @param batch
	 * @param camera
	 */
	public MainMenuScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {
		
			// Get the batch.
			this.batch = batch;
			
			// Get the camera.
			this.camera = camera;
			
			// Get the game.
			stillagame = game;
			
			
			
			// Create the stage.
			mainMenu_stg = new Stage();
			
			// Create a table that fills the screen. Everything else will go inside this table.
			table = new Table();
			table.setFillParent(true);
			
			if (StillaGame.debug == true) {
				table.setDebug(true);
				
			}
			//table.defaults().expand().fill();
			
			mainMenu_stg.addActor(table);
			
			// Initialize the textures.
			initalizeTextures();
			
			// Initialize the sprites.
			initializeSprites();
			
			
			// Initialize the buttons and their actions and assigns stage.
			InitializeButtons();
			
			
			Gdx.input.setInputProcessor(mainMenu_stg);
			
			//mainMenu_stg.setViewport(viewport = new FitViewport(1000, 1000));
			
			
			// Width 640
			// Height 480
			
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		//Gdx.app.log("MainMenuScreen", "is well texture null?: " + wellTexture);
		
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
		
		mainMenu_stg.act();
		mainMenu_stg.draw();
		
		//Gdx.app.log("stage width", "" + mainMenu_stg.getWidth());
		//Gdx.app.log("stage height", "" + mainMenu_stg.getHeight());
		
		//Gdx.app.log("ACCELOMETER X ", "" + Gdx.input.getAccelerometerX());
		
		checkInput();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		mainMenu_stg.setViewport(StillaGame.viewport);
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
		
		
		Gdx.app.log("MainMenuScreen","welltexture before disposing: " + wellTexture.getTexture());
		wellTexture.getTexture().dispose();
		Gdx.app.log("MainMenuScreen","welltexture after disposing: " + wellTexture.getTexture());
		
		
		
	}
	
	/**
	 * Initializes the textures used in this screen.
	 */
	public void initalizeTextures() {
		
		// Initialize the textures.
		wellTexture = imageHandler.getImage("Menu/WellStates/wellState0");
		backgroundTexture = imageHandler.getImage("Menu/BG/background1");
		buttonTexture = imageHandler.getImage("Menu/menucloud");
		menuTitle = imageHandler.getImage("Menu/mainmenu");
		
		
		Gdx.app.log("Menuscreen,  initializeTexures" , "Textures initialized");
		Gdx.app.log("Are any of these null?" , "" + wellTexture);
		
	}
	
	/**
	 * Initializes the sprites used in this screen.
	 */
	public void initializeSprites() {
		
		Sprite arcademode_spr = new Sprite (imageHandler.getImage("Menu/Buttons/arcademode"));
		SpriteDrawable drawablebutton1 = new SpriteDrawable(arcademode_spr);
		arcademode_btn = new ImageButton(drawablebutton1);
		arcademode_spr = null;
		drawablebutton1 = null;
		
		Sprite storymode_spr = new Sprite (imageHandler.getImage("Menu/Buttons/storymode"));
		SpriteDrawable drawablebutton2 = new SpriteDrawable(storymode_spr);
		storymode_btn = new ImageButton(drawablebutton2);
		storymode_spr = null;
		drawablebutton2 = null;
		
		Sprite options_spr = new Sprite (imageHandler.getImage("Menu/Buttons/options"));
		SpriteDrawable drawablebutton3 = new SpriteDrawable(options_spr);
		options_btn = new ImageButton(drawablebutton3);
		options_spr = null;
		drawablebutton3 = null;
		
		Sprite achievements_spr = new Sprite (imageHandler.getImage("Menu/Buttons/achievements"));
		SpriteDrawable drawablebutton4 = new SpriteDrawable(achievements_spr);
		achievements_btn = new ImageButton(drawablebutton4);
		achievements_spr = null;
		drawablebutton4 = null;
		
		Sprite about_spr = new Sprite (imageHandler.getImage("Menu/Buttons/about"));
		SpriteDrawable drawablebutton5 = new SpriteDrawable(about_spr);
		about_btn = new ImageButton(drawablebutton5);
		about_spr = null;
		drawablebutton5 = null;
		
		
		
		VerticalGroup vg = new VerticalGroup();
		vg.addActor(storymode_btn);
		vg.addActor(achievements_btn);
		
		table.add(vg);
		
		VerticalGroup vg2 = new VerticalGroup();
		
		vg2.addActor(arcademode_btn);
		vg2.addActor(options_btn);
		vg2.addActor(about_btn);
		
		table.add(vg2);

	}
	
	/**
	 * Initializes the buttons used in this screen.
	 */
	public void InitializeButtons() {
		
		// Initializing Start Game Button.
		arcademode_btn.addListener(new InputListener()
	    {
	    	 @Override
	    	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchdown");
	    	        return true;
	    	    }
	    	    @Override
	    	    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchup");
	    	        ScreenHandler.getInstance().ShowComingSoon(); 
	    	    }
	    	
	    });
		
		
		
		storymode_btn.addListener(new InputListener()
	    {
	    	 @Override
	    	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchdown");
	    	        return true;
	    	    }
	    	    @Override
	    	    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchup");
	    	        ScreenHandler.getInstance().ShowLevelSelect(); 
	    	    }
	    	
	    });
		
		options_btn.addListener(new InputListener()
	    {
	    	 @Override
	    	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchdown");
	    	        return true;
	    	    }
	    	    @Override
	    	    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchup");
	    	        ScreenHandler.getInstance().ShowOptions(); 
	    	    }
	    	
	    });
		
		achievements_btn.addListener(new InputListener()
	    {
	    	 @Override
	    	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchdown");
	    	        return true;
	    	    }
	    	    @Override
	    	    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchup");
	    	        ScreenHandler.getInstance().ShowComingSoon(); 
	    	    }
	    	
	    });
		
		about_btn.addListener(new InputListener()
	    {
	    	 @Override
	    	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchdown");
	    	        return true;
	    	    }
	    	    @Override
	    	    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
	    	    {
	    	        System.out.println("touchup");
	    	        ScreenHandler.getInstance().ShowAbout();
	    	    }
	    	
	    });
		
		
		
		
		
		Gdx.app.log("Stages height", "" + mainMenu_stg.getHeight());
		
		
		// initializing another button.
		
		
	}
	
	public void checkInput() {
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			Gdx.app.log("MainMenuScreen", "back button pressed");
			Gdx.input.setCatchBackKey(false);
			imageHandler.disposeAtlas();
			this.dispose();
			Gdx.app.exit();
		}
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.K)) {
					Gdx.app.log("MainMenuScreen", "back button pressed");
					imageHandler.disposeAtlas();
					
		}
	
		
	}

}
