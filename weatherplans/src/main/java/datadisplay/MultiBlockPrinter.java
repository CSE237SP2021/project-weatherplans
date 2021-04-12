package datadisplay;

import java.util.ArrayList;

/**
 * Prints multiple blocks side by side
 * @author evan
 *
 */
public class MultiBlockPrinter {

	/**
	 * Print each block side by side
	 * @param boxes
	 */
	public static void printBlocks(ArrayList<Block> boxes) {
		// Print the first block normally
		System.out.println(boxes.get(0));
		int numLinesUp = boxes.get(0).getHeight();
		int numColRight = boxes.get(0).getWidth();
		
		// Then the print the rest side by side
		for (int i = 1; i < boxes.size(); i++) {
			Block box = boxes.get(i);
			String[] displayBlock = box.getDisplayBlock();
			positionBlock(displayBlock, numLinesUp, numColRight);
			printBlock(displayBlock);
			
			numLinesUp = box.getHeight();
			numColRight += box.getWidth();
		}
		
		// Make sure to move the cursor down as far down as the largest height
		int maxHeight = boxes.get(0).getHeight();
		for (Block box : boxes) {
			if (box.getHeight() > maxHeight) {
				maxHeight = box.getHeight();
			}
		}
		int toMove = maxHeight - numLinesUp;
		System.out.println(downNumLines(toMove));
	}
	
	private static void printBlock(String[] displayBlock) {
		for (String line : displayBlock) {
			System.out.println(line);
		}
	}
	
	/**
	 * Print a block at a specific offset by prepending ANSI escape codes.
	 * @param block
	 * @param numLinesUp
	 * @param numColRight
	 */
	private static void positionBlock(String[] block, int numLinesUp, int numColRight) {
		// Move up to the correct position
		block[0] = upNumLines(numLinesUp) + rightNumCol(numColRight) + block[0];
		
		// Move to the right
		for (int i = 1; i < block.length; i++) {
			block[i] = rightNumCol(numColRight) + block[i];
		}
	}
	
	/* These functions create ANSI escape codes */
	private static String downNumLines(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "B";
	}
	private static String upNumLines(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "A";
	}
	private static String rightNumCol(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "C";
	}
	
	/**
	 * Example of expected usage. 
	 * 
	 * It looks a bit daunting, but notice the repetition. Also, everything but the last line is
	 * for the purpose of initializing an ArrayList<Box>.
	 * 
	 */
	public static void example() {
		// Initialize the data we want to display
		ArrayList<String> mondayTitle = new ArrayList<String>();
		mondayTitle.add("Monday");
		ArrayList<String> mondayContent = new ArrayList<String>();
		mondayContent.add("Temp: 80°F");
		mondayContent.add("Humidity: 90%");
		mondayContent.add("Chance of Precip: 30%");
		
		ArrayList<String> tuesdayTitle = new ArrayList<String>();
		tuesdayTitle.add("Tuesday");
		ArrayList<String> tuesdayContent = new ArrayList<String>();
		tuesdayContent.add("Temp: 0°F");
		tuesdayContent.add("Humidity: 100%");
		tuesdayContent.add("Chance of Precip: 99%");
		
		ArrayList<String> wednesdayTitle = new ArrayList<String>();
		wednesdayTitle.add("wednesday");
		ArrayList<String> wednesdayContent = new ArrayList<String>();
		wednesdayContent.add("Temp: -20°F");
		wednesdayContent.add("Humidity: 0%");
		wednesdayContent.add("Chance of Precip: 22%");
		
		// Now prep them to become columns
		ArrayList<Block> mondayChildren = new ArrayList<Block>();
		mondayChildren.add(new TextBlock(mondayTitle, 1, 1, true));
		mondayChildren.add(new TextBlock(mondayContent, 1, 1, true));
		
		ArrayList<Block> tuesdayChildren = new ArrayList<Block>();
		tuesdayChildren.add(new TextBlock(tuesdayTitle, 1, 1, true));
		tuesdayChildren.add(new TextBlock(tuesdayContent, 1, 1, true));
		
		ArrayList<Block> wednesdayChildren = new ArrayList<Block>();
		wednesdayChildren.add(new TextBlock(wednesdayTitle, 1, 1, true));
		wednesdayChildren.add(new TextBlock(wednesdayContent, 1, 1, true));
		
		// Create the columns and group them together
		ParentBlock mondayColumn = new ParentBlock(mondayChildren, 0, 0);
		ParentBlock tuesdayColumn = new ParentBlock(tuesdayChildren, 0, 0);
		ParentBlock wednesdayColumn = new ParentBlock(wednesdayChildren, 0, 0);
		
		ArrayList<Block> columns = new ArrayList<Block>();
		columns.add(mondayColumn);
		columns.add(tuesdayColumn);
		columns.add(wednesdayColumn);
		
		// And print it!!!
		printBlocks(columns);
	}
	
	public void example2() {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("Hello");
		lines.add("bruh");
		lines.add("Yes sir!");
		
		ArrayList<String> lines2 = new ArrayList<String>();
		lines2.add("Hello");
		lines2.add("bruh");
		lines2.add("Yes sir!!!!!!!!!!!!");
		
		ArrayList<String> lines3 = new ArrayList<String>();
		lines3.add("Hello");
		lines3.add("bruh");
		lines3.add("Yes!");
		
		ArrayList<String> lines4 = new ArrayList<String>();
		lines4.add("Hello");

		
		ArrayList<Block> children = new ArrayList<Block>();
		TextBlock tb = new TextBlock(lines, 1, 1, true);
		TextBlock tb2 = new TextBlock(lines2, 1, 1, true);
		children.add(tb);
		children.add(tb2);
		
		ArrayList<Block> children2 = new ArrayList<Block>();
		TextBlock tb3 = new TextBlock(lines3, 1, 1, true);
		TextBlock tb4 = new TextBlock(lines4, 1, 1, true);
		children2.add(tb3);
		children2.add(tb4);
		
		
		ParentBlock parent = new ParentBlock(children, 0, 0);
		ParentBlock parent2 = new ParentBlock(children2, 0, 0);
		
		//
		ArrayList<Block> boxes = new ArrayList<>();
		boxes.add(parent);
		boxes.add(parent2);
		boxes.add(parent);
		

		printBlocks(boxes);
	}
	

	public static void main(String[] args) {
		// Initialization
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("Hello");
		lines.add("bruh");
		lines.add("Yes sir!");
		
		ArrayList<String> lines2 = new ArrayList<String>();
		lines2.add("Hello");
		lines2.add("bruh");
		lines2.add("Yes sir!!!!!!!!!!!!");
		
		ArrayList<String> lines3 = new ArrayList<String>();
		lines3.add("Hello");
		lines3.add("bruh");
		lines3.add("Yes!");
		
		ArrayList<String> lines4 = new ArrayList<String>();
		lines4.add("Hello");

		
		ArrayList<Block> children = new ArrayList<Block>();
		TextBlock tb = new TextBlock(lines, 1, 1, true);
		TextBlock tb2 = new TextBlock(lines2, 1, 1, true);
		children.add(tb);
		children.add(tb2);
		
		ArrayList<Block> children2 = new ArrayList<Block>();
		TextBlock tb3 = new TextBlock(lines3, 1, 1, true);
		TextBlock tb4 = new TextBlock(lines4, 1, 1, true);
		children2.add(tb3);
		children2.add(tb4);
		
		
		ParentBlock parent = new ParentBlock(children, 0, 0);
		ParentBlock parent2 = new ParentBlock(children2, 0, 0);
		
		ArrayList<Block> boxes = new ArrayList<>();
		boxes.add(parent);
		boxes.add(parent2);
		boxes.add(parent);
		
		// Actual printing
		printBlocks(boxes);
	}

}
