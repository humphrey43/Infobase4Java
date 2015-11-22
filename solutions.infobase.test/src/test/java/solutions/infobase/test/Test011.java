/**
 * 
 */
package solutions.infobase.test;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoObject;

import com.airbus.junit.TestRunner;
import com.airbus.junit.TestSequence;

/**
 * @author hardy
 *
 */
@RunWith(TestRunner.class)
public class Test011 extends InfobaseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	@TestSequence(01)
	public void test() throws InfobaseDatabaseException {
		System.out.println("test011");
		InfoClass class1;
		InfoObject test1;
		database.startTransaction();
		try {
			List<InfoObject> result = database.queryObjects("SELECT FROM " + CLASS_NAME1);
			int j= 42;
		} catch(Exception e) {
			int i = 42;
		}
		database.endTransaction();
		class1 = database.getInfoClass("Test1Class");
//		test1 = class1.newObject();
//		test1.setValue("name", "Hardy");
//		test1.save();
//		
//		OClass tc2 = ((OrientGraph) database.getRawDatabase()).getVertexType("meta.ObjectClass");
//		OClass tc3 = ((OrientGraph) database.getRawDatabase()).getVertexType("test.TestClass3");
//		assertEquals("meta.ObjectClass", tc2.getName());
//		assertEquals("test.TestClass3", tc3.getName());
//		assertEquals("de.pch.frames.test.TestClass1", tc3.getClass().getSuperclass().getCanonicalName());
//		assertEquals("test.TestClass1", tc2.getSuperClass().getName());
//		assertEquals("test.TestClass1", tc3.getSuperClass().getName());
	}

}
