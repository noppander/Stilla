package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StillaGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	// Declaring camera
	private OrthographicCamera camera ;
	
	private Viewport viewport;
	
	// The width and height of the world.
	public float worldWidth = 240;
	public float worldHeight = 320;
	
	// Left and right side of the screen used to define movable area
	public float screenLeftSide = 0;
	public float screenRightSide = 240;
	
	// Boolean defining that the game is running.
	private boolean gameIsOn = true;
	
	private Player player;
	
	private BGHandler bghandler;
	
	private ObjectHandler objecthandler;


	
	@Override
	public void create () {
		
		// Create a new camera
		camera = new OrthographicCamera();
		
		// Sets the camera to desired resolution. In this case 240x320.
		camera.setToOrtho(false, worldWidth, worldHeight);
		
		// Updates the camera
		camera.update();

		// Creates new fit viewport so the game will keep the ratio
		viewport = new FitViewport(worldWidth, worldHeight, camera);
		
		// Creates new player giving it this class, so it can access the values
		player = new Player(this);
		
		// Creates new spritebatch.
		batch = new SpriteBatch();
		
		// New BGHandler
		bghandler = new BGHandler(worldWidth, worldHeight);
		
		// New objecthandler
		objecthandler = new ObjectHandler();
		
		// Initializing the sprites.
		objecthandler.initializeSprites();
		
		objecthandler.fillObjectArray();
		
		this.setScreen(new GameScreen(this, batch, camera));
		
		
	}

	@Override
	public void render () {
		super.render();
		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
		viewport.update(width, height);
		
		super.resize(width, height);
	}
}
