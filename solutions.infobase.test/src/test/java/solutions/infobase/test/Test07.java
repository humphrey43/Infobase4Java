/**
 * 
 */
package solutions.infobase.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;

import com.airbus.junit.TestRunner;
import com.airbus.junit.TestSequence;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * @author hardy
 *
 */
@RunWith(TestRunner.class)
public class Test07 {

	static OrientGraphFactory factory = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new OrientGraphFactory("remote:localhost/TestDB", "admin", "admin");
		factory.setAutoStartTx(false);
		factory.setupPool(1, 3);
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
	public void pretest() throws InfobaseDatabaseException {
		System.out.println("pretest07");
		String classname = "Test1";
		OrientGraphNoTx graphdb = factory.getNoTx();
		OrientVertexType v = graphdb.getVertexType(classname);
		if (v != null) {
			graphdb.dropVertexType(classname);
		}
		v = graphdb.getVertexType(classname);
		graphdb.shutdown();
		assertNull("Class nicht gelöscht: " + classname, v);
}

	@Test
	@TestSequence(02)
	public void test2() throws InfobaseDatabaseException {
		OrientGraphNoTx graphdb = null;
		OrientGraph graphdb2 = null;
		System.out.println("test07_2");
		String classname = "Test1";
		OrientVertexType v = null;
		try {
			graphdb2 = factory.getTx();
			v = graphdb2.getVertexType(classname);
			if (v == null) {
				graphdb = factory.getNoTx();
				v = graphdb.createVertexType(classname);
			}
		} finally {
			if (graphdb != null) graphdb.shutdown();
			if (graphdb2 != null) graphdb2.shutdown();
		}
		assertEquals(classname, v.getName());
	}

	@Test
	@TestSequence(03)
	public void test3() throws InfobaseDatabaseException {
//		OrientGraphNoTx graphdb = null;
		OrientGraph graphdb2 = null;
		System.out.println("test07_3");
		String classname = "Test1";
		OrientVertex v = null;
		try {
			graphdb2 = factory.getTx();
			v = graphdb2.addVertex("class:" + classname);
			if (v != null) {
				v.setProperty("name", "Hugo");
				v.save();
			}
		} finally {
//			if (graphdb != null) graphdb.shutdown();
			if (graphdb2 != null) graphdb2.shutdown();
		}
		
		assertEquals(classname, v.getProperty("@class"));
	}

	@Test
	@TestSequence(04)
	public void test4() throws InfobaseDatabaseException {
//		OrientGraphNoTx graphdb = null;
		OrientGraph graphdb2 = null;
		System.out.println("test07_4");
		String classname = "Test1";
//		OrientVertex v = null;
		try {
			graphdb2 = factory.getTx();
			Iterable<Vertex> vs = graphdb2.getVerticesOfClass(classname);
			assertNotNull(vs);
			for (Vertex v : vs) {
				assertEquals("Hugo", v.getProperty("name"));
			}
		} finally {
//			if (graphdb != null) graphdb.shutdown();
			if (graphdb2 != null) graphdb2.shutdown();
		}
	}

}
