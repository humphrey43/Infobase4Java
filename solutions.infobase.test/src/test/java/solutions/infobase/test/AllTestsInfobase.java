package solutions.infobase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	Test001.class
})
public class AllTestsInfobase {
public AllTestsInfobase() {
	System.out.println("All Tests for Infobase");
}
}
