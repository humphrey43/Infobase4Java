package solutions.infobase.orientdb;

import org.apache.commons.configuration.Configuration;

import solutions.infobase.core.database.InfobaseDatabase;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.IDocumentClass;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

@SuppressWarnings("deprecation")
public class InfobaseDatabaseOrientDB extends InfobaseDatabase {

	private String dburl ;
	private String userid;
	private String password;
	
	Graph graphdb = null;
    protected boolean inError;
    
	
	public InfobaseDatabaseOrientDB(Configuration config) {
		super(config);
		dburl = config.getString("dbconfig.url", "");
		userid = config.getString("dbconfig.userid", "");
		password = config.getString("dbconfig.password", "");
		createConnection();
        setOk();
        useCounter = 0L;
	}
	
	public void open() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void close() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void rollback() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
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

	protected long useCounter = 0;
    protected Exception lastException = null;

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
    public long useConnection() throws InfobaseDatabaseException {
		assertConnectionOpened();
        useCounter++;
        return useCounter;
    }

    public void releaseConnection(long useCount) throws InfobaseDatabaseException {
        if (useCount == useCounter) {
            useCounter--;
            if (useCounter == 0L) {
                assertConnectionClosed();
            }
        } else {
        	InfobaseDatabaseException e = new InfobaseDatabaseException("falsche Reihenfolge beim Freigeben");
            setInError(e);
            assertConnectionClosed();
            throw e;
        }
    }

   public void startTransaction() throws InfobaseDatabaseException {
        boolean erg = false;
        if (useCounter == 0L) {
        	assertConnectionOpened();
        	graphdb.begin(TXTYPE.OPTIMISTIC);
        	useCounter++;
            erg = true;
        }
    }

    public void endTransaction() throws InfobaseDatabaseException {
        releaseConnection(1L);
    }

    public boolean isClosed() {
        boolean erg = true;
        try {
        	if (graphdb != null) {
        		erg = false; 
        	}
        }
        catch (Exception exception) { }
        return erg;
    }
	
    protected void assertConnectionOpened() throws InfobaseDatabaseException {
        if (graphdb == null) {
            createConnection();
        }
        if (graphdb != null && isClosed()) {
            openConnection();
            setOk();
        }
    }
    
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	public void openConnection() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	protected void createConnection() throws InfobaseDatabaseException {
		if (graphdb == null) {
			graphdb = ODatabaseDocumentPool.global().acquire(dburl, userid, password);
		}
	}

	public void assertConnectionClosed() throws InfobaseDatabaseException {
        if (graphdb != null) {
        	if (inError) {
        		graphdb.rollback();
        	} else {
        		graphdb.commit();
        	}
        	graphdb.close();
        	graphdb = null;
        }
        useCounter = 0L;
    }
    public boolean isInError() {
        return inError;
    }

    protected void setOk() {
        inError = false;
        lastException = null;
    }

    public void setInError(Exception e) {
        if (lastException == null) {
            lastException = e;
        }
        inError = true;
    }

    public void setInError() {
        inError = true;
    }

    public void finished() throws InfobaseDatabaseException {
    	assertConnectionClosed();
    	graphdb.close();
    }

	public void commit() throws InfobaseDatabaseException {
		graphdb.commit();
	}

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
			assertObjectType(name1);
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
	
	public void assertObjectType(String name) throws InfobaseDatabaseException {
		OClass c = graphdb.getUnderlying().getUnderlying().getVertexType(name);
		if (c == null) {
			graphdb.createVertexType(name);
		}
		
	}
	
	public void assertObjectType(String name, String superclass) throws InfobaseDatabaseException {
		OClass c = graphdb.getVertexType(name);
		if (c == null) {
			graphdb.createVertexType(name, superclass);
		}
		
	}
	
	public void assertRelationType(String name) throws InfobaseDatabaseException {
		OClass c = graphdb.getEdgeType(name);
		if (c == null) {
			graphdb.createEdgeType(name);
		}
		
	}
	
	public void assertRelationType(String name, String superclass) throws InfobaseDatabaseException {
		OClass c = graphdb.getEdgeType(name);
		if (c == null) {
			graphdb.createEdgeType(name, superclass);
		}
		
	}

	@Override
	public IDocumentClass readDocumentClass(String classname) throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		return null;
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

}
