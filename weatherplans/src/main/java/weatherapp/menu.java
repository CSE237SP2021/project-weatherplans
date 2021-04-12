package weatherapp;

import java.util.ArrayList;

import org.json.JSONObject;

import datadisplay.Block;
import datadisplay.ParentBlock;
import datadisplay.TextBlock;
public class menu {
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
		

//		datadisplay.MultiBlockPrinter.printBlocks(boxes);
		datadisplay.MultiBlockPrinter.example();

	}
	


}
