package fi.tamk.tiko.eemeli.libgdx.stilla.game;

/**
 * Class that is responsible for calculating the height differences.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class HeightCalculator {
	
	public float height;
	
	public static HeightCalculator instance;
	
	/**
	 * Instance getter.
	 * @return
	 */
	public static HeightCalculator getInstance(){
		if (instance == null) {
			instance = new HeightCalculator();
		}
		
		return instance;
	}
	
	/**
	 * Height setter.
	 * @param d
	 */
	public void setHeight(String d) {
		
		if (d.equals("EASY")) {
			height = 3000;
		} else if (d.equals("MEDIUM")){
			height = 3500;
		} else if (d.equals("HARD")) {
			height = 4000;
		}
		
		
	}
	
	/**
	 * Height getter
	 * @return
	 */
	public float getHeight() {
		
		return height;
	}
	
	/**
	 * Method that changes height with given argument
	 * @param value
	 */
	public void changeHeight(float value) {
		height += value;
	}
	
}
