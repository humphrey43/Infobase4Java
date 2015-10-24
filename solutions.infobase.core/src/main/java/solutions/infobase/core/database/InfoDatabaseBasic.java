package solutions.infobase.core.database;

import java.util.HashMap;
import java.util.Map;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.interfaces.InfoObjectFactory;

public abstract class InfoDatabaseBasic implements InfoDatabase {
	
	protected long useCounter = 0;
    protected Exception lastException = null;
    protected boolean inError;
	
//	public static InfoDatabase assertConnected(String databaseName) throws InfobaseDatabaseException {
//		InfoDatabaseFactory factory = getFactory(databaseName);
//		InfoDatabase database = factory.newDatabase();
//		database.setDatabaseName(databaseName);
//		return database;
//	}
//	
//	protected static IDatabaseFactory createFactory(String name) throws InfobaseDatabaseException {
//		IDatabaseFactory erg = null;
//		if (config == null) {
//			DefaultConfigurationBuilder configbuilder;
//			try {
//				configbuilder = new DefaultConfigurationBuilder("config.xml");
//				config = configbuilder.getConfiguration();
//			} catch (ConfigurationException e) {
//				throw new InfobaseDatabaseException("Configuration not found", e);
//			}
//		}
//		String classname = config.getString("dbconfig.databaseFactory","");
//		try {
//			erg = (IDatabaseFactory) Class.forName(classname).newInstance();
//			erg.setConfiguration(config);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			throw new InfobaseDatabaseException("DatabaseFactory not found: " + classname, e);
//		}
//		return erg;
//	}

//	public static InfoDatabaseFactory getFactory(String databaseName) throws InfobaseDatabaseException {
//		if (databaseFactories == null) {
//			databaseFactories = new LinkedHashMap<>();
//		}
//		InfoDatabaseFactory factory = databaseFactories.get(databaseName);
//		if (factory == null) {
//			if (config == null) {
//				DefaultConfigurationBuilder configbuilder;
//				try {
//					configbuilder = new DefaultConfigurationBuilder("config.xml");
//					config = configbuilder.getConfiguration();
//				} catch (ConfigurationException e) {
//					throw new InfobaseDatabaseException("Configuration not found", e);
//				}
//			}
//			String classname = config.getString("dbconfig.databaseFactory","");
//			try {
//				factory = (InfoDatabaseFactory) Class.forName(classname).newInstance();
//				factory.setConfiguration(config);
//			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//				throw new InfobaseDatabaseException("DatabaseFactory not found: " + classname, e);
//			}
//			databaseFactories.put(databaseName, factory);
//		}
//		return factory;
//	}

    protected String databaseName;
	@Override
	public String getDatabaseName() {
		return databaseName;
	}

	@Override
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

//	protected InfoObjectFactory infoObjectFactory;
	protected Map<String, InfoClass> infoClasses;
	public InfoDatabaseBasic() {
//		this.infoObjectFactory = infoObjectFactory;
		infoClasses = new HashMap<>();
	    setOk();
	    useCounter = 0L;
	}

	@Override
	public InfoClass getInfoClass(String classname) throws InfobaseDatabaseException {
		InfoClass erg = infoClasses.get(classname);
		if (erg == null) {
			erg = readInfoClass(classname);
			infoClasses.put(classname, erg);
		}
		if (erg == null) {
			throw new InfobaseDatabaseException("Class " + classname + " not found");
		}
		return erg;
	}
	
	public InfoClass getInfoClass2(String classname) throws InfobaseDatabaseException {
		InfoClass erg = infoClasses.get(classname);
		if (erg == null) {
			erg = readInfoClass(classname);
		}
		if (erg == null) {
			throw new InfobaseDatabaseException("Class " + classname + " not found");
		}
		return erg;
	}
	
    public long useConnection() throws InfobaseDatabaseException {
		assertConnectionOpened();
        useCounter++;
        return useCounter;
    }

    public void releaseConnection(long useCount) throws InfobaseDatabaseException {
        if (useCount == useCounter) {
            useCounter--;
            if (useCounter == 0) {
                assertConnectionClosed();
            }
        } else {
        	InfobaseDatabaseException e = new InfobaseDatabaseException("falsche Reihenfolge beim Freigeben");
            setError(e);
            assertConnectionClosed();
            useCounter = 0;
            throw e;
        }
    }

   public void startTransaction() throws InfobaseDatabaseException {
	    if (useCounter == 0) {
	    	assertConnectionOpened();
	     	useCounter++;
	    }
    }

    public void endTransaction() throws InfobaseDatabaseException {
        releaseConnection(1L);
    }

    protected abstract void assertConnectionOpened() throws InfobaseDatabaseException;

	public void assertConnectionClosed() throws InfobaseDatabaseException {
        if (isOpen()) {
        	if (inError) {
        		rollback();
        	} else {
        		commit();
        	}
        	finish();
        }
    }
	
	protected abstract void finish() throws InfobaseDatabaseRuntimeException;
	
	public boolean isInError() {
        return inError;
    }

    protected void setOk() {
        inError = false;
        lastException = null;
    }

    public void setError(Exception e) {
        if (lastException == null) {
            lastException = e;
        }
        inError = true;
    }

    @Override
    public void setError() {
        inError = true;
    }
    
	public String getConfigValue(String name) {
		return Infobase.getConfigValue(databaseName, name, "");
	}

	public String getConfigValue(String name, String defaultValue) {
		return Infobase.getConfigValue(databaseName, name, defaultValue);
	}
	
}
