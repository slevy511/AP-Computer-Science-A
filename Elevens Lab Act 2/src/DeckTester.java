import java.util.List;
import java.util.ArrayList;


/**
 * This is a class that tests the Deck class.
 */

public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
		String[] ranks = {"A", "B", "C"};
		String[] suits = {"Giraffes", "Lions", "Dawms"};
		int[] values = {2, 1, 3};
		
		
		
		Deck deck1 = new Deck(ranks, suits, values);
		
		//Deck deck1 = new Deck(ranks, suits, values);
		
		//System.out.println("card 1:");
		//System.out.println(cards.get(1));
		System.out.println("START");
		System.out.println(deck1);
		System.out.println("END");
		
		
	/*	
		ArrayList<Integer> glub = new ArrayList<Integer>();
		glub.add(1);
		glub.add(3);
		glub.add(69);
		System.out.println(glub.get(2));
	*/	
		
	}
	
	
}
