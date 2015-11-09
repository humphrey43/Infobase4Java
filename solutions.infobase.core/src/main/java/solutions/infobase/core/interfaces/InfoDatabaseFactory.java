package solutions.infobase.core.interfaces;

import org.apache.commons.configuration.Configuration;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;

public interface InfoDatabaseFactory {
	void setConfiguration(Configuration config, String databaseName);
	InfoDatabase newDatabase(String databaseName) throws InfobaseDatabaseException;
	InfoDatabase newBasicDatabase(String databaseName) throws InfobaseDatabaseException;
}
