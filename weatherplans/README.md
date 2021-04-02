# cse237-project

## Current State
The API hookup is 90% functional but needs to fail elegantly when given malformed input.
Argument parsing only supports retrieving only the current weather for a location, not the forecast. It
also prints the wrong error messages at times.

### What user stories were completed this iteration?
None. Having a bit of a tough time on the pre-requisite steps.

### What user stories do you intend to complete next iteration?
We need to first clean up the current code and hopefully provide a better building method.

But user stories we hope to complete
* Get current weather at current location, even if you live in a different country. Currently it assumes you are in the USA.
* Get the 5 day forecast

### Is there anything that you implemented but doesn't currently work?
The hookup with the API backend was mostly completed but requires error handling for malformed inputs.
Argument parsing does not currently support retrieving the forecast and prints the wrong error messages at times.

### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
1. On GitHub, click the green Code button and copy your preferred cloning method URL to your clipboard.
2. Using eclipse, go to File > Import. Then under Git, select Projects with Git using smart import.
3. Open File > Export. Under Java, select Runnable Jar.
4. Under launch configuration, select menu - weatherplans.
5. Specify where you would like to export, and click finish. Ignore the warnings.
6. Within a terminal, enter the directory of the jar file.
7. Enter the following command to execute the code, assuming your default java version is high enough:
```
java -jar executable_name.jar
```
At least Java 9 will be required to run the jar.


## Usage
This is a command-line weather app utilizing OpenWeather API. It currently supports
searching for the current weather by US zipcodes, city name, latitude/longitude, and city id.
We also want to support querying for the forecast, up to 5 days ahead. (Days in forecast is limited by API)

