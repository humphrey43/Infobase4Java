package solutions.infobase.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import solutions.infobase.orientdb.InfobaseDatabaseOrientDB;

public class InfobaseTest {
	
	static InfobaseDatabaseOrientDB database = null;
	
	@BeforeClass
	public static void setUpBeforeClassMain() throws Exception {
		database = (InfobaseDatabaseOrientDB) TestEnvironment.getDatabase();
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
