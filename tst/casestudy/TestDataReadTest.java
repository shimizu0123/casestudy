package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class TestDataReadTest {

	@Test
	public void 読む0行目test() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 0);
		String expected = "";
		assertThat(actual,is(expected));
	}

	@Test
	public void 読む１行目test() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 1);
		String expected = "10  02  07  00  14  be  41  20  00  0b  2c  86  3d  a5  10  03  5d  25  ";
		assertThat(actual,is(expected));
	}

	@Test
	public void 読む20行目test() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 20);
		String expected = "10  02  07  00  60  37  31  20  20  06  93  cf  65  ef  10  03  3e  1a  ";
		assertThat(actual,is(expected));
	}


	@Test
	public void 読む868行目test() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 868);
		String expected = "";
		assertThat(actual,is(expected));
	}

	@Test
	public void 読む869行目test() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 869);
		String expected = "";
		assertThat(actual,is(expected));
	}

}
