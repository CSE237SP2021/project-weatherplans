package weatherapp;

import org.json.JSONArray;
import org.json.JSONObject;

import weatherapp.JsonResults.weatherIndex;
import weatherapp.JsonResults.mainIndex;

public class menu {
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
		}
		JsonResults printResult = new JsonResults(data);
		System.out.println(printResult.toString());
		System.out.println(printResult.getWeatherAt(weatherIndex.OVERALL));
	}



}
