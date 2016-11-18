package casestudy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnalyticalMethodTest.class, EvenAndOddMatcherTest.class, HexToBinaryTest.class,
		PlanePositionFactoryTest.class, TestDataReadTest.class, TypeCodeTest.class, VelocityFactoyTest.class })

public class AllTests {

}
