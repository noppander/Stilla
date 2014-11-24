package fi.tamk.tiko.tiko.eemeli.libgdx.stilla;

import java.util.Date;
import java.util.Random;

public class Tools {
	
	
	public int rand(int start, int end) {
		
		Random randomNum = new Random();
		Date date = new Date();
		randomNum.setSeed(date.getTime());
		return randomNum.nextInt(end+1-start) + start;
	}
}
