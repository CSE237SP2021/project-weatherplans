package weatherapp;

import java.util.ArrayList;

import org.json.JSONObject;

import datadisplay.Box;
import datadisplay.ParentBox;
import datadisplay.TextBox;
public class menu {
	
	/**
	 * Print each block side by side
	 * @param boxes
	 */
	public static void printBoxes(ArrayList<Box> boxes) {
		// Print the first block normally
		System.out.println(boxes.get(0));
		int numLinesUp = boxes.get(0).getHeight();
		int numColRight = boxes.get(0).getWidth();
		
		// Then the print the rest side by side
		for (int i = 1; i < boxes.size(); i++) {
			Box box = boxes.get(i);
			String[] displayBlock = box.getDisplayBlock();
			positionBlock(displayBlock, numLinesUp, numColRight);
			printBox(displayBlock);
			
			numLinesUp = box.getHeight();
			numColRight += box.getWidth();
		}
		
		// Make sure to move the cursor down as far down as the largest height
		int maxHeight = boxes.get(0).getHeight();
		for (Box box : boxes) {
			if (box.getHeight() > maxHeight) {
				maxHeight = box.getHeight();
			}
		}
		int toMove = maxHeight - numLinesUp;
		System.out.println(downNumLines(toMove));
	}
	
	public static void printBox(String[] displayBlock) {
		for (String line : displayBlock) {
			System.out.println(line);
		}
	}
	
	public static void positionBlock(String[] block, int numLinesUp, int numColRight) {
		// Move up to the correct position
		block[0] = upNumLines(numLinesUp) + rightNumCol(numColRight) + block[0];
		
		// Move to the right
		for (int i = 1; i < block.length; i++) {
			block[i] = rightNumCol(numColRight) + block[i];
		}
	}
	
	public static String downNumLines(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "B";
	}
	
	public static String upNumLines(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "A";
	}
	
	public static String rightNumCol(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "C";
	}
	
	public static void main(String[] args) {

//		ArgumentParser parser = new ArgumentParser();
//		ParseResults results = parser.parse(args);
//		WeatherApi wapi = new WeatherApi();
//		JSONObject data = null;
//		switch (results.locType) {
//		case INVALID:
//			System.err.println("No arguments found"); //maybe change this case in the future in case of quick weather forecast retrieval
//      		return;
//		case CITYNAME:
//			data = wapi.fetchCurrentWeatherByCityName(results.getArgAt(0));
//			break;
//		case CITYID:
//			data = wapi.fetchCurrentWeatherByCityId(results.getArgAt(0));
//			break;
//		case COORDINATES:
//			data = wapi.fetchCurrentWeatherByCoordinates(results.getCoordAt(0),results.getCoordAt(1));
//			break;
//		case ZIPCODE:
//			data = wapi.fetchCurrentWeatherByZipcode(results.getArgAt(0));
//			break;
//		default:
//			break;
//		}
//		
//		
//		System.out.println(data.toString(4));
		
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

		
		ArrayList<Box> children = new ArrayList<Box>();
		TextBox tb = new TextBox(lines, 1, 1, true);
		TextBox tb2 = new TextBox(lines2, 1, 1, true);
		children.add(tb);
		children.add(tb2);
		
		ArrayList<Box> children2 = new ArrayList<Box>();
		TextBox tb3 = new TextBox(lines3, 1, 1, true);
		TextBox tb4 = new TextBox(lines4, 1, 1, true);
		children2.add(tb3);
		children2.add(tb4);
		
		
		ParentBox parent = new ParentBox(children, 0, 0);
		ParentBox parent2 = new ParentBox(children2, 0, 0);
		
		//
		ArrayList<Box> boxes = new ArrayList<>();
		boxes.add(parent);
		boxes.add(parent2);
		boxes.add(parent);
		

		printBoxes(boxes);

	}
	


}
