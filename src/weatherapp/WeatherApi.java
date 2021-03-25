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
 * 		Specify Celsius or Fahrenheit
 * 
 * Expected Usage:
 * 		WeatherApi wapi = new WeatherApi("35/139", ApiFetchMethod.COORDINATES);
		JSONObject data = wapi.fetchData();
 * 
 * @author evan
 *
 */
public class WeatherApi {
	private static String API_KEY = "b1bf4a07bd8dca7fa3d39153cab10847";
	private static String baseUrl = "http://api.openweathermap.org/data/2.5/weather?";
	
	// Required API Parameters
	private String identifier;
	private ApiFetchMethod method;
	
	// Optional API Parameters (Unused currently)
	private String units;

	/**
	 * 
	 * @param identifier - The identifier for the designated search method.
	 * 						For example:
	 * 						If you're searching by CITYNAME, it may be "London".
	 * 						If you're searching by CITYID, it may be "2172797" for the city Cairns.
	 * 						If you're searching by COORDINATES, it may be in the form of "Latitude/Longitude" such as "35/139".
	 * 						If you're searching by ZIPCODE, it may be in the form of "77401" for Bellaire, Texas
	 * @param searchMethod - How you would like to search for a location
	 */
	public WeatherApi(String identifier, ApiFetchMethod searchMethod) {
		this.identifier = identifier;
		this.method = searchMethod;
	}
	
	/**
	 * Makes an HTTP request to the OpenWeather API and returns the JSON.
	 * @return - the resultant JSON
	 */
	public JSONObject fetchData() {
		String url = this.constructUrl();
		JSONObject data = null;
		try {
			data = readJsonFromUrl(url);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	};
	
	private String constructUrl() {
		String url = this.baseUrl;
		switch (this.method) {
		case CITYNAME:
			url += "q=" + this.identifier;
			break;
		case CITYID:
			url += "id=" + this.identifier;
			break;
		case COORDINATES:
			CoordinatePair coords = new CoordinatePair(this.identifier);
			url += "lat=" + coords.latitude;
			url += "&lon=" + coords.longitude;
			break;
		case ZIPCODE:
			url += "zip=" + this.identifier;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.method);
		}
		
		url = appendApiKey(url);
		return url;
	}
	
	private String appendApiKey(String url) {
		return url += "&appid=" + this.API_KEY;
	}
	
	// https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	
	/**
	 * Responsible for parsing a coordinate string and returning the latitude and longitude.
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
