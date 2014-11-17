package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {
	
	private StillaGame game;
	
	private float xSpeed;
	
	private float screenRightSide = 0;
	private float screenLeftSide;
	
	private float maxSpeed = 150;
	
	
	public InputHandler(StillaGame game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		
		screenLeftSide = game.worldWidth;
		
		
		
	}
	
	public float CheckInput(float posX) {
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && posX > 0) {
			
			Gdx.app.log("InputHandler", "Left arrow pressed");
			
			if (posX > 0) {
				xSpeed = -maxSpeed;
				
			} else {
				xSpeed = 0;
				Gdx.app.log("InputHandler", "Onto edge, cant move");
			}
			
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && posX < 240) {
			xSpeed = maxSpeed;
			Gdx.app.log("InputHandler", "right arrow pressed");
		}
		
		if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xSpeed = 0;
			//Gdx.app.log("InputHandler", "neither of arrows pressed");
		}
		
		
		
		return xSpeed;
	}
	
	public boolean CheckArea(float posX) {
		boolean possible = false;
		
		if (posX > 0 + 20 && posX < game.worldWidth - 20) {
			possible = true;
		}
		
		return possible;
	}
	
}
