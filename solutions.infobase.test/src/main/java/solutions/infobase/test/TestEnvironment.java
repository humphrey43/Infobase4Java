package solutions.infobase.test;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoDatabaseFactory;

public class TestEnvironment {

	static InfoDatabase database = null;
	
	public static InfoDatabase getDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ConfigurationException {
		if (database == null) {
				try {
					database = Infobase.getDatabase("test");
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return database;
	}
	
	public static void clearDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ConfigurationException {
		getDatabase();
	}
	
	public static void clearDatabaseFile() {
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
