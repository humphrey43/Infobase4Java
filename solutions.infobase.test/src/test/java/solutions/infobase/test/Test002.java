/**
 * 
 */
package solutions.infobase.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.InfoClass;

import com.airbus.junit.TestRunner;
import com.airbus.junit.TestSequence;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

/**
 * @author hardy
 *
 */
@RunWith(TestRunner.class)
public class Test002 extends InfobaseTest {

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
	public void test01() {
		System.out.println("test002_01");
		InfoClass c = null;
		try {
			database.startTransaction();
		} catch (InfobaseDatabaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			c = database.getInfoClass(CLASS_NAME1);
		} catch (InfobaseDatabaseException e) {
		}
		try {
			if (c == null) {
				c = database.createInfoClass(CLASS_NAME1, database.getInfoClass("InfoObject"));
				database.createInfoAttribute(c, "Name", AttributeType.STRING);
				database.createInfoAttribute(c, "Alter", AttributeType.INTEGER);
			}
		} catch (InfobaseDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			database.endTransaction();
		} catch (InfobaseDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		database.assertRelationshipType("test.TestEdge1");
		
//		OClass tc2 = ((OrientGraph) database.getRawDatabase()).getVertexType("test.TestClass1");
//		OClass tc3 = ((OrientGraph) database.getRawDatabase()).getVertexType("test.TestClass3");
		assertEquals(CLASS_NAME1, c.getName());
//		assertEquals("test.TestClass3", tc3.getName());
//		assertEquals("de.pch.frames.test.TestClass1", tc3.getClass().getSuperclass().getCanonicalName());
//		assertEquals("test.TestClass1", tc2.getSuperClass().getName());
//		assertEquals("test.TestClass1", tc3.getSuperClass().getName());
	}
	@Test
	@TestSequence(02)
	public void test02() {
		System.out.println("test002_02");
		InfoClass c = null;
		try {
			database.startTransaction();
		} catch (InfobaseDatabaseException e1) {
			e1.printStackTrace();
		}
		try {
			c = database.getInfoClass(CLASS_NAME2);
		} catch (InfobaseDatabaseException e) {
		}
		try {
			if (c == null) {
				c = database.createInfoClass(CLASS_NAME2, database.getInfoClass("InfoObject"));
				database.createInfoAttribute(c, "Strasse", AttributeType.STRING);
				database.createInfoAttribute(c, "PLZ", AttributeType.STRING);
				database.createInfoAttribute(c, "Ort", AttributeType.STRING);
			}
		} catch (InfobaseDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			database.endTransaction();
		} catch (InfobaseDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(CLASS_NAME2, c.getName());
	}

}
