package tests.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import config.UnitTesting;
import domain.Station;
@Category(UnitTesting.class)
public class StationTests {

	String FROM_STATION = "dummyLocation";
	Station station;
	
	@Before
	public void before() {
		station = new Station(FROM_STATION);
	}
	
	@Test
	public void testName() {
		Assert.assertEquals(station.getName(), FROM_STATION);
	}
}
