# cse237-project

## Current State
The console display has been hooked up with the JSON parsing, so it's functional but not as pleasing to look at as we wanted. We didn't get to finish coloring or having nice images.

### What user stories were completed this iteration?
The user can see the weather intuitively and not just as a JSON Dump.

### What user stories do you intend to complete next iteration?
N/A

### Is there anything that you implemented but doesn't currently work?
The console display is wonky when the width of the text to be displayed is larger than the console width

### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
```
java -jar weatherplans.jar
```
At least Java 9 will be required to run the jar.


## Usage
This is a command-line weather app utilizing OpenWeather API. It currently supports
searching for the current weather by US zipcodes, city name, latitude/longitude, and city id.
One can get the current time or the forecast for the next 5 days.

