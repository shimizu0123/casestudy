package casestudy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EvenAndOddMatcherTest.class, HexToBinaryTest.class, TestDataReadTest.class })
public class AllTests {

}
