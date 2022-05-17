package GameModes;

/**
 * the freeze tag mode
 * @author Noah Pien and Kartik Joshi
 *
 */
public class FreezeMode extends GameModes{
	private boolean frozen = false;
	
	public boolean frozen() {
		//if intersected make them frozen make their velocity 0
	}
	
	public boolean unfrozen() {
		// if another runner touches them then they are unfrozen and their velocity is set to what the 
		// keyboard presses
	}
	
	public int tagged() {
		// if a player has been tagged 3 times then the runners all lose
	}
	
	public gameWon() {
		// if when the time runs out and there are still runners who are not frozen or a person has
		// not been tagged three times runner wins else taggers win
	}
}
