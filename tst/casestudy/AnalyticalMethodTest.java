package casestudy;

import static casestudy.AnalyticalMethod.*;
import static casestudy.HexToBinary.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AnalyticalMethodTest {

	@Test
	public void calc_callSignTestテストデータ130行目からコールサインJAL2464_取得() {

		String actual	= calc_callSign(hexToBinary(TestDataRead.fileReadLine(130)));
		String expected	= "JAL2464_";

		assertThat(actual, is(expected));
	}

}
