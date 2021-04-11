package weatherapp;

import java.util.ArrayList;

import org.json.JSONObject;

import datadisplay.Box;
public class menu {
	
	public static void printBoxes(ArrayList<Box> boxes) {
		
		
	}
	
	public static String upNumLines(int num) {
		if (num == 0) {
			return "";
		}
		return "\033[" + num + "A";
	}
	
	public static String rightNumLines(int num) {
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
		
		System.out.println(upNumLines(0) + "Hello");

	}
	


}
