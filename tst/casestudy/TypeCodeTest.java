package casestudy;

import static casestudy.HexToBinary.*;
import static casestudy.TypeCode.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TypeCodeTest {


	@Test
	public void createVerocityTestテストデータ4_12_13() {
		TypeCode actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(4))));
		TypeCode expected = VELOCITY;
		assertThat("テストデータ4行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(12))));
		assertThat("テストデータ12行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(13))));
		assertThat("テストデータ132行目:",actual,is(expected));
	}
	@Test
	public void createCallSignTestテストデータ130_147_189() {
		TypeCode actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(130))));
		TypeCode expected = CALL_SIGN;
		assertThat("テストデータ130行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(147))));
		assertThat("テストデータ147行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(189))));
		assertThat("テストデータ189行目:",actual,is(expected));
	}
	@Test
	public void createPlanePositonTestテストデータ11_32_61() {
		TypeCode actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(11))));
		TypeCode expected = PLANE_POSITION;
		assertThat("テストデータ11行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(32))));
		assertThat("テストデータ32行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(61))));
		assertThat("テストデータ61行目:",actual,is(expected));
	}
	@Test
	public void createOtherTestテストデータ3_5_7() {
		TypeCode actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(3))));
		TypeCode expected = OTHER;
		assertThat("テストデータ3行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(5))));
		assertThat("テストデータ5行目:",actual,is(expected));

		actual = createTypeCode(hexToBinary((TestDataRead.fileReadLine(7))));
		assertThat("テストデータ7行目:",actual,is(expected));
	}



}
