package weatherapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import weatherapp.ApiFetchMethod;
import weatherapp.ParseResults;
public class ArgumentParser {
	
	/**
	 * Parses the arguments passed in from main
	 * @param args string array to be parsed through for intended arguments
	 * @return returns a parseresults class which includes all variables we might need for running the api
	 */
	public ParseResults parse(String[] args) {
		int argIndex=0;
		String arg;
		Set<Flag> flags = new HashSet<Flag>(); 
		ParseResults results = new ParseResults();
		results.locType = ApiFetchMethod.EMPTY;
		while(argIndex < args.length && args[argIndex].startsWith("-")) {
			arg = args[argIndex++];
			if(arg.equals("-help")) {
				//usageMessage();
				results.locType = ApiFetchMethod.INVALID;
				break;
			}
			ArrayList<String> locArgCheck = new ArrayList<String>();
			locArgCheck.add("-cityid"); locArgCheck.add("-cityname"); locArgCheck.add("-zipcode"); locArgCheck.add("-latlong");
			if(arg.equals("-extend")&&!flags.contains(Flag.LENGTH)) {
				flags.add(Flag.LENGTH);
				continue;
			}
			else if(locArgCheck.contains(arg) && !flags.contains(Flag.LOCATION)) { //have to use flag check so not accepting two location arguments
				if(argIndex < args.length) { 
					String locArg = args[argIndex++];
					flags.add(Flag.LOCATION);
					results = processLoc(arg,locArg);
				}
				else {
					System.err.println("Incorrect number of arguments");
					results.locType = ApiFetchMethod.INVALID;
					break;
				}
			}
			else {
				System.err.println("Unknown argument flag found");
				results.locType = ApiFetchMethod.INVALID;
				break;
			}

		}
		results.flags = flags;
		return results;
	}
	public ParseResults processLoc(String arg, String locArg) {
		ParseResults results = new ParseResults();
		switch(arg) {
		case "-cityid":
			results = processId(locArg);
			break;
		case "-cityname":
			results = processName(locArg);
			break;
		case "-zipcode":
			results = processZip(locArg);
			break;
		case "-latlong":
			results = processLatLong(locArg);
			break;
		default:
			System.err.println("Invalid argument tag");
			break;
		}
		return results;
	}
	/**
	 * 
	 * @param locArg location argument string
	 * @return parseresult object that contains all information for api processing
	 */
	public ParseResults processId(String locArg) {
		ParseResults results = new ParseResults();
		results.locType = ApiFetchMethod.EMPTY;
		try {
			int cityId = Integer.parseInt(locArg);
			ArrayList<String> returnVars = new ArrayList<String>();
			returnVars.add(locArg);
			
			results.locType = ApiFetchMethod.CITYID;
			results.arguments = returnVars;
			
		}
		catch(NumberFormatException e) {
			System.err.println("Incorrect format for city id");
			results.locType = ApiFetchMethod.INVALID;
		}
		return results;
	}
	/**
	 * 
	 * @param locArg location argument string
	 * @return parseresult object that contains all information for api processing
	 */
	public ParseResults processLatLong(String locArg) {
		ParseResults results = new ParseResults();
		if(countOccurrence(locArg,',')==1) {
			String[] locArgSplit = locArg.split(",");
			try {
				double lat = Double.parseDouble(locArgSplit[0]);
				double lon = Double.parseDouble(locArgSplit[1]);
				ArrayList<Number> returnCoords = new ArrayList<Number>();
				returnCoords.add(lat);
				returnCoords.add(lon);
				results.locType = ApiFetchMethod.COORDINATES;
				results.coords = returnCoords;
			}catch(NumberFormatException e) {
				System.err.println("Incorrect format for latitude longitude");
				results.locType = ApiFetchMethod.INVALID;
			}
		}
		else {
			System.err.println("Incorrect format for latitude longitude");
			results.locType = ApiFetchMethod.INVALID;
		}
		return results;
	}
	/**
	 * 
	 * @param locArg location argument string
	 * @return parseresult object that contains all information for api processing
	 */
	public ParseResults processZip(String locArg) {
		ParseResults results = new ParseResults();
		results.locType = ApiFetchMethod.EMPTY;
		try {
			int zipcode = Integer.parseInt(locArg);
			ArrayList<String> returnVars = new ArrayList<String>();
			returnVars.add(locArg);
			results.locType = ApiFetchMethod.ZIPCODE;
			results.arguments = returnVars;
		}catch(NumberFormatException e) {
			System.err.println("Incorrect format for zipcode");
			results.locType = ApiFetchMethod.INVALID;
		}
		return results;
	}
	/**
	 * 
	 * @param locArg location argument string
	 * @return parseresult object that contains all information for api processing
	 */
	public ParseResults processName(String locArg) {
		ParseResults results = new ParseResults();
		ArrayList<String> returnVars = new ArrayList<String>();
		String newLocArg = locArg.replace("_", " ");
		returnVars.add(newLocArg);
		results.locType = ApiFetchMethod.CITYNAME;
		results.arguments= returnVars;
		return results;
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
	/**
	 * Prints the usage message for the program in cases where arguments are not met correctly
	 */
	public static void usageMessage() {
		System.err.println("Usage: menu.java [-cityid,-cityname,-latlong,-zipcode identifier] [-extend]");
		System.err.println("[-cityid] requires unique city id, [-cityname] requires name of city (if the name has spaces in it, replace all spaces with _)");
		System.err.println("[-zipcode] takes argument in form of 'zipcode'"); //and if country code is omitted, default country is USA");
		System.err.println("[-latlong] takes argument in form of 'latitude,longitude'");
		System.err.println("[-extend] extends forecast from default current weather forecast to 5 day forecast");
	}
}
