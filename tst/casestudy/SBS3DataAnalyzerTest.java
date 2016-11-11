package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class SBS3DataAnalyzerTest {

	@Test
	public void test() {


		String actual = SBS3DataAnalyzer.analyzeData(TestDataRead.fileReadLine(22));
		String expected = null;

		assertThat(actual,is(expected));

	}

}
