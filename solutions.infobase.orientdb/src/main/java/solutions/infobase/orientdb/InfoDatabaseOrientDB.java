package solutions.infobase.orientdb;

import java.util.LinkedList;
import java.util.List;

import com.orientechnologies.orient.core.exception.OSchemaException;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.database.InfoDatabaseBasic;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.meta.InfoAttributeBasic;
import solutions.infobase.core.meta.InfoClassBasic;
import solutions.infobase.core.meta.InfoObjectBasic;

public class InfoDatabaseOrientDB extends InfoDatabaseBasic {

	OrientGraph graphdb = null;
	OrientGraphNoTx graphdbNoTx = null;
	
	public InfoDatabaseOrientDB() {
		super();
	}
	
	public Object getRawDatabase() {
		return getGraphdb();
	}

	public OrientGraph getGraphdb() {
		if (graphdb == null) {
			try {
				InfoDatabaseFactoryOrientDB factory = (InfoDatabaseFactoryOrientDB) Infobase.getDatabaseFactory(databaseName);
				graphdb = factory.newConnection();
			} catch (InfobaseDatabaseException e) {
				e.printStackTrace();
			}
		}
		return graphdb;
	}

	public OrientGraphNoTx getGraphdbNoTx() {
		if (graphdbNoTx == null) {
			try {
				InfoDatabaseFactoryOrientDB factory = (InfoDatabaseFactoryOrientDB) Infobase.getDatabaseFactory(databaseName);
				graphdbNoTx = factory.newAdminConnection();
			} catch (InfobaseDatabaseException e) {
				e.printStackTrace();
			}
		}
		return graphdbNoTx;
	}

    protected String userId;
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public InfoObject newInfoObject(InfoClass infoClass) {
		OrientVertex v = getGraphdb().addVertex("class:" + infoClass.getName());
		v.setProperty(Infobase.OBJECT_INFO_CLASS_NAME, infoClass.getName());
		InfoObjectBasic erg = new InfoObjectBasic(this, v);
		erg.setInfoClass(infoClass);
		return erg;
	}
	
	public InfoAttribute newInfoAttribute(InfoClass infoClass) {
		OrientVertex v = getGraphdb().addVertex("class:" + infoClass.getName());
		InfoAttributeBasic erg = new InfoAttributeBasic(this, v);
		erg.setInfoClass(infoClass);
		return erg;
	}
	
	protected void assertInfoClass(String infoClassName) throws InfobaseDatabaseException {
		InfoClass c = getInfoClass(infoClassName);
		if (c == null) {
		}
		String name1 = infoClassName;
		int p = name1.lastIndexOf(".");
		p = name1.lastIndexOf(".",p-1);
		name1 = name1.substring(p+1);
		String name2 = "object";
		p = name2.lastIndexOf(".");
		p = name2.lastIndexOf(".",p-1);
		name2 = name2.substring(p+1);
//		if (name2.equals("core.FrameObject")) {
			assertObjectType(infoClassName);
//		} else {
//			assertObjectType(name1, name2);
//		}
	}
	
//	public void assertRelationshipType(String relationshipClassName) throws InfobaseDatabaseException {
//		String name1 = relationshipClassName;
//		int p = name1.lastIndexOf(".");
//		p = name1.lastIndexOf(".",p-1);
//		name1 = name1.substring(p+1);
//		String name2 = "object";
//		p = name2.lastIndexOf(".");
//		p = name2.lastIndexOf(".",p-1);
//		name2 = name2.substring(p+1);
////		if (name2.equals("core.FrameRelation")) {
//			assertRelationType(name1);
////		} else {
////			assertRelationType(name1, name2);
////		}
//	}
	
//	public ODocument createODocument(Class<?> class1) {
//		ODocument erg = null;
//		String name1 = class1.getName();
//		int p = name1.lastIndexOf(".");
//		p = name1.lastIndexOf(".",p-1);
//		name1 = name1.substring(p+1);
//		String name2 = class1.getSuperclass().getName();
//		p = name2.lastIndexOf(".");
//		p = name2.lastIndexOf(".",p-1);
//		name2 = name2.substring(p+1);
////		if (name2.equals("core.FrameObject")) {
//			erg = new ODocument(name1);
////		} else {
////			erg = new ODocument(name1,name2);
////		}
//		return erg;
//	}
	
	protected OClass assertRelationshipType(String name) throws InfobaseDatabaseException {
		OClass e = getGraphdb().getEdgeType(name);
		if (e == null) {
			getGraphdb().createEdgeType(name);
		}
		return e;
	}
	
	protected OClass assertRelationshipType(String name, String superClassName) throws InfobaseDatabaseException {
		OClass e = getGraphdb().getEdgeType(name);
		if (e == null) {
			e = getGraphdb().createEdgeType(name, superClassName);
		}
		return e;
	}
	
	protected OClass assertObjectType(String name) throws InfobaseDatabaseException {
		OClass c = getGraphdb().getVertexType(name);
		if (c == null) {
			c = getGraphdbNoTx().createVertexType(name);
		}
		return c;
	}
	
	protected OClass assertObjectType(String name, String superClassName) throws InfobaseDatabaseException {
		OClass c = getGraphdb().getVertexType(name);
		if (c == null) {
			c = getGraphdbNoTx().createVertexType(name, superClassName);
		}
		return c;
	}
	
	protected OProperty assertProperty(OClass oclass, String name, OType type) throws InfobaseDatabaseException {
		OProperty p = oclass.getProperty(name);
		if (p == null) {
			oclass.createProperty(name, type);
		}
		return p;
	}
	
	protected void assertRelation(OrientVertexType iclass, String linkedclassname, Direction direction, OType type) throws InfobaseDatabaseException {
//		OProperty p = iclass.g.getProperty(name);
//		if (p == null) {
		 iclass.createEdgeProperty(direction, linkedclassname, type);
//		}
	}
	
//	public void assertObjectType(String name, String superclass) throws InfobaseDatabaseException {
//		OClass c = graphdb.getVertexType(name);
//		if (c == null) {
//			graphdb.createVertexType(name, superclass);
//		}
//	}
	
//	public void assertRelationType(String name) throws InfobaseDatabaseException {
//		OClass c = graphdb.getEdgeType(name);
//		if (c == null) {
//			graphdb.createEdgeType(name);
//		}
//	}
//	
//	public void assertRelationType(String name, String superclass) throws InfobaseDatabaseException {
//		OClass c = graphdb.getEdgeType(name);
//		if (c == null) {
//			graphdb.createEdgeType(name, superclass);
//		}
//		
//	}

	public List<InfoObject> queryObjects(String query) throws InfobaseDatabaseException {
		List<InfoObject> erg = new LinkedList<>();
		InfobaseDatabaseException exception = null;
		try {
			long dbcount = useConnection();
			for (OrientVertex v : (Iterable<OrientVertex>) getGraphdb().command(new OCommandSQL(query)).execute()) {
				InfoObjectBasic fo;
				InfoClass infoclass;
				try {
					infoclass = getInfoClass(v.getProperty(Infobase.OBJECT_INFO_CLASS_NAME));
					fo = new InfoObjectBasic(this, v);
					fo.setInfoClass(infoclass);
					erg.add(fo);
				} catch (SecurityException | IllegalArgumentException e) {
					exception = new InfobaseDatabaseException(e);
					break;
				}
			}
			releaseConnection(dbcount);
		} catch (InfobaseDatabaseException e) {
			exception = e;
		}
		if (exception != null) {
			exception.printStackTrace();
			throw exception;
		}
		return erg;
	}
	
	@Override
	public InfoObject queryObject(String query) throws InfobaseDatabaseException {
		InfoObject erg = null;
		InfobaseDatabaseException exception = null;
		try {
			List<InfoObject> result = queryObjects(query);
			if (result != null) {
				if (result.size() == 1) {
					erg = result.get(0);
				} else if (result.size() == 1) {
					exception = new InfobaseDatabaseException("Multiple result objects");
				}
			}
		} catch (InfobaseDatabaseException e) {
			exception = e;
		}
		if (exception != null) {
			exception.printStackTrace();
			throw exception;
		}
		return erg;
	}
//	public List<InfoObject> queryObjects(String query) throws InfobaseDatabaseException {
//		List<InfoObject> erg = new LinkedList<>();
//		InfobaseDatabaseException exception = null;
//		try {
//			long dbcount = useConnection();
//			List<ODocument> result = ((OrientGraph) graphdb).getRawGraph().query(new OSQLSynchQuery<ODocument>(query));
//			for (ODocument od : result) {
//				InfoObject fo;
//				try {
//					Constructor<?> c;
//					 Constructor<?>[] cs = Class.forName((String) od.field(Infobase.INFO_CLASS_NAME)).getDeclaredConstructors();
//					c = Class.forName((String) od.field(Infobase.INFO_CLASS_NAME)).getConstructor(new Class[]{OrientBaseGraph.class,ODocument.class});
//					fo = (InfoObject)c.newInstance(new Object[]{((OrientGraph) graphdb).getRawGraph(), od});
//					erg.add(fo);
//				} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
//						| ClassNotFoundException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
//					exception = new InfobaseDatabaseException(e);
//					break;
//				}
//			}
//			releaseConnection(dbcount);
//		} catch (InfobaseDatabaseException e) {
//			exception = e;
//		}
//		if (exception != null) {
//			exception.printStackTrace();
//			throw exception;
//		}
//		return erg;
//	}

//	public FrameObject addVertex(Class<?> class1) {
//		FrameObject erg = null;
////		((OrientGraph) graphdb).addVertex("class:" + class1);
////		da geht auch einfacher über OrientBaseGraph.addvertx !!
//		try {
//			Constructor<?> c;
//			c = class1.getConstructor(new Class[]{OrientBaseGraph.class,ODocument.class});
//			erg = (FrameObject)c.newInstance(new Object[]{this.getOrientGraph(), createODocument(class1)});
//		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DatabaseFrameException e) {
//			e.printStackTrace();
//		}
//		return erg;
//	}
//
//	public FrameRelation addEdge(Class<?> class1, FrameObject fo1, FrameObject fo2) {
//		FrameRelation erg = null;
//		graphdb.addEdge("", fo1, fo2, "");
//		return erg;
//	}

	public void initBasicMetadata() {
		try {
			clearMetadata();
			
			OClass infoobject = assertObjectType("InfoObject");
//			assertProperty(infoobject, Infobase.INFO_CLASS_NAME, OType.STRING);
//			assertProperty(infoobject, Infobase.SUPER_CLASS_NAME, OType.STRING);
			
			OClass infoclass = assertObjectType("InfoClass", "InfoObject");
			
			OClass infoattribute = assertObjectType("InfoAttribute", "InfoObject");
//			assertProperty(infoattribute, Infobase.ATTRIBUTE_NAME, OType.STRING);
//			assertProperty(infoattribute, Infobase.ATTRIBUTE_TYPE, OType.STRING);
			
			assertRelation((OrientVertexType) infoclass, "InfoAttribute", Direction.OUT, OType.LINKMAP );
			
			assertRelationshipType("hasRelation");
			assertRelationshipType("hasAttribute", "hasRelation");
			
			InfoClass io = newInfoClass("InfoObject");
			InfoClass ic = newInfoClass("InfoClass", io);
			InfoClass ia = newInfoClass("InfoAttribute", io);

			startTransaction();
			io = getInfoClass("InfoObject");
			ia = getInfoClass("InfoAttribute");
			ic = getInfoClass("InfoClass");
			
			createAttribute(io, Infobase.OBJECT_INFO_CLASS_NAME, AttributeType.STRING);
			createAttribute(io, Infobase.OBJECT_SUPER_CLASS_NAME, AttributeType.STRING);
			
			createAttribute(ic, Infobase.CLASS_NAME, AttributeType.STRING);
			
			createAttribute(ia, Infobase.ATTRIBUTE_NAME, AttributeType.STRING);
			createAttribute(ia, Infobase.ATTRIBUTE_TYPE, AttributeType.STRING);
			endTransaction();
			int i = 42;
			
		} catch (InfobaseDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InfoAttribute createAttribute(InfoClass infoclass, String name, AttributeType type ) throws InfobaseDatabaseException {
		
		InfoClass ia = getInfoClass("InfoAttribute");
		InfoAttribute attr = newInfoAttribute(ia);
		
		assertProperty((OClass) infoclass.getRawClassType(), name, translateType(type));
		
		attr.setName(name);
		attr.setDescribedClass(infoclass);
		attr.setType(type);
		attr.save();
		
		createRelation(Infobase.RELATION_HAS_ATTRIBUTE, (OrientVertex) infoclass.getRawObject(), (OrientVertex) attr.getRawObject());

		infoclass.save();
		attr.save();

		return attr;
	}
	
	public OType translateType(AttributeType type) {
		OType erg = null;
		switch (type) {
		case STRING:
			erg = OType.STRING;
			break;
		case BOOLEAN:
			erg = OType.BOOLEAN;
			break;
		case DATE:
			erg = OType.DATE;
			break;
		case DECIMAL:
			break;
		case INTEGER:
			erg = OType.LONG;
			break;
		case LANGUAGE_STRING:
			break;
		case OBJECT:
			break;
		case RELATIONSHIP:
			break;
		case TEXT:
			break;
		case TIME:
			break;
		case TIMESTAMP:
			break;
		default:
			break;
		}
		return erg;
	}

	private void createRelation(String label, OrientVertex out, OrientVertex in) throws InfobaseDatabaseException {
		getGraphdbNoTx().addEdge(0, out, in, label);
	}

	private InfoClass newInfoClass(String infoClassName) {
		OrientVertex v = getGraphdb().addVertex("class:InfoClass");
		v.setProperty(Infobase.OBJECT_INFO_CLASS_NAME, infoClassName);
		InfoClass erg = new InfoClassBasic(this, v, infoClassName);
		return erg;
	}

	private InfoClass newInfoClass(String infoClassName, InfoClass superclass) {
		OrientVertex v = getGraphdb().addVertex("class:InfoClass");
		v.setProperty(Infobase.OBJECT_INFO_CLASS_NAME, infoClassName);
		v.setProperty(Infobase.OBJECT_SUPER_CLASS_NAME, superclass.getName());
		InfoClass erg = new InfoClassBasic(this, v, infoClassName, superclass);
		return erg;
	}


	protected void clearMetadata() throws InfobaseDatabaseException {
		dropVertexType("TestClass1");
		dropVertexType("InfoClass");
		dropVertexType("InfoAttribute");
		dropVertexType("InfoObject");
		dropEdgeType("hasAttribute");
		dropEdgeType("hasRelation");
	}

	public void dropEdgeType(String name) {
		try {
			getGraphdbNoTx().dropEdgeType(name);
		} catch (OSchemaException e) {
		}
	}

	public void dropVertexType(String name) {
		try {
			getGraphdbNoTx().dropVertexType(name);
		} catch (OSchemaException e) {
		}
	}

	@Override
	public InfoClass readInfoClass(String classname) throws InfobaseDatabaseException {
		InfoClass erg = null;
		InfobaseDatabaseException exception = null;
		try {
			long dbcount = useConnection();
			Iterable<OrientVertex> list = getGraphdb().command(new OCommandSQL("select from InfoClass where infoclassname = '" + classname + "'")).execute();
			if (list != null) {
				for (OrientVertex v : list ) {
					try {
						OrientVertexType vt = getGraphdb().getVertexType(classname);
						if (vt != null) {
							erg = new InfoClassBasic(this, v, classname);
							erg.setRawClassType(vt);
							break;
						}
					} catch (SecurityException | IllegalArgumentException e) {
						exception = new InfobaseDatabaseException(e);
						break;
					}
				}
			}
			releaseConnection(dbcount);
		} catch (InfobaseDatabaseException e) {
			exception = e;
		}
		if (exception != null) {
			exception.printStackTrace();
			throw exception;
		}
		return erg;
	}

	@Override
    public boolean isClosed() {
        boolean erg = true;
       	if (graphdb != null) {
       		erg = graphdb.isClosed(); 
        }
        return erg;
    }
	
	@Override
	public void commit() throws InfobaseDatabaseException {
		if (graphdb != null) graphdb.commit();
	}

	@Override
	public void rollback() throws InfobaseDatabaseException {
		if (graphdb != null) graphdb.rollback();
	}

	@Override
	public void finish() {
		if (graphdb != null) {
			graphdb.shutdown();
			graphdb = null;
		}
		if (graphdbNoTx != null) {
			graphdbNoTx.shutdown();
			graphdbNoTx = null;
		}
	}

	@Override
	public boolean isOpen() {
		return (graphdb != null && ! graphdb.isClosed()) ;
	}

	@Override
	public void open() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws InfobaseDatabaseRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void assertConnectionOpened() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfoClass createInfoClass(String infoClassName, InfoClass superclass) throws InfobaseDatabaseException {
		assertObjectType(infoClassName, superclass.getName());
		return getInfoClass(infoClassName);
	}

	@Override
	public void save(InfoObject infoObject) {
		OrientVertex v = (OrientVertex) infoObject.getRawObject();
		v.save();
	}

	@Override
	public Object getValue(Object rawObject, String name) {
		return ((OrientVertex) rawObject).getProperty(name);
	}

	@Override
	public void setValue(Object rawObject, String name, Object value) {
		((OrientVertex) rawObject).setProperty(name, value);
	}
}
