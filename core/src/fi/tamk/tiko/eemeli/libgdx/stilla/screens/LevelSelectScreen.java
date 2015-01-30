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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fi.tamk.tiko.eemeli.libgdx.stilla.ImageHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.StillaGame;
import fi.tamk.tiko.eemeli.libgdx.stilla.menu.CloudButton;

/**
 * LevelSelect screen of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class LevelSelectScreen implements Screen {
	
	// The variables from main class.
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private StillaGame stillagame;
	
	// The characters
	public enum CharacterClass {
		STILLA, STELLA, POLIUM, PLUM
	}
	
	// Texture regions.
	private TextureRegion backgroundTexture;
	private TextureRegion wellTexture;
	private TextureRegion buttonTexture;
	private TextureRegion menuTitle;
	
	private Stage mainMenu_stg;
	
	private Table table;
	
	private ImageHandler imageHandler = new ImageHandler();
	private MusicHandler musichandler = new MusicHandler();
	
	
	private Skin skin = new Skin (Gdx.files.internal("Skin/CustomSkin/uiskin.json"));
	TextButton btn = new TextButton("           Start game           ", skin);
	
	
	private Stage stage;
	
	private ImageButton char1_btn;
	private ImageButton char2_btn;
	private ImageButton char3_btn;
	private ImageButton char4_btn;
	
	private ImageButton level1_btn;
	private ImageButton level2_btn;
	private ImageButton level3_btn;
	
	private Music menuMusic;
	
	private TextureAtlas atlas;
	
	/**
	 * Constructor
	 * 
	 * @param game
	 * @param batch
	 * @param camera
	 */
	public LevelSelectScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {
		
		atlas = new TextureAtlas (Gdx.files.internal("Atlas/StillaAtlas.atlas"));
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
			
			initalizeTextures();
			
			initializeSprites();
			
			// Initialize the buttons and their actions and assigns stage.
			initializeButtons();
			
			Gdx.input.setInputProcessor(mainMenu_stg);
			
			ScreenHandler.getInstance().setCharacter(CharacterClass.STILLA);
			
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
		
		mainMenu_stg.act();
		mainMenu_stg.draw();
		
		
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
		
		level1_btn = null;
		level2_btn = null;
		level3_btn = null;
		char1_btn = null;
		char2_btn = null;
		char3_btn = null;
		char4_btn = null;
		backgroundTexture.getTexture().dispose();
		
		
	}
	
	/**
	 * Initializes the textures used in this screen.
	 */
	public void initalizeTextures() {
		
		// Initialize the textures.
		wellTexture = imageHandler.getImage("Menu/WellStates/wellState0");
		backgroundTexture = imageHandler.getImage("Menu/BG/background1");
		buttonTexture = imageHandler.getImage("Menu/menucloud");
		menuTitle = imageHandler.getImage("Menu/levelselect");
		
		Gdx.app.log("Menuscreen,  initializeTexures" , "Textures initialized");
		Gdx.app.log("Are any of these null?" , "" + wellTexture);
		
	}
	
	/**
	 * Initializes the sprites used in this screen.
	 */
	public void initializeSprites() {
		
		Sprite level1_spr = new Sprite (imageHandler.getImage("Menu/level1"));
		SpriteDrawable drawableLevel1 = new SpriteDrawable(level1_spr);
		level1_btn = new ImageButton(drawableLevel1);
		level1_spr = null;
		drawableLevel1 = null;
		
		Sprite level2_spr = new Sprite (imageHandler.getImage("Menu/level2"));
		SpriteDrawable drawableLevel2 = new SpriteDrawable(level2_spr);
		level2_btn = new ImageButton(drawableLevel2);
		level2_spr = null;
		drawableLevel2 = null;
		
		Sprite level3_spr = new Sprite (imageHandler.getImage("Menu/level3"));
		SpriteDrawable drawableLevel3 = new SpriteDrawable(level3_spr);
		level3_btn = new ImageButton(drawableLevel3);
		level3_spr = null;
		drawableLevel3 = null;
		
		
		Sprite char1_spr = new Sprite (imageHandler.getImage("Menu/CharacterBox1"));
		SpriteDrawable drawable1 = new SpriteDrawable(char1_spr);
		char1_btn = new ImageButton(drawable1);
		char1_spr = null;
		drawable1 = null;
		
		Sprite char2_spr = new Sprite (imageHandler.getImage("Menu/CharacterBox2"));
		SpriteDrawable drawable2 = new SpriteDrawable(char2_spr);
		char2_btn = new ImageButton(drawable2);
		char2_spr = null;
		drawable1 = null;
		
		Sprite char3_spr = new Sprite (imageHandler.getImage("Menu/CharacterBox3"));
		SpriteDrawable drawable3 = new SpriteDrawable(char3_spr);
		char3_btn = new ImageButton(drawable3);
		char3_spr = null;
		drawable1 = null;
		
		Sprite char4_spr = new Sprite (imageHandler.getImage("Menu/CharacterBox4"));
		SpriteDrawable drawable4 = new SpriteDrawable(char4_spr);
		char4_btn = new ImageButton(drawable4);
		char4_spr = null;
		drawable1 = null;
		
		
		Table table2 = new Table();
		
		table2.add(level1_btn);
		table2.add(level2_btn);
		table2.add(level3_btn);
		table.add(table2).colspan(4);
		table.row();
		
		
		table.bottom();
		table.defaults().pad(3f);
		table.add(char1_btn);
		table.add(char2_btn);
		table.add(char3_btn);
		table.add(char4_btn);
		
		
	}
	
	/**
	 * Initializes the buttons used in this screen.
	 */
	public void initializeButtons() {
		
		// Initializing Start Game Button.
		
		
		char1_btn.addListener(new InputListener()
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
	    	           
	    	        ScreenHandler.getInstance().setCharacter(CharacterClass.STILLA);
	    	      
	    	    }
	    	
	    });
		
		char2_btn.addListener(new InputListener()
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
	    	        
	    	      
	    	        ScreenHandler.getInstance().setCharacter(CharacterClass.STELLA);
	    	      
	    	    }
	    	
	    });
		
		char3_btn.addListener(new InputListener()
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
	    	         
	    	        
	    	        ScreenHandler.getInstance().setCharacter(CharacterClass.POLIUM);
	    	      
	    	    }
	    	
	    });
		
		char4_btn.addListener(new InputListener()
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
	    	          
	    	        ScreenHandler.getInstance().setCharacter(CharacterClass.PLUM);

	    	      
	    	    }
	    	
	    });
		
		level1_btn.addListener(new InputListener()
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
	    	            
	    	        
	    	        
	    	        ScreenHandler.getInstance().setDifficulty("EASY");
	    	        ScreenHandler.getInstance().ShowGame();
	    	      
	    	    }
	    	
	    });
		
		level2_btn.addListener(new InputListener()
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
	    	            
	    	        
	    	        
	    	        ScreenHandler.getInstance().setDifficulty("MEDIUM");
	    	        ScreenHandler.getInstance().ShowGame();
	    	      
	    	    }
	    	
	    });
		
		level3_btn.addListener(new InputListener()
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
	
	    	        ScreenHandler.getInstance().setDifficulty("HARD");
	    	        ScreenHandler.getInstance().ShowGame();
	    	      
	    	    }
	    	
	    });
		
		Gdx.app.log("Stages height", "" + mainMenu_stg.getHeight());
		
		
		// initializing another button.
		
		
	}
	
	public void checkInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			ScreenHandler.getInstance().ShowMenu();
		}
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			ScreenHandler.getInstance().ShowMenu();
		}
	}

}
