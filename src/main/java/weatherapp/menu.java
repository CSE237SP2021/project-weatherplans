package weatherapp;

import org.json.JSONObject;

public class menu {

	public static void main(String[] args) {
		int argIndex=0;
		String arg;
		String cityName;
		String cityId;
		Number[] latlong = new Number[2];
		String[] zipcode = new String[2];
		while(argIndex < args.length && args[argIndex].startsWith("-")) {
			arg = args[argIndex++];
			if(arg.equals("-l") || arg.equals("-location")){
				if(argIndex < args.length) {
					String locArg = args[argIndex++];
					try { //if try method succeeds, then argument is only city id
						int numericArg = Integer.parseInt(locArg);
						
						
						//city id variable parse
						System.out.println("city id");
						cityId = locArg;
						
					}catch (NumberFormatException e){ //if exception is caught, then argument for location is either city name, zipcode or lat,lon
						if(countOccurrence(locArg,',')==1) { //if there is comma, then it is zipcode or lat,lon
							String[] locArgSplit = locArg.split(",");
							if(locArgSplit[0].matches("\\d+(\\.\\d+)?") && locArgSplit[1].matches("\\d+(\\.\\d+)?")) { //regular expression check if first part is number, if yes then it is lat,long
								
								//latlong variable parse
								System.out.println("latitude longitude");
								latlong[0]= Double.parseDouble(locArgSplit[0]);
								latlong[1]= Double.parseDouble(locArgSplit[1]);
							}
							else if(locArgSplit[1].matches("\\d+(\\.\\d+)?")){
								
								//zip code variable parse
								System.out.println("zipcode");
								zipcode[0]=locArgSplit[0];
								zipcode[1]=locArgSplit[1];
							}
							
						}
						else if(countOccurrence(locArg,',')==0){ //if no comma, then it is city name
							
							//city name variable parse
							System.out.println("city name");
							cityName = locArg;
							
						}
						else {
							System.err.println("Incorrect argument format");
							usageMessage();
						}
					}
				}
				else {
					System.err.println("Invalid number of arguments");
					usageMessage();
				}
			}
		}
		if(argIndex == args.length) {
			usageMessage();
		}
		WeatherApi wapi = new WeatherApi();
		JSONObject data = wapi.fetchForecastByCoordinates(35, 139);
		System.out.println(data.toString(4));

	}
	
	/**
	 * Prints the usage message for the program in cases where arguments are not met correctly
	 */
	public static void usageMessage() {
		System.err.println("Usage: menu.java [-l,-location]");
		System.err.println("[-l,-location] tag takes arguments for city name, city id, latitude and longtitude in form 'lat,lon', and zip code in form of 'country code,zipcode'");
	}
	/**
	 * 
	 * @param in string to be searched to find number of occurrences of certain character
	 * @param cFind character to be counted
	 * @return number of certain character in string
	 */
	public static int countOccurrence(String in, char cFind) {
		int count = 0;
		for(char c: in.toCharArray()) {
			if(c==cFind) {
				count++;
			}
		}
		return count;
	}
}
