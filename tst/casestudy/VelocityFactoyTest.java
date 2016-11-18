package casestudy;

import static casestudy.HexToBinary.*;
import static casestudy.TestDataRead.*;
import static casestudy.VelocityFactoy.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VelocityFactoyTest {

	@Test
	public void testCalc_velocityテストデータ116行目() {
		Velocity sut = calc_velocity(hexToBinary(fileReadLine(116)));
		assertThat("Deg",sut.getDeg(), is(87));
		assertThat("S_Vr",sut.getS_Vr(), is(0));
		assertThat("Vel",sut.getVel(), is(285.34365246137855));
		assertThat("Vr",sut.getVr(), is(0.0));
	}

	@Test
	public void testCalc_velocityテストデータ134行目() {
		Velocity sut = calc_velocity(hexToBinary(fileReadLine(134)));
		assertThat("Deg",sut.getDeg(), is(31));
		assertThat("S_Vr",sut.getS_Vr(), is(1));
		assertThat("Vel",sut.getVel(), is(458.9814811078983));
		assertThat("Vr",sut.getVr(), is(22.0));
	}

	@Test
	public void testCalcVerticalSignテストデータ4行目() {
		int actual = calcVerticalSign(hexToBinary(fileReadLine(4)));
		int expected = 1;
		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcVerticalSignテストデータ12行目() {
		int actual = calcVerticalSign(hexToBinary(fileReadLine(12)));
		int expected = 0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcVerticalVerocityテストデータ4行目() {
		double actual = calcVerticalVerocity(hexToBinary(fileReadLine(4)));
		double expected = 5.0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcVerticalVerocityテストデータ23行目() {
		double actual = calcVerticalVerocity(hexToBinary(fileReadLine(23)));
		double expected = 22.0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcHorizonDegテストデータ4行目() {
		double actual = calcHorizonDeg(hexToBinary(fileReadLine(4)));
		double expected = 272.7263109939063;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcHorizonDegテストデータ13行目() {
		double actual = calcHorizonDeg(hexToBinary(fileReadLine(13)));
		double expected = 80.33049474540944;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcHorizonVelocityテストデータ13行目() {
		double actual = calcHorizonVelocity(hexToBinary(fileReadLine(13)));
		double expected = 500.10498897731463;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcHorizonVelocityテストデータ41行目() {
		double actual = calcHorizonVelocity(hexToBinary(fileReadLine(41)));
		double expected = 213.84106247397855;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcNSVelocityテストデータ13行目() {
		double actual = calcNSVelocity(hexToBinary(fileReadLine(13)));
		double expected = 84.0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcNSVelocityテストデータ41行目() {
		double actual = calcNSVelocity(hexToBinary(fileReadLine(41)));
		double expected = -28.0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcEWVelocityテストデータ13行目() {
		double actual = calcEWVelocity(hexToBinary(fileReadLine(13)));
		double expected = 493.0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testCalcEWVelocityテストデータ41行目() {
		double actual = calcEWVelocity(hexToBinary(fileReadLine(41)));
		double expected = -212.0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testSignNSテストデータ4行目() {
		int actual = signNS(hexToBinary(fileReadLine(4)));
		int expected = 1;

		assertThat(actual, is(expected));
	}

	@Test
	public void testSignNSテストデータ41行目() {
		int actual = signNS(hexToBinary(fileReadLine(41)));
		int expected = 1;

		assertThat(actual, is(expected));
	}

	@Test
	public void testSignEWテストデータ4行目() {
		int actual = signEW(hexToBinary(fileReadLine(4)));
		int expected = 0;

		assertThat(actual, is(expected));
	}

	@Test
	public void testSignEWテストデータ41行目() {
		int actual = signEW(hexToBinary(fileReadLine(41)));
		int expected = 1;

		assertThat(actual, is(expected));
	}

}
