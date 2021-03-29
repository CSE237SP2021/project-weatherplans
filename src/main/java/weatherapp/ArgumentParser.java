package weatherapp;

import java.util.ArrayList;

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
		ArrayList<String> returnVars = new ArrayList<String>(); 
		ParseResults results = new ParseResults();
		results.locType = ApiFetchMethod.INVALID;
		while(argIndex < args.length && args[argIndex].startsWith("-")) {
			arg = args[argIndex++];
			if(argIndex < args.length) { //for now we only have one potential argument tag, if there is more (which don't accept further arguments), this might be changed
				String locArg = args[argIndex++];
				switch(arg) {
				case "-cityid":
					try {
						int cityId = Integer.parseInt(locArg);
						returnVars.add(locArg);
						results.locType = ApiFetchMethod.CITYID;
						results.arguments = returnVars;
					}catch(NumberFormatException e) {
						System.err.println("Incorrect format for city id");
						usageMessage();
					}
					break;
				case "-cityname":
					returnVars.add(locArg);
					results.locType = ApiFetchMethod.CITYNAME;
					results.arguments= returnVars;
					break;
				case "-zipcode":
					try {
						int zipcode = Integer.parseInt(locArg);
						returnVars.add(locArg);
						results.locType = ApiFetchMethod.ZIPCODE;
						results.arguments = returnVars;
					}catch(NumberFormatException e) {
						System.err.println("Incorrect format for city id");
						usageMessage();
					}
					break;
				case "-latlong":
					if(countOccurrence(locArg,',')==1) {
						String[] locArgSplit = locArg.split(",");
						try {
							double lat = Double.parseDouble(locArgSplit[0]);
							double lon = Double.parseDouble(locArgSplit[1]);
							returnVars.add(locArgSplit[0]);
							returnVars.add(locArgSplit[1]);
							results.locType = ApiFetchMethod.COORDINATES;
							results.arguments = returnVars;
						}catch(NumberFormatException e) {
							System.err.println("Incorrect format for latitude longitude");
							usageMessage();
						}
					}
					else {
						System.err.println("Incorrect format for latitude longitude");
						usageMessage();
					}
					break;
				default:
					System.err.println("Invalid argument tag");
					break;
				}


			}
			else {
				System.err.println("Incorrect number of arguments");
				usageMessage();
			}

		}
		if(args.length == 0) { //this might be removed if we want to add quick weather retrieval
			usageMessage();
		}
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
		System.err.println("Usage: menu.java [-cityid,-cityname,-latlong,-zipcode identifier]");
		System.err.println("[-cityid] requires unique city id, [-cityname] requires name of city");
		System.err.println("[-zipcode] takes argument in form of 'zipcode'"); //and if country code is omitted, default country is USA");
		System.err.println("[-latlong] takes argument in form of 'latitude,longitude'");
	}
}
