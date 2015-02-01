package solutions.infobase.orientdb;

import org.apache.commons.configuration.Configuration;

import com.orientechnologies.orient.core.db.ODatabaseDocumentInternal;
import com.orientechnologies.orient.core.db.ODatabaseThreadLocalFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;

import solutions.infobase.core.interfaces.IDatabase;
import solutions.infobase.core.interfaces.IDatabaseFactory;

public class InfobaseDatabaseFactoryOrientDB implements IDatabaseFactory, ODatabaseThreadLocalFactory {

	protected Configuration config;
	protected ODatabaseThreadLocalFactory factory = null;
	public InfobaseDatabaseFactoryOrientDB() {
	}
	
	public IDatabase newDatabase() {
		return new InfobaseDatabaseOrientDB(config);
	}

	public void setConfiguration(Configuration config) {
		this.config = config;
	}

	@Override
	public ODatabaseDocumentInternal getThreadDatabase() {
		// TODO Auto-generated method stub
		return ODatabaseDocumentPool.global().aquire();
	}

}
