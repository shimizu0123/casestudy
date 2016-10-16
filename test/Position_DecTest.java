import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Position_DecTest {

	String dataE = "000100000000001000000001000000001011111110011101011001011000110101000000011000100001110101011000110000111000001011010110100100001100100010101100001010000110001110100111";
	String dataO = "000100000000001000000001000000001011111110011101011001011000110101000000011000100001110101011000110000111000011001000011010111001100010000010010011010010010101011010110";


	@Test
	public void 緯度の計算() {
		Position_Dec sut = new Position_Dec();

		double actual	= sut.calc_Position(dataE,dataO);
		double expected	= 52.25720214843750;
		assertThat(actual,is(expected));

	}

	@Test
	public void 経度の計算() {
		Position_Dec sut = new Position_Dec();

		double actual	= sut.calc_Position(dataE,dataO);
		double expected	= 3.91937255859375;
		assertThat(actual,is(expected));

	}

}
