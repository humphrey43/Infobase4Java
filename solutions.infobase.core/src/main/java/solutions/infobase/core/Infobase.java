package solutions.infobase.core;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoDatabaseFactory;

public class Infobase {

	public final static String CLASS_NAME = "name";
	
	protected static Map<String, InfoDatabaseFactory> databaseFactories = null;
	protected static Configuration config = null;
	SimpleDateFormat df;
	
	public static Configuration getConfig() {
		return config;
	}

	public static InfoDatabaseFactory getFactory(String databaseName) throws InfobaseDatabaseException {
		if (databaseFactories == null) {
			databaseFactories = new LinkedHashMap<>();
		}
		InfoDatabaseFactory factory = databaseFactories.get(databaseName);
		if (factory == null) {
			if (config == null) {
				DefaultConfigurationBuilder configbuilder;
				try {
					configbuilder = new DefaultConfigurationBuilder("config.xml");
					HierarchicalConfiguration c = (HierarchicalConfiguration) configbuilder.getConfiguration();
					c.setExpressionEngine(new XPathExpressionEngine());
					config = c;
				} catch (ConfigurationException e) {
					throw new InfobaseDatabaseException("Configuration not found", e);
				}
			}
			String classname = config.getString("dbconfig[@name='" + databaseName + "']/databaseFactory","");
			try {
				factory = (InfoDatabaseFactory) Class.forName(classname).newInstance();
				factory.setConfiguration(config, databaseName);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new InfobaseDatabaseException("DatabaseFactory for database " + databaseName + " not found: " + classname, e);
			}
			databaseFactories.put(databaseName, factory);
		}
		return factory;
	}
	
	public static InfoDatabase getConnection(String databaseName) throws InfobaseDatabaseException {
		InfoDatabaseFactory factory = getFactory(databaseName);
		InfoDatabase database = factory.newDatabase();
		database.setDatabaseName(databaseName);
		return database;
	}
	
//	public static InfoDatabase assertConnected(String databaseName) throws InfobaseDatabaseException {
//	InfoDatabaseFactory factory = getFactory(databaseName);
//	InfoDatabase database = factory.newDatabase();
//	database.setDatabaseName(databaseName);
//	return database;
//}
}
