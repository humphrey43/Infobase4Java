package solutions.infobase.core.meta;

import org.apache.commons.configuration.Configuration;

import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.interfaces.InfoObjectFactory;

public class InfoObjectFactoryBasic implements InfoObjectFactory {

	@Override
	public InfoObject newInfoObject(InfoDatabase database, Object rawObject) {
		return new InfoObjectBasic(database, rawObject);
	}

	@Override
	public void setConfiguration(Configuration config, String databaseName) {
		// dummy implementation
	}

}
