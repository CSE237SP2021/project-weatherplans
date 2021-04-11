package datadisplay;

import java.util.ArrayList;

public class Printer {
	
	/**
	 * Prints a bunch of boxes side by side
	 * @param boxes
	 */
	public static void printBoxes(ArrayList<Box> boxes) {
		String MOVE_UP_3_LINES = "\033[3A";
		String MOVE_RIGHT_11_COLUMNS = "\033[11C";
		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\033[3AHello");
	}

}
