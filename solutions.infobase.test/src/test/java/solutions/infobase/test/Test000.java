/**
 * 
 */
package solutions.infobase.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.airbus.junit.TestRunner;
import com.airbus.junit.TestSequence;

import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.orientdb.InfoDatabaseOrientDB;

/**
 * @author hardy
 *
 */
@RunWith(TestRunner.class)
public class Test000 extends InfobaseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		System.out.println("setUpBeforeClass1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		System.out.println("tearDownAfterClass1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		System.out.println("setUp1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
//		System.out.println("tearDown1");
	}

	@Test
	@TestSequence(01)
	public void test() throws InfobaseDatabaseException {
		System.out.println("test000");
		System.out.println(AttributeType.LANGUAGE_STRING.toString());
		((InfoDatabaseOrientDB) database).initBasicMetadata();
		((InfoDatabaseOrientDB) database).dropVertexType(CLASS_NAME1);
		((InfoDatabaseOrientDB) database).dropVertexType(CLASS_NAME2);
		((InfoDatabaseOrientDB) database).dropVertexType(CLASS_NAME3);
	}

}
