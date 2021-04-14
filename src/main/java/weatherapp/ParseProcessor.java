package weatherapp;
import weatherapp.ApiFetchMethod;
import java.util.ArrayList;

public class ParseProcessor {
	
	/**
	 * Gets the temperature from a raw string of information.
	 * @param rawTemperature is the the raw string containing the temperature
	 * @return returns the temperature after unnecessary characters are removed
	 */
	public String getTemperature(String rawTemperature) {
		String temperature = "Temperature is: ";
		
		int leftBound = rawTemperature.indexOf("placeholder");
		int rightBound = rawTemperature.indexOf("placeholder");
		String combined = rawTemperature.substring(leftBound + 1, rightBound);
		temperature = temperature + combined;
		
		return temperature;
	}
	
	/**
	 * Gets the humidity from a raw string of information.
	 * @param rawHumidity is the the raw string containing the humidity
	 * @return returns the humidity after unnecessary characters are removed
	 */
	public String getHumidity(String rawHumidity) {
		String humidity = "Humidity is: ";
		
		int leftBound = rawHumidity.indexOf("placeholder");
		int rightBound = rawHumidity.indexOf("placeholder");
		String combined = rawHumidity.substring(leftBound + 1, rightBound);
		humidity = humidity + combined;
		
		return humidity;
	}
	
	/**
	 * Gets the wind speed from a raw string of information.
	 * @param rawWind is the the raw string containing the wind speed
	 * @return returns the wind speed after unnecessary characters are removed
	 */
	public String getWind(String rawWind) {
		String wind = "Windspeed is: ";
		
		int leftBound = rawWind.indexOf("placeholder");
		int rightBound = rawWind.indexOf("placeholder");
		String combined = rawWind.substring(leftBound + 1, rightBound);
		wind = wind + combined;
		
		return wind;
	}
}