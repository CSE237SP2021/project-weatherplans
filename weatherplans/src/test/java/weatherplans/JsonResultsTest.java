package weatherplans;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import weatherapp.JsonResults.weatherIndex;
import weatherapp.JsonResults;
import weatherapp.JsonResults.mainIndex;
import weatherapp.WeatherApi;

class JsonResultsTest {

	@Test
	void testJsonCurrentMain() {

		WeatherApi wapi = new WeatherApi();
		JSONObject data = wapi.fetchCurrentWeatherByCityName("Saint Louis");
		JSONObject main = (JSONObject) data.get("main");
		JsonResults results = new JsonResults(data);
		assertEquals("Saint Louis",results.name);
		
		assertEquals(main.get("temp"),results.getMainAt(mainIndex.TEMP));
		assertEquals(main.get("temp_min"),results.getMainAt(mainIndex.TEMPMIN));
		assertEquals(main.get("humidity"),results.getMainAt(mainIndex.HUMIDITY));
		assertEquals(main.get("pressure"),results.getMainAt(mainIndex.PRESSURE));
		assertEquals(main.get("feels_like"),results.getMainAt(mainIndex.FEELSLIKE));
		assertEquals(main.get("temp_max"),results.getMainAt(mainIndex.TEMPMAX));
	}
	@Test
	void testJsonCurrentWeather() {
		WeatherApi wapi = new WeatherApi();
		JSONObject data = wapi.fetchCurrentWeatherByCityName("Saint Louis");
		JSONArray jsonTransform = (JSONArray) data.get("weather");
		JSONObject weather = (JSONObject) jsonTransform.get(0);
		JsonResults results = new JsonResults(data);
		
		assertEquals("Saint Louis",results.name);
		assertEquals(weather.get("main"),results.getWeatherAt(weatherIndex.OVERALL));
		assertEquals(weather.get("description"),results.getWeatherAt(weatherIndex.DESC));
	}
}
