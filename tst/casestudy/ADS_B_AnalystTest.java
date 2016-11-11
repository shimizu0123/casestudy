package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class ADS_B_AnalystTest {

	@Test
	public void test() {


		String actual = ADS_B_Analyst.analyzeData(TestDataRead.fileReadLine(22));
		String expected = null;

		assertThat(actual,is(expected));

	}

}
