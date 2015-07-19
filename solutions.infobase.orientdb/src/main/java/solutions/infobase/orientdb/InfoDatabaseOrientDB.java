package solutions.infobase.orientdb;

import solutions.infobase.core.database.InfoDatabaseBasic;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoObjectFactory;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class InfoDatabaseOrientDB extends InfoDatabaseBasic {

	OrientGraph graphdb = null;

	public InfoDatabaseOrientDB(OrientGraph graphdb, InfoObjectFactory infoObjectFactory) {
		super(infoObjectFactory);
		this.graphdb = graphdb;
        setOk();
        useCounter = 0L;
	}
	
	public void open() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void close() throws InfobaseDatabaseRuntimeException {
		this.graphdb.shutdown();
	}

	public Object getRawDatabase() {
		return graphdb;
	}

    protected String userId;
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

//	public OrientGraph getOrientGraph() throws DatabaseFrameException {
//		assertConnectionOpened();
//		return (OrientGraph) graphdb;
//	}
//
//	public OrientGraph getRawGraph() throws DatabaseFrameException {
//		assertConnectionOpened();
//		return ((OrientGraph) graphdb);
//	}
//
//	public FrameDatabase(String connectionId, String signonUserId, String signonPassword) {
//		this.connectionId = connectionId;
//		this.signonUserId = signonUserId;
//		this.signonPassword = signonPassword;
//        setOk();
//        useCounter = 0L;
//	}
//	
	public void assertDocumentType(String documentClassName) throws InfobaseDatabaseException {
		String name1 = documentClassName;
		int p = name1.lastIndexOf(".");
		p = name1.lastIndexOf(".",p-1);
		name1 = name1.substring(p+1);
		String name2 = "object";
		p = name2.lastIndexOf(".");
		p = name2.lastIndexOf(".",p-1);
		name2 = name2.substring(p+1);
//		if (name2.equals("core.FrameObject")) {
			assertObjectType(documentClassName);
//		} else {
//			assertObjectType(name1, name2);
//		}
	}
	
	public void assertRelationshipType(String relationshipClassName) throws InfobaseDatabaseException {
		String name1 = relationshipClassName;
		int p = name1.lastIndexOf(".");
		p = name1.lastIndexOf(".",p-1);
		name1 = name1.substring(p+1);
		String name2 = "object";
		p = name2.lastIndexOf(".");
		p = name2.lastIndexOf(".",p-1);
		name2 = name2.substring(p+1);
//		if (name2.equals("core.FrameRelation")) {
			assertRelationType(name1);
//		} else {
//			assertRelationType(name1, name2);
//		}
	}
	
	public ODocument createODocument(Class<?> class1) {
		ODocument erg = null;
		String name1 = class1.getName();
		int p = name1.lastIndexOf(".");
		p = name1.lastIndexOf(".",p-1);
		name1 = name1.substring(p+1);
		String name2 = class1.getSuperclass().getName();
		p = name2.lastIndexOf(".");
		p = name2.lastIndexOf(".",p-1);
		name2 = name2.substring(p+1);
//		if (name2.equals("core.FrameObject")) {
			erg = new ODocument(name1);
//		} else {
//			erg = new ODocument(name1,name2);
//		}
		return erg;
	}
	
	public OClass assertObjectType(String name) throws InfobaseDatabaseException {
		OClass c = graphdb.getVertexType(name);
		if (c == null) {
//			InfobaseDatabaseFactoryOrientDB factory = (InfobaseDatabaseFactoryOrientDB) InfobaseDatabase.getFactory(databaseName);
//			InfobaseDatabaseOrientDB db = (InfobaseDatabaseOrientDB) factory.newDatabase();
//			boolean isActive = graphdb.getRawGraph().getTransaction().isActive();
//			if (isActive) graphdb.getRawGraph().commit();
			c = graphdb.createVertexType(name);
//			if (isActive) graphdb.begin();
//			OrientGraphNoTx dbNoTx = factory.newDatabaseNoTx();
//			dbNoTx.createVertexType(name);
//			dbNoTx.shutdown();
		}
		return c;
	}
	
//	public void assertObjectType(String name, String superclass) throws InfobaseDatabaseException {
//		OClass c = graphdb.getVertexType(name);
//		if (c == null) {
//			graphdb.createVertexType(name, superclass);
//		}
//	}
	
	public void assertRelationType(String name) throws InfobaseDatabaseException {
		OClass c = graphdb.getEdgeType(name);
		if (c == null) {
			graphdb.createEdgeType(name);
		}
	}
	
	public void assertRelationType(String name, String superclass) throws InfobaseDatabaseException {
//		OClass c = graphdb.getEdgeType(name);
//		if (c == null) {
//			graphdb.createEdgeType(name, superclass);
//		}
//		
	}

//	public List<FrameObject> queryObjects(String query) throws DatabaseFrameException {
//		List<FrameObject> erg = new LinkedList<FrameObject>();
//		DatabaseFrameException exception = null;
//		try {
//			long dbcount = useConnection();
//			List<ODocument> result = ((OrientGraph) graphdb).getRawGraph().query(new OSQLSynchQuery<ODocument>(query));
//			for (ODocument od : result) {
//				FrameObject fo;
//				try {
//					Constructor<?> c;
//					 Constructor<?>[] cs = Class.forName((String) od.field(FrameBase.FRAME_CLASS)).getDeclaredConstructors();
//					c = Class.forName((String) od.field(FrameBase.FRAME_CLASS)).getConstructor(new Class[]{OrientBaseGraph.class,ODocument.class});
//					fo = (FrameObject)c.newInstance(new Object[]{this.getOrientGraph(), od});
//					erg.add(fo);
//				} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
//						| ClassNotFoundException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
//					exception = new DatabaseFrameException(e);
//					break;
//				}
//			}
//			releaseConnection(dbcount);
//		} catch (DatabaseFrameException e) {
//			exception = e;
//		}
//		if (exception != null) {
//			exception.printStackTrace();
//			throw exception;
//		}
//		return erg;
//	}
//
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

	@Override
	public void initBasicMetadata() {
		try {
			assertDocumentType("meta.InfoObject");
			assertDocumentType("meta.InfoClass");
			assertDocumentType("meta.InfoAttribute");
			
		} catch (InfobaseDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public InfoClass readInfoClass(String classname)
			throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		return null;
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
		graphdb.commit();
	}

	@Override
	public void rollback() throws InfobaseDatabaseException {
		graphdb.rollback();
	}

	@Override
	public void finish() {
		graphdb.shutdown();
	}

	@Override
	public boolean isOpen() {
		return ! graphdb.isClosed();
	}
}
