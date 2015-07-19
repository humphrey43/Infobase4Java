package solutions.infobase.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import solutions.infobase.orientdb.InfoDatabaseOrientDB;

public class InfobaseTest {
	
	static InfoDatabaseOrientDB database = null;
	
	@BeforeClass
	public static void setUpBeforeClassMain() throws Exception {
		database = (InfoDatabaseOrientDB) TestEnvironment.getDatabase();
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
