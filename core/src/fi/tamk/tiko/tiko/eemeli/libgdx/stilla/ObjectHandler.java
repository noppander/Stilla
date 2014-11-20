package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjectHandler {
	
	private String difficulty = "easy";
	
	private int currentScore;
	
	private boolean arcadeOn = false;
	
	private boolean gameIsOn;
	private boolean gameFinished;
	
	private int characterInUse;
	
	private int starCount = 0;
			
	private int contamination = 0;
	private int addedScore = 0;
			
	
	// Variables which are moving the droplet.
		private int velX;
		private int maxVel = 1;
		
		// Y position of droplet.
		private final int y = 50;
		
		// Lane presets. Equally devided four lanes.
		private final int CLOUD_LANE_1 = 5;
		private final int CLOUD_LANE_2 = 65;
		private final int CLOUD_LANE_3 = 125;
		private final int CLOUD_LANE_4 = 185;
		
		// Used when creating the object array.
		private int randomObject = 0;
		
		// The height where droplet is falling. The length of the level.
		private int height = 3000;
		private int lives;
		
		// The maximum count of the objects in randomized array.
		private int maxObjects = 1500;
		
		// Index used in printing and moving.
		private int objectIndex;
		
		// Array where randomized objects are stored.
		private Sprite[] object_array = new Sprite[maxObjects];
		private int[] object_type = new int [maxObjects];
		private int[] object_speed = new int [maxObjects];
		private Random random = new Random();
		
		// Used in checking which objects are on the screen.
		private boolean noNextVisibles, noMoreVisibles;
		private int nextVisible, lastVisible;
		
		// Defining cloud images.
		private Texture cloud_img;
		private Texture icecloud_img;
		private Texture thundercloud_img;
		private Texture myrkkypilvi_img;
		private Texture vesipilvi_img;
		
		// Defining droplet images.
		private Texture droplet_img;
		private Texture droplet2_img;
		private Texture droplet3_img;
		private Texture droplet4_img;
		private Texture blackdroplet_img;
		private Texture snowflake_img;
		
		// Defining object images.
		private Texture balloon_img;
		private Texture justwell_img;
		private Texture star_img;
		
		// Contamination bar and indicator.
		private Sprite contaminationBar_spr;
		private Sprite indicator_spr;
		
		// Defining background images
		private Texture level1Loop_img;
		private Texture level1Loop2_img;
		private Texture level2Loop_img;
		private Texture level3Loop_img;
		private Texture level1Background_img;
		private Texture level2Background_img;
		private Texture level3Background_img;
		
		// Defining the droplet sprites
		private Sprite droplet_spr;
		private Sprite blackdroplet_spr;
		private Sprite snowflake_spr;
		
		// Defining object sprites
		private Sprite star_spr;
		private Sprite blackscreen_spr;
		private Sprite whitescreen_spr;
		private Sprite greenscreen_spr;
		private Sprite bluescreen_spr;
		private Sprite redscreen_spr;
		private Sprite sparkle_spr;
		private Sprite justwell_spr;
		
		// Defining background sprites
		private Sprite levelXLoop_spr;
		private Sprite levelXLoop2_spr;
		private Sprite levelXBackground_spr;	
		
		// After hitting the balloon, changes to true.
		private boolean higher;
		private boolean goingHigher = false;
		
		// Checks if its possible to continue
		private boolean continuePossible = false;

		// Pointers
		private boolean pointerPressed = false;
		private int pointerX;
		private int pointerY;
		
		// Used in putObjectOnScreen(), printing objects to screen.
		private int counter;
		private int spawnCounter = 0;
		int idleTime = rand(20,50);
		
		int number = -2;
		
		private int endingHeight = 300;
			
		// Defines what sprite is used for droplet.
		private int dropletState = 0;
		private int screenState = 0;
		
		// Boolean which controls the movement of the sprite.
		private boolean movingPossible = true;
		
		// Controls the movement of the sprite when in snowflake form.
		boolean goRight = false;
		boolean goLeft = false;
		
		// Turn TRUE If you want to reset the points.
		static boolean resetThePoints = false;
		
		private int characterBonus;
		private int characterLives;
		private int heightSpeed;

		private int balloonCounter;
		
		private int frenzyCounter;
		private boolean frenzyGoingUp = true;
	
	/** 
	 * Filling the printable array with objects.
	 */
	public void fillObjectArray() {
		
		if (difficulty.equals("easy")) {
			easyObjects ();
		
		} else if (difficulty.equals("medium")) {
			mediumObjects ();
			
		} else if (difficulty.equals("hard")) {
			hardObjects ();
			
		} else if (difficulty.equals("arcade")) {
			hardObjects ();
			
		}
	}
	
	/**
	 * Sets easy objects.
	 */
	private void easyObjects () {

				// Counter for lightning, water and ice clouds
				int counter2 = 5;
				int fillClouds = 2;
				
				// Loop that runs as many times as there are objects.
				for (int i = 0; i < maxObjects; ++i) {
					
					// Filling the speed array.
					object_speed[i] = random.nextInt(6 - 3) +6 *-1;
						
					// Random number between 0-100
					randomObject = random.nextInt(100);
					
//					Debug.printInfo("MyGameCanvas", "fillObjectArray", 
//					"At the start of for loop, where the objects are randomized. " +
//					"The random number: " + randomObject, 1);
						
						// 40% Chance for normal clouds
						if (randomObject >= 0 && randomObject <= 40) {
							object_array[i] = new Sprite(cloud_img, 348/6, 38);
							
							// Defines the type of object in an array for collision checking.
							object_type[i] = 0;
						
						// 35% Chance for thunderclouds
						} else if (randomObject >= 41 && randomObject <= 73) {
							if (counter2 >= fillClouds) {
							object_array[i] = new Sprite(thundercloud_img, 405/5, 61);

							counter2 = 0;
							
							// Defines the type of object in an array for collision checking.
							object_type[i] = 2;
							
							} else {
								object_array[i] = new Sprite(cloud_img, 348/6, 38);
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 0;
							}
							counter2++;
							
						
						// 10% Chance for waterclouds
						} else if (randomObject >= 74 && randomObject <= 85) {
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(vesipilvi_img, 372/6, 57);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 4;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						
						// 10% Chance for stars
						} else if (randomObject >= 86 && randomObject <= 96) {
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(star_img, 192/6, 32);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 5;
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
							
						// 5% Chance for balloons
						} else if (randomObject >= 97 && randomObject <= 100) {
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(balloon_img, 182/7, 68);	

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 6;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						}
						
						object_array[i].setPosition(0, -500);
					}
				Gdx.app.log("ObjectHandler", "Easy objects DONE!");
	}
	
	/**
	 * Sets medium objects.
	 */
	private void mediumObjects () {
				
				// Counter for lightning, water and ice clouds
				int counter2 = 5;
				int fillClouds = 2;
				
				// Loop that runs as many times as there are objects.
				for (int i = 0; i < maxObjects; ++i) {
					
					// Filling the speed array.
					object_speed[i] = random.nextInt(6 - 3) +6 *-1;
					
					////System.out.println(object_speed[i]);
						
					// Random number between 0-100
					randomObject = random.nextInt(100);
					
//					Debug.printInfo("MyGameCanvas", "fillObjectArray", 
//					"At the start of for loop, where the objects are randomized. " +
//					"The random number: " + randomObject, 1);
						
						// 30% Chance for normal clouds
						if (randomObject >= 0 && randomObject <= 30) {
							object_array[i] = new Sprite(cloud_img, 348/6, 38);
							
							// Defines the type of object in an array for collision checking.
							object_type[i] = 0;
							
						// 30% Chance for poisonclouds
						} else if (randomObject >= 31 && randomObject <= 60) {
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(myrkkypilvi_img, 372/6, 42);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 1;
								
							} else {
								object_array[i] = new Sprite(cloud_img, 348/6, 38);
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 0;
								
							}
							counter2++;
						
						// 10% Chance for thunderclouds
						} else if (randomObject >= 61 && randomObject <= 73) {
							if (counter2 >= fillClouds) {
							object_array[i] = new Sprite(thundercloud_img, 405/5, 61);

							counter2 = 0;
							
							// Defines the type of object in an array for collision checking.
							object_type[i] = 2;
							
							} else {
								object_array[i] = new Sprite(cloud_img, 348/6, 38);
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 0;
							}
							counter2++;
						
						// 10% Chance for waterclouds
						} else if (randomObject >= 74 && randomObject <= 85) {
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(vesipilvi_img, 372/6, 57);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 4;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						
						// 10% Chance for stars
						} else if (randomObject >= 86 && randomObject <= 96) {
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(star_img, 192/6, 32);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 5;
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
							
						// 5% Chance for balloons
						} else if (randomObject >= 97 && randomObject <= 100) {
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(balloon_img, 182/7, 68);	

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 6;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						}
						
						object_array[i].setPosition(0, -500);
					}
		
	}
	
	/**
	 * Sets hard objects.
	 */
	private void hardObjects () {
				// Counter for poison clouds
				//int counter1 = 10;
				
				// Counter for lightning, water and ice clouds
				int counter2 = 5;
				int fillClouds = 2;
				
				// Counter for stars
				//int counter3 = 30;
				
				// Counter for balloons
				//int counter4 = 40;
				
				// Loop that runs as many times as there are objects.
				for (int i = 0; i < maxObjects; ++i) {
					
					// Makes the objects visible
					
					// Filling the speed array.
					object_speed[i] = random.nextInt(6 - 3) +6 *-1;
					
					////System.out.println(object_speed[i]);
						
					// Random number between 0-100
					randomObject = random.nextInt(100);
					
//					Debug.printInfo("MyGameCanvas", "fillObjectArray", 
//					"At the start of for loop, where the objects are randomized. " +
//					"The random number: " + randomObject, 1);
						
						// 30% Chance for normal clouds
						if (randomObject >= 0 && randomObject <= 30) {
							object_array[i] = new Sprite(cloud_img, 348/6, 38);
							
							// Defines the type of object in an array for collision checking.
							object_type[i] = 0;
							
						// 20% Chance for poisonclouds
						} else if (randomObject >= 31 && randomObject <= 50) {
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(myrkkypilvi_img, 372/6, 42);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 1;
								
							} else {
								object_array[i] = new Sprite(cloud_img, 348/6, 38);
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 0;
								
							}
							counter2++;
						
						// 10% Chance for thunderclouds
						} else if (randomObject >= 51 && randomObject <= 61) {
							if (counter2 >= fillClouds) {
							object_array[i] = new Sprite(thundercloud_img, 405/5, 61);

							counter2 = 0;
							
							// Defines the type of object in an array for collision checking.
							object_type[i] = 2;
							
							} else {
								object_array[i] = new Sprite(cloud_img, 348/6, 38);
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 0;
							}
							counter2++;
							
						// 10% Chance for iceclouds
						} else if (randomObject >= 62 && randomObject <= 73) {
							
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(icecloud_img, 355/5, 58);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 3;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						
						// 10% Chance for waterclouds
						} else if (randomObject >= 74 && randomObject <= 85) {
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(vesipilvi_img, 372/6, 57);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 4;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						
						// 10% Chance for stars
						} else if (randomObject >= 86 && randomObject <= 96) {
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(star_img, 192/6, 32);

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 5;
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
							
						// 5% Chance for balloons
						} else if (randomObject >= 97 && randomObject <= 100) {
							
							if (counter2 >= fillClouds) {
								object_array[i] = new Sprite(balloon_img, 182/7, 68);	

								counter2 = 0;
								
								// Defines the type of object in an array for collision checking.
								object_type[i] = 6;
								
								} else {
									object_array[i] = new Sprite(cloud_img, 348/6, 38);
									
									// Defines the type of object in an array for collision checking.
									object_type[i] = 0;
								}
								counter2++;
						}
						
						object_array[i].setPosition(0, -500);
					}
		
	}
	
	/**
	 * Puts objects randomly on one of the lanes.
	 */
	private void putObjectOnScreen() {
		
		// Counts time and assigns a lane to objects.
		if (idleTime <= spawnCounter && height > 400) {
			
			boolean assignOk = false;
			
			// Checks that there isn't two clouds spawning on the same lane.
			while (assignOk == false) {
				
				// 4 Cases and 4 possible lanes.
				switch (random.nextInt(4)) {
				
					case 0:
						if (number != 0) {
							object_array[objectIndex++].setPosition(CLOUD_LANE_1, -45);
							number = 0;
							assignOk = true;
							break;
						}
						break;
					case 1:
						if (number != 1) {
							object_array[objectIndex++].setPosition(CLOUD_LANE_2, -45);
							number = 1;
							assignOk = true;
							break;
						}
						break;
					case 2:
						if (number != 2) {
							object_array[objectIndex++].setPosition(CLOUD_LANE_3, -45);
							number = 2;
							assignOk = true;
							break;
						}
						break;
					case 3:
						if (number != 3) {
							object_array[objectIndex++].setPosition(CLOUD_LANE_4, -45);
							number = 3;
							assignOk = true;
							break;
						}
						break;	
				}
				Gdx.app.log("ObjectHandler;", "Object spawned");

			}
			
			// Resets the counter.
			spawnCounter = 0;
			
//			// DEFINES THE SPAWNING SPEED.
//			if (difficulty.equals("easy")) {
//				
//				if (height > 1500) {
//					idleTime = rand(10,30);
//				} else if (height < 1500) {
//					idleTime = rand(8,25);
//				}
//				
//			} else if (difficulty.equals("medium")) {
//				
//				if (height > 2500) {
//					idleTime = rand(10,30);
//				} else if (height > 1000) {
//					idleTime = rand(8,25);
//				} else if (height < 1000) {
//					idleTime = rand(5,15);
//				}
//				
//			} else if (difficulty.equals("hard")) {
//				
//				if (height > 4000) {
//					idleTime = rand(10,30);
//				} else if (height > 2000) {
//					idleTime = rand(8,25);
//				} else if (height < 2000) {
//					idleTime = rand(5,15);
//				}
//				
//			} else if (difficulty.equals("arcade")) {
//				idleTime = 15;
//				
//				if (currentScore > 1000 && currentScore < 2000 ) {
//					idleTime = idleTime - 1;
//				} else if (currentScore > 2000 && currentScore < 3000 ) {
//					idleTime = idleTime - 2;
//				} else if (currentScore > 3000 && currentScore < 4000 ) {
//					idleTime = idleTime - 3;
//				} else if (currentScore > 4000 && currentScore < 5000 ) {
//					idleTime = idleTime - 4;
//				} else if (currentScore > 5000 && currentScore < 6000 ) {
//					idleTime = idleTime - 5;
//				} else if (currentScore > 6000 && currentScore < 7000 ) {
//					idleTime = idleTime - 6;
//				} else if (currentScore > 7000 && currentScore < 8000 ) {
//					idleTime = idleTime - 7;
//				} else if (currentScore > 8000 && currentScore < 9000 ) {
//					idleTime = idleTime - 8;
//				} else if (currentScore > 9000) {
//					idleTime = idleTime - 9;
//				}
//		
//			}
//				
//			if (balloonCounter > 0) {
//				if (arcadeOn) {
//					idleTime -= 5;
//				} else {
//					idleTime = idleTime/2;
//				}
//			}

						
//			if (object_array[objectIndex + 1]. == false) {
//				object_array[objectIndex + 1].setVisible(true);
//			}

			// Debug
//			Debug.printInfo("Assign ok: " + assignOk, 1);
//			Debug.printInfo("Object index: " + objectIndex, 1);
		}
		
		// Adds 1 to counter
		spawnCounter++;
		
		// Debug
		//Gdx.app.log("ObjectHandler", "Spawncounter: " + spawnCounter);
//		Debug.printInfo("Idle time" + idleTime, 1);
//		Debug.printInfo("Spawn counter" + spawnCounter, 1);
	}
	
	 /**
     * Updates information about visible objects.
     */
    private void updateVisibles() {
        
        // check if next visible object has entered the screen
        if (!noNextVisibles && object_array[nextVisible].getY() > -50) {

            if (++nextVisible == maxObjects) {

                // it was the last object
                noNextVisibles = true;
            }
        }
        
        // check if last visible object has left the screen
        if (!noMoreVisibles && object_array[lastVisible].getY() +
        		object_array[lastVisible].getHeight() > 360) {
            
            if (++lastVisible == maxObjects) {

                // it was the last object
                noMoreVisibles = true;
            }
        }
    }
    
    /**
	 * Draws the sprites on the canvas
	 */
	public void drawSprites(SpriteBatch batch) {


		// index through all visible objects
		for(int i = lastVisible; i < nextVisible; i++) {

			// Temp float
			float temp = object_array[i].getY();
			
			// Move object
			//object_array[i].setCenterY(temp += object_speed[i] + heightSpeed);
			
			// Move object with speed of: 1
			object_array[i].setPosition(object_array[i].getX(), object_array[i].getY() + 0.8f);
			
			//Gdx.app.log("ObjectHandler", "Objects position with ID: " + i + " And position: " + temp);
			
			object_array[i].draw(batch);
		
			
		}
		
		// Painting contamination bar and indicator
//		contaminationBar_spr.paint(g);
//		indicator_spr.paint(g);
		
		// Sets the color to black and draws the GUI
//		g.setColor(0,0,0);
		
		// If we are not in arcade mode, show height
//		if (Midlet.arcadeOn == false) {
//			g.drawString("Height: " + height, 120, 20, Graphics.HCENTER | Graphics.TOP);
//		}
		
		
//		g.drawString("Lives: " + lives, getWidth()/2 + 5, getHeight(), Graphics.HCENTER | Graphics.BOTTOM);
//		g.drawString("Score: " + Midlet.score, 0, getHeight(), Graphics.LEFT | Graphics.BOTTOM);
//		g.drawString("Stars: " + Midlet.starCount, getWidth(), getHeight(), Graphics.RIGHT | Graphics.BOTTOM);
//		
//		if (height < endingHeight) {
//		justwell_spr.paint(g);
//		}
		
//		if (balloonCounter > 0) {
//			g.setColor(254,157,0);
//			
//			Font font2 = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
//		
//			g.setFont(font2);
//			
//			if (frenzyGoingUp == true) {
//				g.drawString("FRENZYYYY!!!!!", 120, 280, Graphics.HCENTER | Graphics.BOTTOM);
//				frenzyCounter++;
//				
//				if (frenzyCounter == 20) {
//					frenzyGoingUp = false;
//				}
//			} else if (frenzyGoingUp == false) {
//				
//				frenzyCounter--;
//				
//				if (frenzyCounter == 0) {
//					frenzyGoingUp = true;
//				}
//			} 
//					
//			balloonCounter--;
//			g.setColor(255, 255, 255);
//		}
		
		
	}
	
    /**
	 * Resets the values used in the game. This to start a new game
	 */
	public void reset() {
		
//		Debug.printInfo("Reset", 0);
		
		gameIsOn = true;
		gameFinished = false;
		
		// Checks and uses the right droplet Image.
		switch(characterInUse) {
		
			// Stilla's bonus and lives and image definer. If characterinuse == 1
			case 1:
				characterBonus = 1;
				characterLives = 3;
				droplet_spr = new Sprite(droplet_img, 96/4, 48);
				break;
				
			// Stella's bonus and lives and image definer. If characterinuse == 2
			case 2:
				characterBonus = 3;
				characterLives = 1;
				droplet_spr = new Sprite(droplet2_img, 96/4, 48);
				break;
				
			// Polium's bonus and lives and image definer. If characterinuse == 3
			case 3:
				characterBonus = 2;
				characterLives = 3;
				droplet_spr = new Sprite(droplet3_img, 96/4, 48);
				break;
				
			// Plum's bonus and lives and image definer. If characterinuse == 4
			case 4:
				characterBonus = -2;
				characterLives = 2;
				droplet_spr = new Sprite(droplet4_img, 96/4, 48);
				break;	
		}
		
		
		// Sets the things that are depending what level is running
		if (difficulty.equals("easy")) {
			
			maxObjects = 500;
			height = 3000;
			lives = 2 + characterLives;
			levelXLoop_spr = new Sprite(level1Loop_img);
			levelXLoop2_spr = new Sprite(level1Loop2_img);
			levelXBackground_spr = new Sprite(level1Background_img);
		
		} else if (difficulty.equals("medium")) {
			
			maxObjects = 1000;
			height = 4500;
			lives = 1 + characterLives;
			levelXLoop_spr = new Sprite(level2Loop_img);
			levelXLoop2_spr = new Sprite(level1Loop2_img);
			levelXBackground_spr = new Sprite(level2Background_img);
			
		} else if (difficulty.equals("hard")) {
			
			maxObjects = 1500;
			height = 6000;
			lives = 0 + characterLives;
			levelXLoop_spr = new Sprite(level3Loop_img);
			// Second loop sprite creating parallax
			levelXLoop2_spr = new Sprite(level1Loop2_img);
			levelXBackground_spr = new Sprite(level3Background_img);
			
		} else if (difficulty.equals("arcade")) {
			
			maxObjects = 1500;
			height = 3000;
			lives = 1;
			levelXLoop_spr = new Sprite(level1Loop_img);
			levelXLoop2_spr = new Sprite(level1Loop2_img);
			levelXBackground_spr = new Sprite(level1Background_img);
			
		}
		
		// Fills the objects again.
		fillObjectArray();
		
		// Resets the counter.
		objectIndex = 0;
		dropletState = 0;
		
		/*
		 * So, a little bit about special skills of the droplets
		 * 
		 * Stilla: Normal droplet, nothing special.
		 * Stella: Gets bigger score from the distance but dies one shot!
		 * Polium: Gets little bit bigger score from falling. Gets points from poisonclouds.
		 * Plum: Doesn't get much points from dropping, but gets slown by the white clouds a lot.
		 * 
		 * 
		 */
		
		// RESETS THE NEXT AND LAST VISIBLE WHICH MAKES THE CLOUDS TO SPAWN
		nextVisible = 0;
		lastVisible = 0;
		
		//Resets Starcount
		starCount = 0;
		
		// Resets contamination
		contamination = 0;
		addedScore = 0;
		
//		justwell_spr.setPosition(0, 280);
//		levelXBackground_spr.setPosition(0, 320);
//		justwell_spr.setPosition(0, 280);

		currentScore = 0;
	}
	
		public static int rand(int start, int end) {
		
		Random randomNum = new Random();
		Date date = new Date();
		randomNum.setSeed(date.getTime());
		return randomNum.nextInt(end+1-start) + start;
	}
		
		/**
		 * Initializes the sprites and puts them to their default place
		 */
		public void initializeSprites() {
					
				myrkkypilvi_img = new Texture(Gdx.files.internal("Clouds/myrkkypilvi.png"));
				
				greenscreen_spr = new Sprite(new Texture(Gdx.files.internal("Objects/greenscreen92x92.png")));

				
				myrkkypilvi_img = new Texture(Gdx.files.internal("Clouds/myrkkypilvi.png"));
				icecloud_img = new Texture(Gdx.files.internal("Clouds/icecloud.png"));
				
				whitescreen_spr = new Sprite(new Texture(Gdx.files.internal("Objects/whitescreen92x92.png")));
				greenscreen_spr = new Sprite(new Texture(Gdx.files.internal("Objects/greenscreen92x92.png")));
				
//					whitescreen_spr.defineReferencePixel(whitescreen_spr.getWidth()/2, whitescreen_spr.getHeight()/2);
//					greenscreen_spr.defineReferencePixel(greenscreen_spr.getWidth()/2, greenscreen_spr.getHeight()/2);
				
				
				// Loading clouds.
				cloud_img = new Texture(Gdx.files.internal("Clouds/normalcloud.png"));
				thundercloud_img = new Texture(Gdx.files.internal("Clouds/thundercloud.png"));
				vesipilvi_img = new Texture(Gdx.files.internal("Clouds/vesipilvi.png"));
				blackdroplet_img = new Texture(Gdx.files.internal("Objects/blackdroplet40x48.png"));
				snowflake_img = new Texture(Gdx.files.internal("Objects/snowflake32x32.png"));
				
				// Loading object images.
				balloon_img = new Texture(Gdx.files.internal("Objects/balloon.png"));
				star_img = new Texture(Gdx.files.internal("Objects/star.png"));
				
				// all 92x92
				blackscreen_spr = new Sprite(new Texture(Gdx.files.internal("Objects/blackscreen92x92.png")));
				bluescreen_spr = new Sprite(new Texture(Gdx.files.internal("Objects/bluescreen92x92.png")));
				redscreen_spr = new Sprite(new Texture(Gdx.files.internal("Objects/redscreen92x92.png")));
				sparkle_spr = new Sprite(new Texture(Gdx.files.internal("Objects/yellowscreen92x92.png")));
				
				justwell_img = new Texture(Gdx.files.internal("Objects/justwell.png"));
				
				// Loading Backgrounds

				// Contamination bar and indicator
				//contaminationBar_spr = new Sprite (new Texture(Gdx.files.internal("Objects/contaminationBar.png")));
				//indicator_spr = new Sprite (new Texture(Gdx.files.internal("Objects/pointer.png")));
						
			
			// Defines the sprites and animation frames

			// For droplets
			blackdroplet_spr = new Sprite(blackdroplet_img, 40, 48);
			snowflake_spr = new Sprite(snowflake_img, 32, 32);
			
			// For objects
			star_spr = new Sprite(star_img, 192/6, 32);	
			
			// For others
			justwell_spr = new Sprite(justwell_img);
			
			// DEFINE reference pixels if needed
			
//			droplet_spr.defineReferencePixel(droplet_spr.getWidth()/2, 38);
//			
//			blackscreen_spr.defineReferencePixel(blackscreen_spr.getWidth()/2, blackscreen_spr.getHeight()/2);
//			
//			bluescreen_spr.defineReferencePixel(bluescreen_spr.getWidth()/2, bluescreen_spr.getHeight()/2);
//			redscreen_spr.defineReferencePixel(redscreen_spr.getWidth()/2, redscreen_spr.getHeight()/2);
//			sparkle_spr.defineReferencePixel(sparkle_spr.getWidth()/2, sparkle_spr.getHeight()/2);
//			blackdroplet_spr.defineReferencePixel(blackdroplet_spr.getWidth()/2,blackdroplet_spr.getHeight()/2);
//			snowflake_spr.defineReferencePixel(snowflake_spr.getWidth()/2,snowflake_spr.getHeight()/2);
//			
//			// Reference pixel of contamination bar and indicator.
//			contaminationBar_spr.defineReferencePixel(contaminationBar_spr.getWidth()/2, contaminationBar_spr.getHeight()/2);
//			indicator_spr.defineReferencePixel(indicator_spr.getWidth()/2, indicator_spr.getHeight()/2);
			
			// Position of contamination bar and indicator
//			contaminationBar_spr.setRefPixelPosition(120, 10);
//			indicator_spr.setRefPixelPosition(120, 10);
//			
//			// Set position if needed.
//			justwell_spr.setPosition(0, 280);
			
			//System.out.println("GAME: Sprites initialized.");
		}
		
		public void RunTheGame() {
			
			// Checks the objects on the screen
			updateVisibles();
			
			// Places the sprites on the canvas
			putObjectOnScreen();
		
		}
}
