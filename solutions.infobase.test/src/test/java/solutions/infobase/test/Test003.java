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
import solutions.infobase.core.Infobase.CardinalityType;
import solutions.infobase.core.Infobase.OptionalityType;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoRelationship;

import com.airbus.junit.TestRunner;
import com.airbus.junit.TestSequence;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

/**
 * @author hardy
 *
 */
@RunWith(TestRunner.class)
public class Test003 extends InfobaseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown1");
	}

	@Test
	@TestSequence(01)
	public void test() throws InfobaseDatabaseException {
		System.out.println("test003");
		InfoRelationship r = null;
		try {
			database.startTransaction();
		} catch (InfobaseDatabaseException e1) {
			e1.printStackTrace();
		}
		try {
			r = database.getRelationship(RELATION_NAME1);
		} catch (InfobaseDatabaseException e) {
		}
		try {
			if (r == null) {
				InfoClass c1 = database.getInfoClass(CLASS_NAME1);
				InfoClass c2 = database.getInfoClass(CLASS_NAME2);
				r = database.createRelationship(RELATION_NAME1, c1, c2, CardinalityType.MANY, OptionalityType.OPTIONAL, CardinalityType.MANY, OptionalityType.OPTIONAL, true);
//				r.s
//				database.createInfoAttribute(r, "Strasse", AttributeType.STRING);
//				database.createInfoAttribute(r, "PLZ", AttributeType.STRING);
//				database.createInfoAttribute(r, "Ort", AttributeType.STRING);
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
		
		assertEquals(CLASS_NAME2, r.getName());
	}
}
