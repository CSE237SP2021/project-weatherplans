package weatherapp;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class JsonResults {
	public enum mainIndex{TEMP,TEMPMIN,HUMIDITY,PRESSURE,FEELSLIKE,TEMPMAX};
	public enum weatherIndex{OVERALL,DESC};
	public String name;
	public ArrayList<Number> main;
	public ArrayList<String> weather;
	
	public JsonResults(JSONObject master) {
		name = (String) master.get("name");
		main = getMain(master);
		weather = getWeather(master);
	}
	//if we want to look at more data, we can add more functions
	//handles main environment data
	private ArrayList<Number> getMain(JSONObject master){
		ArrayList<Number>mainVals = new ArrayList<Number>();
		JSONObject main = (JSONObject) master.get("main");
		mainVals.add((BigDecimal) main.get("temp"));
		mainVals.add((BigDecimal) main.get("temp_min"));
		mainVals.add((Integer) main.get("humidity"));
		mainVals.add((Integer) main.get("pressure"));
		mainVals.add((BigDecimal) main.get("feels_like"));
		mainVals.add((BigDecimal) main.get("temp_max"));
		return mainVals;
	}
	//handles weather description
	private ArrayList<String> getWeather(JSONObject master){
		ArrayList<String>weatherVals = new ArrayList<String>();
		JSONArray transform =  (JSONArray) master.get("weather"); //why is the json object stored in a one long json array bruh
		JSONObject weather = (JSONObject) transform.get(0);
		weatherVals.add((String) weather.get("main"));
		weatherVals.add((String) weather.get("description"));
		return weatherVals;
	}

	@Override
	public String toString() {
		return "JsonResults [name=" + name + ", main=" + main + ", weather=" + weather + "]";
	}
	
}
