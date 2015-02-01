package solutions.infobase.test;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.IDatabase;
import solutions.infobase.core.interfaces.IDatabaseFactory;
import solutions.infobase.core.interfaces.IDocumentFactory;

public class TestEnvironment {

	static IDatabase database = null;
	
	public static IDatabase getDatabase() {
		if (database == null) {
			try {
				DefaultConfigurationBuilder configbuilder = new DefaultConfigurationBuilder("config.xml");
				Configuration config = configbuilder.getConfiguration();
				String dbpath = "demo";
//				String dbpath = "C:/temp/TestDB";
//				String dbpath = "D:/Downloads/orientdb-graphed-1.3.0.tar/orientdb-graphed-1.3.0/databases/TestDB";
				String dburl = "memory:" + dbpath;
//				String dburl = "plocal:" + dbpath;
//	  			String dburl = "remote:localhost/" + dbpath;
				String userid = "admin";
				String password = "admin";
				String databasefactoryClassname = config.getString("dbconfig.databaseFactory","");
				IDatabaseFactory databasefactory = (IDatabaseFactory) Class.forName(databasefactoryClassname).newInstance();
				databasefactory.setConfiguration(config);
				database =  databasefactory.newDatabase();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return database;
	}
	
	public static void clearDatabase() {
		String dbpath = "C:/temp/TestDB";
//		String dbpath = "D:/Downloads/orientdb-graphed-1.3.0.tar/orientdb-graphed-1.3.0/databases/TestDB";
//		String dburl = "local:" + dbpath;
		File f = new File(dbpath);
		if (f.exists()) {
			if (f.isDirectory()) {
				for (File g : f.listFiles()) {
					g.delete();
				}
			} 
			f.delete();
		}
	}
	
	public static void finish() {
		if (database != null) {
			try {
				database.close();
			} catch (InfobaseDatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InfobaseDatabaseRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		database = null;
	}
}
