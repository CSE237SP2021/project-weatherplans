package weatherapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import datadisplay.Block;
import datadisplay.MultiBlockPrinter;
import datadisplay.ParentBlock;
import datadisplay.TextBlock;
import weatherapp.JsonResults.weatherIndex;
import weatherapp.JsonResults.mainIndex;

public class Menu {
	public static void printResults(JsonResults results, boolean isForecast) {
		ArrayList<Block> columns = new ArrayList<Block>();
		
		// Populate columns
		if (isForecast) {
			ArrayList<ArrayList<String>> multiTextContent = results.getForecastMainAsListOfLists();
			for (ArrayList<String> textContent : multiTextContent) {
				ParentBlock column = constructParentBlock("Wednesday", textContent);
				columns.add(column);
			}
			
		} else {
			ArrayList<String> textContent = results.getMainAsStringList();
			ParentBlock column = constructParentBlock("Wednesday", textContent);
			columns.add(column);
		}
		
		// And print the columns
		MultiBlockPrinter.printBlocks(columns);
	}
	
	public static ParentBlock constructParentBlock(String titleText, ArrayList<String> textContent) {
		ArrayList<String> title = new ArrayList<>();
		title.add(titleText);
		
		ArrayList<Block> children = new ArrayList<Block>();
		children.add(new TextBlock(title, 1, 1, true));
		children.add(new TextBlock(textContent, 1, 1, true));
		
		ParentBlock column = new ParentBlock(children, 0, 0);
		return column;
	}
	
	public static void main(String[] args) {

		ArgumentParser parser = new ArgumentParser();
		ParseResults results = parser.parse(args);
		WeatherApi wapi = new WeatherApi();
		JSONObject data = null;
		if(results.containsFlag(Flag.LENGTH)) {
			switch (results.locType) {
			case EMPTY:
				System.out.println("No location found -> running default location");
				data = wapi.fetchForecastByCityName("St. Louis");
				break;
			case CITYNAME:
				data = wapi.fetchForecastByCityName(results.getArgAt(0));
				break;
			case CITYID:
				data = wapi.fetchForecastByCityId(results.getArgAt(0));
				break;
			case COORDINATES:
				data = wapi.fetchForecastByCoordinates(results.getCoordAt(0),results.getCoordAt(1));
				break;
			case ZIPCODE:
				data = wapi.fetchForecastByZipcode(results.getArgAt(0));
				break;
			default:
				ArgumentParser.usageMessage();
				return;
			}
			
			// Convert results to array list of strings
			JsonResults printResult = new JsonResults(data);
			System.out.println(printResult.toString());

		}
		else {
			switch (results.locType) {
			case EMPTY:
				System.out.println("No arguments found -> running default location and forecast length");
				data = wapi.fetchCurrentWeatherByCityName("St. Louis");
				break;
			case CITYNAME:
				data = wapi.fetchCurrentWeatherByCityName(results.getArgAt(0));
				break;
			case CITYID:
				data = wapi.fetchCurrentWeatherByCityId(results.getArgAt(0));
				break;
			case COORDINATES:
				data = wapi.fetchCurrentWeatherByCoordinates(results.getCoordAt(0),results.getCoordAt(1));
				break;
			case ZIPCODE:
				data = wapi.fetchCurrentWeatherByZipcode(results.getArgAt(0));
				break;
			default:
				ArgumentParser.usageMessage();
				return;
			}
			JsonResults printResult = new JsonResults(data);
			printResults(printResult, false);

		}
//		JsonResults printResult = new JsonResults(data);
//		System.out.println(printResult.toString());
	}



}
