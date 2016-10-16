import org.junit.Test;

import casestudy.DF17DataAnalysis;

public class Test_DF17_analyzer {

	@Test
	public void test() {
		DF17DataAnalysis test =new DF17DataAnalysis();
		Altitude alt = new Altitude();

		
		String data = "00010000000000100000000100000000100001100110101101101101100011110111100000001100111000100101100000001101110000101111011101010100110100110010000000000000000000000000000000010000000000110111010110100010";
		System.out.println("typecode = "+ test.tc_analys(data));

		System.out.println("modeS_Address = " + test.modoS_analys(data));
;
		System.out.println("Altitude = " + alt.alt_calc(data) + "ft");
	}

}
