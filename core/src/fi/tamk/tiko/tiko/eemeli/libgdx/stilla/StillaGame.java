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
	private float worldWidth = 240;
	private float worldHeight = 320;
		
	private float screenLeftSide = 0;
	private float screenRightSide = 240;
	
	private Player player;


	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		
		camera.setToOrtho(false, worldWidth, worldHeight);
		
		camera.position.set(worldWidth / 2, worldHeight / 2, 0);
		camera.update();

		viewport = new FitViewport(worldWidth, worldHeight, camera);
		
		player = new Player();
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
		viewport.update(width, height);
		
		super.resize(width, height);
	}
}
