package weatherapp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class JsonResults {
	public static enum mainIndex{TEMP,TEMPMIN,HUMIDITY,PRESSURE,FEELSLIKE,TEMPMAX};
	public static enum weatherIndex{OVERALL,DESC};
	public String name;
	public Map<mainIndex,Number> main;
	public Map<weatherIndex, String> weather;
	//gosh why can't tuples exist in java
	//index for forecast goes from 0-39 in 3 hour time segments for a total of 120 hours (5 days)
	public ArrayList<Object> forecastMain = new ArrayList<Object>();
	public ArrayList<Object> forecastWeather = new ArrayList<Object>();
	public ArrayList<String> forecastTime = new ArrayList<String>();
	
	public JsonResults(JSONObject master) {
		if(master.has("city")) {
			JSONObject city = (JSONObject) master.get("city");
			JSONArray jsonForecast = (JSONArray) master.get("list");
			name = (String) city.get("name");
			for(int i = 0; i< jsonForecast.length(); i++) {
				JSONObject time = (JSONObject) jsonForecast.get(i);
				forecastMain.add(getMain(time));
				forecastWeather.add(getWeather(time));
				forecastTime.add((String) time.get("dt_txt"));
			}
		}
		else {
			name = (String) master.get("name");
			main = getMain(master);
			weather = getWeather(master);
		}
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
		mainVals.put(mainIndex.FEELSLIKE,(Number) main.get("feels_like"));
		mainVals.put(mainIndex.TEMPMAX,(BigDecimal) main.get("temp_max"));
		return mainVals;
	}
	//handles weather description
	private Map<weatherIndex, String> getWeather(JSONObject master){
		Map<weatherIndex, String>weatherVals = new EnumMap<>(weatherIndex.class);
		JSONArray transform =  (JSONArray) master.get("weather"); //why is the json object stored in a size one json array bruh
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
	
	public Number getForecastMainAt(int timeIndex, mainIndex mIndex) {
		Map<mainIndex,Number> fmainMap = (Map<weatherapp.JsonResults.mainIndex, Number>) forecastMain.get(timeIndex);
		return fmainMap.get(mIndex);
	}
	
	public String getForecastWeatherAt(int timeIndex, weatherIndex wIndex) {
		Map<weatherIndex, String> fweatherMap = (Map<weatherIndex, String>) forecastWeather.get(timeIndex);
		return fweatherMap.get(wIndex);
	}
	public boolean isForecast() {
		return !forecastMain.isEmpty();
	}

	//
	public ArrayList<String> getMainAsStringList() {
		ArrayList<String> textContent = new ArrayList<>();
		for (Map.Entry<mainIndex, Number> entry : this.main.entrySet()) {
			mainIndex mainEnum = entry.getKey();
			Number numericVal = entry.getValue();
			String line = mainEnum.toString() + ": " + numericVal.toString();
			textContent.add(line);
		}
		return textContent;
	}
	
	//
	public ArrayList<ArrayList<String>> getForecastMainAsListOfLists() {
		ArrayList<ArrayList<String>> results = new ArrayList<>();
		
		for (Object obj : this.forecastMain) {
			Map<mainIndex, Number> map = (Map<mainIndex, Number>) obj;
			
			ArrayList<String> textContent = new ArrayList<>();
			for (Map.Entry<mainIndex, Number> entry : map.entrySet()) {
				mainIndex mainEnum = entry.getKey();
				Number numericVal = entry.getValue();
				String line = mainEnum.toString() + ": " + numericVal.toString();
				textContent.add(line);
			}
			results.add(textContent);
		}
		
		return results;
	}

	@Override
	public String toString() {
		return "JsonResults [name=" + name + ", main=" + main + ", weather=" + weather + ", forecastMain="
				+ forecastMain + ", forecastWeather=" + forecastWeather + "]";
	}

}
