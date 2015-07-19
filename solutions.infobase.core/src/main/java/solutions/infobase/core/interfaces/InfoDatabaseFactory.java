package solutions.infobase.core.interfaces;

import org.apache.commons.configuration.Configuration;

public interface InfoDatabaseFactory extends InfoObjectFactory {
	void setConfiguration(Configuration config, String databaseName);
	InfoDatabase newDatabase();
}
