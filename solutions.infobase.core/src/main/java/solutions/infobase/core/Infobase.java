package solutions.infobase.core;

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
import solutions.infobase.core.interfaces.InfoObjectFactory;

public class Infobase {

	public final static String OBJECT_INFO_CLASS_NAME = "infoclassname";
	public static final String OBJECT_SUPER_CLASS_NAME = "superclassname";
	
	public static final String CLASS_NAME = "name";
	
	public static final String ATTRIBUTE_NAME = "name";
	public static final String ATTRIBUTE_DESCRIBED_CLASS_NAME = "describedclassname";
	public static final String ATTRIBUTE_TYPE = "type";
	public static final String ATTRIBUTE_DESIGNATION = "designation";
	public static final String ATTRIBUTE_DESCRIPTION = "description";
	public static final String ATTRIBUTES = "attributes";
	
	public static final String RELATION_HAS_ATTRIBUTE = "hasAttribute";
	
	protected static Map<String, InfoDatabaseFactory> databaseFactories = null;
	protected static Map<String, InfoObjectFactory> objectFactories = null;
	protected static Configuration config = null;
	
	public enum AttributeType {
		INTEGER,
		DECIMAL,
		STRING,
		LANGUAGE_STRING,
		TEXT,
		BOOLEAN,
		DATE,
		TIMESTAMP,
		TIME,
		OBJECT,
		RELATIONSHIP
	}
	
	public static Configuration getConfig() {
		return config;
	}

	public static InfoDatabaseFactory getDatabaseFactory(String databaseName) throws InfobaseDatabaseException {
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
	
	public static InfoObjectFactory getObjectFactory(String databaseName) throws InfobaseDatabaseException {
		if (objectFactories == null) {
			objectFactories = new LinkedHashMap<>();
		}
		InfoObjectFactory factory = objectFactories.get(databaseName);
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
			String classname = config.getString("dbconfig[@name='" + databaseName + "']/objectFactory","");
			try {
				factory = (InfoObjectFactory) Class.forName(classname).newInstance();
				factory.setConfiguration(config, databaseName);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new InfobaseDatabaseException("ObjectFactory for database " + databaseName + " not found: " + classname, e);
			}
			objectFactories.put(databaseName, factory);
		}
		return factory;
	}
	
	public static InfoDatabase getDatabase(String databaseName) throws InfobaseDatabaseException {
		InfoDatabaseFactory factory = getDatabaseFactory(databaseName);
		InfoObjectFactory of =  getObjectFactory(databaseName);
		InfoDatabase database = factory.newDatabase(databaseName);
		return database;
	}

	public static InfoDatabase getEmptyDatabase(String databaseName) throws InfobaseDatabaseException {
		InfoDatabaseFactory factory = getDatabaseFactory(databaseName);
		InfoObjectFactory of =  getObjectFactory(databaseName);
		InfoDatabase database = factory.newEmptyDatabase(databaseName);
		return database;
	}

	public static String getConfigValue(String databaseName, String name) {
		return getConfigValue(databaseName, name, "");
	}

	public static String getConfigValue(String databaseName, String name, String defaultValue) {
		return config.getString("dbconfig[@name='" + databaseName + "']/" + name,defaultValue);
	}
}
