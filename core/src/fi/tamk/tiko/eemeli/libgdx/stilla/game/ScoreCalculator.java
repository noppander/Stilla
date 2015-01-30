package fi.tamk.tiko.eemeli.libgdx.stilla.game;

/**
 * Responsible of calculating the score of the player.
 *
 * @author Eemeli Jokiperä
 * @version 2014.1215
 * @since 1.7
 *
 */
public class ScoreCalculator {
	
	public int Score;
	public int totalScore;
	
	public static ScoreCalculator instance;
	
	/**
	 * Static constructor.
	 * @return
	 */
	public static ScoreCalculator getInstance() {
		if (instance == null) {
			instance = new ScoreCalculator();
		}
		
		return instance;
	}
	
	/**
	 * Score getter.
	 * @return
	 */
	public int GetScore() {
		return Score;
	}
	
	/**
	 * Score adder.
	 * @param value
	 */
	public void AddScore(int value){
		Score += value;
	}
	
	/**
	 * Set the total score.
	 * @param value
	 */
	public void setTotalScore (int value) {
		totalScore = value;
	}
	
	/**
	 * get the total score.
	 * @return
	 */
	public int getTotalScore () {
		return totalScore;
	}
	
	/**
	 * Update the totalscore
	 */
	public void updateTotalScore() {
		totalScore += Score;
	}
	
	/**
	 * Update totalscore with value.
	 * @param value
	 */
	public void updateTotalScore(int value) {
		totalScore = value;
	}
	
	/**
	 * reset the temporary score.
	 */
	public void resetScore() {
		Score = 0;
	}
	
}
