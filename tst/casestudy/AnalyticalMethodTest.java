package casestudy;

import static casestudy.AnalyticalMethod.*;
import static casestudy.TestDataRead.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class AnalyticalMethodTest {

	@Test
	public void countOfLatZoneTest0度で59個を返す() {
		int actual		= countOfLatZone(0);
		int expected	= 59;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest86_5度で3個を返す() {
		int actual		= countOfLatZone(86.5);
		int expected	= 3;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest86度で3個を返す() {
		int actual		= countOfLatZone(86);
		int expected	= 3;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest87度で2個を返す() {
		int actual		= countOfLatZone(87);
		int expected	= 2;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest88度で1個を返す() {
		int actual		= countOfLatZone(88);
		int expected	= 1;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTestマイナス86_6度で2個を返す() {
		int actual		= countOfLatZone(-86.6);
		int expected	= 2;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTestマイナス89度で1個を返す() {
		int actual		= countOfLatZone(-89);
		int expected	= 1;
		assertThat(actual, is(expected));
	}

	@Test
	public void テストデータ528行目629行目calcLat_calcLon_calcAlt() {
		String dataE = fileReadLineBinary(528);
		String dataO = fileReadLineBinary(629);
		boolean evenNewThanOdd = false;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 36.6502434866769;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 136.52072987657914;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38100;
		assertThat("Alt:",actual2, is(expected2));

	}
	@Test
	public void calcLat_calcLon_calcAltTestテストデータ820行目789行目() {
		String dataE = fileReadLineBinary(820);
		String dataO = fileReadLineBinary(789);
		boolean evenNewThanOdd = true;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 34.782257080078125;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 133.65585638552295;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38100;
		assertThat("Alt:",actual2, is(expected2));
	}
	@Test
	public void calcLat_calcLon_calcAltTestテストデータ789行目528行目() {
		String dataE = fileReadLineBinary(528);
		String dataO = fileReadLineBinary(789);
		boolean evenNewThanOdd = false;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 36.64607456752232;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 136.50179599193817;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38075;
		assertThat("Alt:",actual2, is(expected2));
	}
	@Test
	public void calcAltTestテストデータ313行目76行目() {
		String dataE = fileReadLineBinary(528);
		String dataO = fileReadLineBinary(789);
		boolean evenNewThanOdd = false;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 36.64607456752232;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 136.50179599193817;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38075;
		assertThat("Alt:",actual2, is(expected2));
	}

}
