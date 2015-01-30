package fi.tamk.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import fi.tamk.tiko.eemeli.libgdx.stilla.game.HeightCalculator;
import fi.tamk.tiko.eemeli.libgdx.stilla.gameobjects.GameObject;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.GameScreen;
import fi.tamk.tiko.eemeli.libgdx.stilla.screens.ScreenHandler;

/**
 * Handles the debug inputs of the game.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class InputHandler {
	
	// The game instance.
	private StillaGame game;
	
	// The xSpeed given back to the player.
	private float xSpeed;
	
	// Right and Left side of the screen used to stop the player.
	private float screenRightSide = 0;
	private float screenLeftSide;
	
	// The maxspeed of the player
	private float maxSpeed = 150;
	
	private HeightCalculator heightcalculator = HeightCalculator.getInstance();
	
	
	
	/**
	 * The constructor.
	 * 
	 * @param game
	 */
	public InputHandler(StillaGame game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		
		screenLeftSide = game.worldWidth;
		
		
		
	}
	
	/**
	 * Called from Player class.
	 * 
	 * Returns xSpeed for horisontal control.
	 * 
	 * @param posX
	 * @return
	 */
	public float checkPlayerInput(float posX) {
		
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && posX > 0) {
			
			//Gdx.app.log("InputHandler", "Left arrow pressed");
			
			if (posX > 0) {
				xSpeed = -maxSpeed;
				
			} else {
				xSpeed = 0;
				//Gdx.app.log("InputHandler", "Onto edge, cant move");
			}
			
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && posX < 240) {
			xSpeed = maxSpeed;
			//Gdx.app.log("InputHandler", "right arrow pressed");
		}
		
		if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xSpeed = 0;
			//Gdx.app.log("InputHandler", "neither of arrows pressed");
		}
		
		
		
		return xSpeed;
	}
	
	/**
	 * Checks the debug inputs.
	 */
	public void checkDebugInput() {

		if (!Gdx.input.isKeyPressed(Input.Keys.UP)) {
			GameScreen.gameobjectSpeed -= 0.2f;
			//Gdx.app.log("InputHandler", "neither of arrows pressed");
		}
		
		if (!Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			GameScreen.gameobjectSpeed += 0.2;
			//Gdx.app.log("InputHandler", "neither of arrows pressed");
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.O)) {
			GameScreen.spawnInterval += 0.02;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			GameScreen.spawnInterval -= 0.02;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			ScreenHandler.getInstance().ShowMenu();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			ScreenHandler.getInstance().ShowGame();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			heightcalculator.changeHeight(-10000);
			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			ScreenHandler.getInstance().ShowGameOverLose();
		}
		
	}
	
	/**
	 * Defines the area where player can move.
	 * 
	 * @param posX
	 * @return
	 */
	public boolean CheckArea(float posX) {
		boolean possible = false;
		
		
		
		return possible;
	}
	
}
