package weatherplans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import weatherapp.ApiFetchMethod;
import weatherapp.ArgumentParser;
import weatherapp.ParseResults;


class ArgumentParserTest {

	@Test
	void testParseZip() {
		String[]testZip = {"-zipcode", "63130"};
		ArrayList<String> correctZip = new ArrayList<String>();
		correctZip.add("63130");
		ArgumentParser parser = new ArgumentParser();
		ParseResults results = parser.parse(testZip);
		assertEquals(ApiFetchMethod.ZIPCODE,results.locType);
		assertEquals(correctZip,results.arguments);
	}
	@Test
	void testParseCoord() {
		String[]testCoord = {"-latlong","38.6669,-90.3225"};
		ArrayList<Number> correctCoord = new ArrayList<Number>();
		correctCoord.add(38.6669);
		correctCoord.add(-90.3225);
		ArgumentParser parser = new ArgumentParser();
		ParseResults results = parser.parse(testCoord);
		assertEquals(ApiFetchMethod.COORDINATES,results.locType);
		assertEquals(correctCoord,results.coords);
	}

}
