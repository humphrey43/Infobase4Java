package solutions.infobase.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import solutions.infobase.core.interfaces.InfoDatabase;

public class InfobaseTest {
	
	static InfoDatabase database = null;
	
	public static final String CLASS_NAME1 = "TestClass1";
	public static final String CLASS_NAME2 = "TestClass2";
	public static final String CLASS_NAME3 = "TestClass3";
	
	@BeforeClass
	public static void setUpBeforeClassMain() throws Exception {
		database = TestEnvironment.getDatabase();
//		database.startTransaction();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClassMain() throws Exception {
//		database.endTransaction();
//		TestEnvironment.finish();
//		database = null;
	}

}
