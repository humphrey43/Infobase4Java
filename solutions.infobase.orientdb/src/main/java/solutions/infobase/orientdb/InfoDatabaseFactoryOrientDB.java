package solutions.infobase.orientdb;

import org.apache.commons.configuration.Configuration;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoDatabaseFactory;
import solutions.infobase.core.interfaces.InfoObject;

public class InfoDatabaseFactoryOrientDB implements InfoDatabaseFactory {

	protected OrientGraphFactory factory = null;
	protected String dburl ;
	protected String userid;
	protected String password;
	protected int minPool;
	protected int maxPool;
	
	public InfoDatabaseFactoryOrientDB() {
	}
	
	public InfoDatabase newDatabase() {
		return new InfoDatabaseOrientDB();
	}

	public OrientGraph newConnection() {
		return factory.getTx();
	}

	public OrientGraphNoTx newAdminConnection() {
		return factory.getNoTx();
	}

	public void setConfiguration(Configuration config, String databaseName) {
		dburl = config.getString("dbconfig[@name='" + databaseName + "']/url", "");
		userid = config.getString("dbconfig[@name='" + databaseName + "']/userid", "");
		password = config.getString("dbconfig[@name='" + databaseName + "']/password", "");
		minPool = config.getInt("dbconfig[@name='" + databaseName + "']/minPool", 1);
		maxPool = config.getInt("dbconfig[@name='" + databaseName + "']/maxPool", 3);
//		OrientGraph graph = new OrientGraph(dburl);
//		graph.shutdown();
	    factory = new OrientGraphFactory(dburl, userid, password);
	    factory.setAutoStartTx(false);
		factory.setupPool(minPool, maxPool);
	}
}
