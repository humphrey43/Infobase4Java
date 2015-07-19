package solutions.infobase.test;

import java.io.File;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoDatabase;

public class TestEnvironment {

	static InfoDatabase database = null;
	
	public static InfoDatabase getDatabase() {
		if (database == null) {
				try {
					database = Infobase.getConnection("test");
				} catch (InfobaseDatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				DefaultConfigurationBuilder configbuilder = new DefaultConfigurationBuilder("config.xml");
//				Configuration config = configbuilder.getConfiguration();
//				String dbpath = "demo";
//				String dbpath = "C:/temp/TestDB";
//				String dbpath = "D:/Downloads/orientdb-graphed-1.3.0.tar/orientdb-graphed-1.3.0/databases/TestDB";
//				String dburl = "memory:" + dbpath;
//				String dburl = "plocal:" + dbpath;
//	  			String dburl = "remote:localhost/" + dbpath;
//				String userid = "admin";
//				String password = "admin";
//				String databasefactoryClassname = config.getString("dbconfig[.databaseFactory","");
//				IDatabaseFactory databasefactory = (IDatabaseFactory) Class.forName(databasefactoryClassname).newInstance();
//				databasefactory.setConfiguration(config);
//				database =  databasefactory.newDatabase();
			
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
			} catch (InfobaseDatabaseRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		database = null;
	}
}
