package weatherplans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import weatherapp.ApiFetchMethod;
import weatherapp.ArgumentParser;
import weatherapp.Flag;
import weatherapp.ParseResults;


class ArgumentParserTest {
	
	@Test
	void testParseCityid() {
		String cityid = "2172797";
		String[] args = {"-cityid", cityid};
		ArrayList<String> expectedParsedArg = new ArrayList<String>();
		expectedParsedArg.add(cityid);
		
		ArgumentParser parser = new ArgumentParser();
		ParseResults results = parser.parse(args);
		
		assertEquals(ApiFetchMethod.CITYID,results.locType);
		assertEquals(expectedParsedArg,results.arguments);
	}
	
	@Test
	void testParseCityName() {
		String cityName = "London";
		String[] args = {"-cityname", cityName};
		ArrayList<String> expectedParsedArg = new ArrayList<String>();
		expectedParsedArg.add(cityName);
		
		ArgumentParser parser = new ArgumentParser();
		ParseResults results = parser.parse(args);
		
		assertEquals(ApiFetchMethod.CITYNAME,results.locType);
		assertEquals(expectedParsedArg,results.arguments);
	}
	
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
	@Test
	void testMultipleArgs() {
		String[]testArgs = {"-zipcode", "63130","-extend"};
		ArrayList<String> correctZip = new ArrayList<String>();
		correctZip.add("63130");
		ArgumentParser parser = new ArgumentParser();
		ParseResults results = parser.parse(testArgs);
		assertEquals(ApiFetchMethod.ZIPCODE,results.locType);
		assertTrue(results.containsFlag(Flag.LOCATION));
		assertTrue(results.containsFlag(Flag.LENGTH));
	}
}
