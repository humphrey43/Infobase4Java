package solutions.infobase.core.interfaces;

import org.apache.commons.configuration.Configuration;

public interface InfoObjectFactory {
	void setConfiguration(Configuration config, String databaseName);
	InfoObject newInfoObject(InfoDatabase database, Object rawObject);
}
