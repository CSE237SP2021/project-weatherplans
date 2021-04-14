package weatherapp;
import weatherapp.ApiFetchMethod;
import java.util.ArrayList;
import java.util.Set;
public class ParseResults {
	public ApiFetchMethod locType;
	public ArrayList<String> arguments;
	public ArrayList<Number> coords;
	public Set<Flag> flags;
	
	public String getArgAt(int index) {
		return arguments.get(index);
	}
	@Override
	public String toString() {
		return "ParseResults [locType=" + locType + ", arguments=" + arguments + ", coords=" + coords + "]";
	}
	public Number getCoordAt(int index) {
		return coords.get(index);
	}
	
	public boolean containsFlag(Flag check) {
		return flags.contains(check);
	}
}
