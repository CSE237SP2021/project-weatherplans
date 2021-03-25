package weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Wrapper around the OpenWeather API; documentation at https://openweathermap.org/current
 * Currently supports fetching by 
 * 		city name
 * 		city id - id's can be downloaded from city.list.json.gz at http://bulk.openweathermap.org/sample/
 * 		coordinates in string form as "latitude/longitude"
 * 		zipcode
 * 
 * TODO: Optional API Parameters
 * 		Specify Celsius or Fahrenheit or Kelvin
 * 
 * @author evan
 *
 */
public class WeatherApi {
	private static String API_KEY = "b1bf4a07bd8dca7fa3d39153cab10847";
	private static String forecastBaseUrl = "http://api.openweathermap.org/data/2.5/forecast?";
	private static String currentWeatherBaseUrl = "http://api.openweathermap.org/data/2.5/weather?";

	public WeatherApi() {}

	/* *********************************************************************************
	 *  The following methods fetch the current weather using a specific identifier.
	 */
	public JSONObject fetchCurrentWeatherByCityName(String cityName) {
		String url = constructUrl(ApiFetchMethod.CITYNAME, WeatherApi.currentWeatherBaseUrl, cityName);
		return readJsonFromUrl(url);
	}
	public JSONObject fetchCurrentWeatherByCityId(String cityId) {
		String url = constructUrl(ApiFetchMethod.CITYID, WeatherApi.currentWeatherBaseUrl, cityId);
		return readJsonFromUrl(url);
	}
	public JSONObject fetchCurrentWeatherByCoordinates(Number latitude, Number longitude) {
		String coordinateString = latitude.toString() + "/" + longitude.toString();
		String url = constructUrl(ApiFetchMethod.COORDINATES, WeatherApi.currentWeatherBaseUrl, coordinateString);
		return readJsonFromUrl(url);
	}
	public JSONObject fetchCurrentWeatherByZipcode(String zipcode) {
		String url = constructUrl(ApiFetchMethod.ZIPCODE, WeatherApi.currentWeatherBaseUrl, zipcode);
		return readJsonFromUrl(url);
	}
	
	
	/* *********************************************************************************
	 * The following methods fetch the forecast for the next 5 days.
	 * There is data for every 3 hr step.
	 */
	public JSONObject fetchForecastByCityName(String cityName) {
		String url = constructUrl(ApiFetchMethod.CITYNAME, WeatherApi.forecastBaseUrl, cityName);
		return readJsonFromUrl(url);
	}
	public JSONObject fetchForecastByCityId(String cityId) {
		String url = constructUrl(ApiFetchMethod.CITYID, WeatherApi.forecastBaseUrl, cityId);
		return readJsonFromUrl(url);
	}
	public JSONObject fetchForecastByCoordinates(Number latitude, Number longitude) {
		String coordinateString = latitude.toString() + "/" + longitude.toString();
		String url = constructUrl(ApiFetchMethod.COORDINATES, WeatherApi.forecastBaseUrl, coordinateString);
		return readJsonFromUrl(url);
	}
	public JSONObject fetchForecastByZipcode(String zipcode) {
		String url = constructUrl(ApiFetchMethod.ZIPCODE, WeatherApi.forecastBaseUrl, zipcode);
		return readJsonFromUrl(url);
	}
	
	/**
	 * Constructs a URL that the OpenWeather API will accept.
	 * @param fetchMethod - Whether we are searching by city id, city name, latitude/longitude, or zipcode
	 * @param baseUrl 
	 * @param identifier - The value(s) used to find the location whose weather we want
	 * @return - A valid url for the OpenWeather API
	 */
	private String constructUrl(ApiFetchMethod fetchMethod, String baseUrl, String identifier) {
		String url = baseUrl;
		switch (fetchMethod) {
		case CITYNAME:
			url += "q=" + identifier;
			break;
		case CITYID:
			url += "id=" + identifier;
			break;
		case COORDINATES:
			CoordinatePair coords = new CoordinatePair(identifier);
			url += "lat=" + coords.latitude;
			url += "&lon=" + coords.longitude;
			break;
		case ZIPCODE:
			url += "zip=" + identifier;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + fetchMethod);
		}
		
		url = appendApiKey(url);
		return url;
	}
	
	private String appendApiKey(String url) {
		return url += "&appid=" + WeatherApi.API_KEY;
	}
	
	// https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
	//Extracts all of the text stored in a Reader combined into a single String.
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	/**
	 * Given a URL, tries to extract a JSONObject from the response.
	 * @param url
	 * @return - Tries to parse and return a JSONObject. If any errors occur, returns null.
	 */
	private static JSONObject readJsonFromUrl(String url) {
		try {
			InputStream is = new URL(url).openStream();
			try (is){
				BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				String jsonText = readAll(rd);
				JSONObject json = new JSONObject(jsonText);
				return json;
			}
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * Responsible for parsing a coordinate string in the form "latitude/longitude"
	 * and returning the values as a struct.
	 * @author evan
	 *
	 */
	private class CoordinatePair{
		private String latitude;
		private String longitude;
		
		public CoordinatePair(String coordinateString) {
			String[] coordinates = this.parseCoordinates(coordinateString);
			this.latitude = coordinates[0];
			this.longitude = coordinates[1];
		}
		
		private String[] parseCoordinates(String coordinateString) {
			return coordinateString.split("/");
		}
	}
}
