import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Data_List_Test {

	@Test
	public void EVENデータのモードSアドレスを確認する() {
		Data_List sut = new Data_List("00010000000000100000000100000000001111001001110011000100100011011000011010000000100101000101100000111011110000110001100000110110110101000101110100000000000000000000000000010000000000110010101111010010");
		if(sut.getBin_O() != null)fail("Bin_Oがnullでない");
		String actual = sut.toString();
		String expected = "868094";
		assertThat(actual, is(expected));
	}

	@Test
	public void ODDデータのモードSアドレスを確認する() {
		Data_List sut = new Data_List("0001000000000010000000010000000000000100000100000001000010001101100011001000011001110101010101100011100101011111001001100100100010100000010000011011111000000000000000000000000000010000000000110100100101101100");
		if(sut.getBin_E() != null)fail("Bin_Eがnullでない");
		String actual = sut.toString();
		String expected = "8c8675";
		assertThat(actual, is(expected));
	}

}
