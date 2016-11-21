package casestudy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnalyticalMethodTest.class, CallSignFactoryTest.class, EvenAndOddMatcherTest.class,
		HexToBinaryTest.class, PlanePositionFactoryTest.class, TestDataReadTest.class, TypeCodeTest.class,
		VelocityFactoryTest.class })

public class AllTests {

}
