package weatherapp;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class JsonResults {
	public enum mainIndex{TEMP,TEMPMIN,HUMIDITY,PRESSURE,FEELSLIKE,TEMPMAX};
	public enum weatherIndex{OVERALL,DESC};
	public String name;
	public Map<mainIndex,Number> main;
	public Map<weatherIndex, String> weather;
	
	public JsonResults(JSONObject master) {
		name = (String) master.get("name");
		main = getMain(master);
		weather = getWeather(master);
	}
	

	//if we want to look at more data, we can add more functions
	//handles main environment data
	private Map<mainIndex,Number> getMain(JSONObject master){
		Map<mainIndex,Number>mainVals = new EnumMap<>(mainIndex.class);
		JSONObject main = (JSONObject) master.get("main");
		mainVals.put(mainIndex.TEMP,(BigDecimal) main.get("temp"));
		mainVals.put(mainIndex.TEMPMIN,(BigDecimal) main.get("temp_min"));
		mainVals.put(mainIndex.HUMIDITY,(Integer) main.get("humidity"));
		mainVals.put(mainIndex.PRESSURE,(Integer) main.get("pressure"));
		mainVals.put(mainIndex.FEELSLIKE,(BigDecimal) main.get("feels_like"));
		mainVals.put(mainIndex.TEMPMAX,(BigDecimal) main.get("temp_max"));
		return mainVals;
	}
	//handles weather description
	private Map<weatherIndex, String> getWeather(JSONObject master){
		Map<weatherIndex, String>weatherVals = new EnumMap<>(weatherIndex.class);
		JSONArray transform =  (JSONArray) master.get("weather"); //why is the json object stored in a one long json array bruh
		JSONObject weather = (JSONObject) transform.get(0);
		weatherVals.put(weatherIndex.OVERALL, (String) weather.get("main"));
		weatherVals.put(weatherIndex.DESC,(String) weather.get("description"));
		return weatherVals;
	}

	public Number getMainAt(mainIndex index) {
		return main.get(index);
	}
	public String getWeatherAt(weatherIndex index) {
		return weather.get(index);
	}
	
	@Override
	public String toString() {
		return "JsonResults [name=" + name + ", main=" + main + ", weather=" + weather + "]";
	}


}
