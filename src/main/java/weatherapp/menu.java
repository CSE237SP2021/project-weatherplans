package weatherapp;

import org.json.JSONObject;

public class menu {

	public static void main(String[] args) {
//		if(args.length == 1) {
//			if(args[0].equals("--help")) {
//				System.out.println("get help lmao");
//			}
//		}
//		else {
//			System.out.println("we doing other stuff");
//		}
		
		WeatherApi wapi = new WeatherApi();
		JSONObject data = wapi.fetchForecastByCoordinates(35, 139);
		System.out.println(data.toString(4));

	}

}
