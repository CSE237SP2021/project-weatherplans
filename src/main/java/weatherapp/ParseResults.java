package weatherapp;
import weatherapp.ApiFetchMethod;
import java.util.ArrayList;
public class ParseResults {
	public ApiFetchMethod locType;
	public ArrayList<String> arguments;
	public ArrayList<Number> coords;
	
	
	public String getArgAt(int index) {
		return arguments.get(index);
	}
	public Number getCoordAt(int index) {
		return coords.get(index);
	}
}
