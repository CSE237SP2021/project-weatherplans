# cse237-project

## Current State
Currently, we are able to correctly parse argument inputs and we have established a viable ascii display system. The JSON data is displayed for the correct location and forecast. However, they are not connected together quite yet in that the json is not being aesthetically displayed.

### What user stories were completed this iteration?
In this iteration we were able to complete a flexible and generic ascii text box display system as well as editing the parsing method to conform to clean code guidelines and accept different forecast lengths.

### What user stories do you intend to complete next iteration?
In the next iteration, we intend to finalize the processing of json data so that we are able to use it in the ascii display system that is printed to the terminal. Also, we still might have to consider locations that are outside of the United States. If a location has a city name that is not unique, we are unsure how the api handles this.

### Is there anything that you implemented but doesn't currently work?
We believe it is mostly functional right now, but testing has not been thorough for the ascii display system

### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
Enter the following command to execute the code, assuming your default java version is high enough:
```
java -jar weatherplans.jar
```
At least Java 9 will be required to run the jar.


## Usage
This is a command-line weather app utilizing OpenWeather API. It currently supports
searching for the current weather by US zipcodes, city name, latitude/longitude, and city id.
We also want to support querying for the forecast, up to 5 days ahead. (Days in forecast is limited by API)

