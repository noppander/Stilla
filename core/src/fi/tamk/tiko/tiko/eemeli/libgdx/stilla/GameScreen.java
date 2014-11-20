package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	
	StillaGame stillagame;
	
	private Player player;
	
	private BGHandler bghandler;
	
	private ObjectHandler objecthandler;
	
	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	
	public GameScreen(StillaGame game, SpriteBatch batch, OrthographicCamera camera) {

		// Get the batch
		this.batch = batch;
		
		// Get the camera
		this.camera = camera;
		
		stillagame = game;
		
		// Creates new player giving it this class, so it can access the values
		player = new Player(game);
		
		// New BGHandler
		bghandler = new BGHandler(game.worldWidth, game.worldHeight);
		
		// New objecthandler
		//objecthandler = new ObjectHandler();
		
		// Initializing the sprites.
		//objecthandler.initializeSprites();
		
		//objecthandler.fillObjectArray();
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		// Background color.
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Updating the camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// Begins the batch
		batch.begin();
		
		bghandler.moveBackground(1f);
		bghandler.drawBackground(batch);
		
		player.DrawMySprite(batch, delta, stillagame.screenLeftSide, stillagame.screenRightSide);
		
		//objecthandler.RunTheGame();
		
		//objecthandler.drawSprites(batch);
		
		
		
		// Ends the batch
		batch.end();
		
		Gdx.app.log("GameScreen", "Drawing the graphics in gamescreen class");
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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

}
