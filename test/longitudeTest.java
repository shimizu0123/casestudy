import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class longitudeTest {

	@Test
	public void 受信したEvenデータをバイナリで表示() {
		longitude sut = new longitude();

		String actual		= sut.binDataE();
		String expected	= "1000110101000000011000100001110101011000110000111000001011010110100100001100100010101100001010000110001110100111";
		assertThat(actual,is(expected));
	}

	@Test
	public void 受信したOddデータをバイナリで表示() {
		longitude sut = new longitude();

		String actual		= sut.binDataO();
		String expected	= "1000110101000000011000100001110101011000110000111000011001000011010111001100010000010010011010010010101011010110";
		assertThat(actual,is(expected));

	}


	@Test
	public void 経度の計算() {
		longitude sut = new longitude();

		double actual	= sut.calc_longi();
		double expected	= 3.91937;
		assertThat(actual,is(expected));

	}

}
