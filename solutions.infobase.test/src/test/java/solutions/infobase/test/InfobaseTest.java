package solutions.infobase.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.interfaces.InfoDatabase;

public class InfobaseTest {
	
	static InfoDatabase database = null;
	
	public static final String CLASS_NAME1 = "Person";
	public static final String CLASS_NAME2 = "Adresse";
	public static final String CLASS_NAME3 = "TestClass3";
	public static final String RELATION_NAME1 = "wohnt";
	
	@BeforeClass
	public static void setUpBeforeClassMain() throws Exception {
		database = Infobase.getDatabase("test");
//		database.startTransaction();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClassMain() throws Exception {
		if (database != null) {
			database.close();
			database = null;
		}
//		database.endTransaction();
//		TestEnvironment.finish();
//		database = null;
	}

}
