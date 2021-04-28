# cse237-project

## Current State
JSON parsing is now hooked up with the command line display. We ran out of time and weren't able to add color or images. 

### What user stories were completed this iteration?
The program no longer dumps unformatted JSON. We weren't able to make the display very visually pleasing, but it is organized.

### What user stories do you intend to complete next iteration?
N/A

### Is there anything that you implemented but doesn't currently work?
The ASCII display gets very wonky if the printed content is wider than the console width. 

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

