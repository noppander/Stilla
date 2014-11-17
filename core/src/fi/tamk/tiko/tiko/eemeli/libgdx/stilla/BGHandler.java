package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BGHandler {
	
		// Creating background sprites.
		private Sprite background;
		private Sprite background2;
		
		// The positions for the looping background
		private float BG_Pos1;
		private float BG_Pos2;
		
		// The positions for the second looping background
		private float BG2_Pos1;
		private float BG2_Pos2;
		
		private String difficulty = "medium";
		
		// Constructor.
		public BGHandler (float viewportWidth, float viewportHeight) {
			
			// Loading the image of the background to the memory depending of the level
			
			// Loads the images from "res" folder
			// Loading objects in to individual levels, so they are not taking space for nothing
			if (difficulty.equals("easy")) {
				
				background = new Sprite(new Texture(Gdx.files.internal("Backgrounds/level1Loop.png")));
				
			} else if (difficulty.equals("medium")) {
				background = new Sprite(new Texture(Gdx.files.internal("Backgrounds/level2Loop.png")));
				
				
			} else if (difficulty.equals("hard") || difficulty.equals("arcade")) {
				background = new Sprite(new Texture(Gdx.files.internal("Backgrounds/level3Loop.png")));
				
			}
			
			background.setSize(viewportWidth, viewportHeight * 2);
			
			System.out.println("Taustan posiitio: " + background.getX());
			
			BG_Pos1 = -background.getHeight();
			BG_Pos2 = 0;
			
			// Loading the image of the background2 to the memory.
			background2 = new Sprite(new Texture(Gdx.files.internal("Backgrounds/level1Loop2.png")));
			background2.setSize(viewportWidth, viewportHeight * 2);
			
			System.out.println("Taustan posiitio: " + background2.getX());
			
			BG2_Pos1 = -background2.getHeight();
			BG2_Pos2 = 0;
			
			
		}
		
		public void moveBackground(float speed){
			BG_Pos1 += speed;
			BG_Pos2 += speed;
			
			BG2_Pos1 += speed / 2;
			BG2_Pos2 += speed / 2;
			
		}
		
		public void drawBackground(SpriteBatch batch) {
			
			if (BG_Pos1 > 0) {
				BG_Pos1 = -background.getHeight();
			}
			
			if (BG_Pos2 > background.getHeight()) {
				BG_Pos2 = 0;
			}
			
			if (BG2_Pos1 > 0) {
				BG2_Pos1 = -background2.getHeight();
			}
			
			if (BG2_Pos2 > background2.getHeight()) {
				BG2_Pos2 = 0;
			}
			
			// Positioning and drawing of the first bg.
			background.setPosition(0, BG_Pos1);
			background.draw(batch);

			background.setPosition(0, BG_Pos2);
			background.draw(batch);
			
			// Positioning and drawing of the second bg.
			background2.setPosition(0, BG2_Pos1);
			background2.draw(batch);	

			background2.setPosition(0, BG2_Pos2);
			background2.draw(batch);

		}
}
