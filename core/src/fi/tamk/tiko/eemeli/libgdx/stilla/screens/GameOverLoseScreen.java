package fi.tamk.tiko.eemeli.libgdx.stilla.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import fi.tamk.tiko.eemeli.libgdx.stilla.ImageHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler;
import fi.tamk.tiko.eemeli.libgdx.stilla.StillaGame;
import fi.tamk.tiko.eemeli.libgdx.stilla.MusicHandler.Musics;
import fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects.GameObject;

/**
 * Gameover Screen when lost.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class GameOverLoseScreen implements Screen {
	// Default initializing.
	private StillaGame stillagame;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private MusicHandler musichandler = MusicHandler.getInstance();
	
	// Background images.
	private Texture background_texture;
	private Texture waterlevel_texture;
	private Texture gameoverTitle;
	
	// Stage.
	private Stage stage;
	private Table table;
	
	// Image Handler
	ImageHandler imageHandler = new ImageHandler();
	
	private Skin skin = new Skin (Gdx.files.internal("Skin/CustomSkin/uiskin.json"));
		
		public GameOverLoseScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {
			
			// Get the batch.
			this.batch = batch;
			
			// Get the camera.
			this.camera = camera;
			
			// Get the game.
			stillagame = game;
			
			// Create the stage.
			stage = new Stage();
			
			
			table = new Table();
			table.setFillParent(true);
			
			if (StillaGame.debug == true) {
				table.setDebug(true);
			}
			
			stage.addActor(table);
			
			// Input processor.
			Gdx.input.setInputProcessor(stage);
			
			// Initialize the textures.
			initializeTextures();
			
			// Initialize the buttons.
			initializeButtons();
			
			musichandler.stopMusic();
			
			musichandler.playMusic(Musics.GAMEOVERLOSE);
			
			
			
		}
		
	

	@Override
	public void render(float delta) {
		
		// Check debug input.
		CheckInput();
		
		// Updating the camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
					
		// Background color.
		Gdx.gl.glClearColor(0, 162, 232, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(background_texture, 0, 0);
		batch.draw(waterlevel_texture, 0, 100);
		batch.draw(gameoverTitle, (240/2) - (gameoverTitle.getWidth() / 2), 260);
		
		batch.end();
		
		stage.draw();
		
		Gdx.app.log("GameOverScreen: ", "Camera height and width" +  camera.viewportHeight + camera.viewportWidth);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
		stage.setViewport(StillaGame.viewport);
	    Gdx.app.log("Viewport size before", "" + StillaGame.viewport.getWorldHeight() + StillaGame.viewport.getWorldWidth());
	    
	    StillaGame.viewport.update(width, height);
	    Gdx.app.log("Viewport size after", ""  + StillaGame.viewport.getWorldHeight() + StillaGame.viewport.getWorldWidth());
		
		
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
	
public void CheckInput() {
	
		// Debug buttons for changing the screen.
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			musichandler.stopMusic();
			ScreenHandler.getInstance().ShowMenu();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			ScreenHandler.getInstance().ShowGame();
		}
		
		// Back button for android
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			musichandler.stopMusic();
			ScreenHandler.getInstance().ShowMenu();
		}

		
	}
	
	/**
	 * Initializes the textures used in this screen.
	 */
	private void initializeTextures() {
		
		// The textures that are loaded.
		background_texture = new Texture(Gdx.files.internal("GameOverScreen/background.png"));
		waterlevel_texture = new Texture(Gdx.files.internal("GameOverScreen/waterlevel.png"));
		gameoverTitle = new Texture(Gdx.files.internal("GameOverScreen/title.png"));
	}
	
	/**
	 * Initializes the buttons used in this screen.
	 */
	private void initializeButtons() {
		
		TextButton restart_btn;
		TextButton mainmenu_btn;
		
		table.add(new Label("Wanna try again?", skin));
		table.row();
		table.add(restart_btn = new TextButton("           Restart           ", skin));
		table.row();
		table.add(new Label("Go to menu?", skin));
		table.row();
		table.add(mainmenu_btn = new TextButton("         Main Menu         ", skin));
		
		
		
		restart_btn.addListener(new InputListener()
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
	    	        
	    	        ScreenHandler.getInstance().ShowGame();
	    	        
	    	      
	    	    }
	    	
	    });
		
		
		mainmenu_btn.addListener(new InputListener()
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
	    	        
	    	        
	    	        
	    	        musichandler.stopMusic();
	    	        
	    	        ScreenHandler.getInstance().ShowMenu();
	    	        
	    	      
	    	    }
	    	
	    });
		
		

	}
}
