package solutions.infobase.test.simple;

import org.apache.commons.configuration.Configuration;

import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoDatabaseFactory;
import solutions.infobase.core.interfaces.InfoObject;

public class InfoDatabaseFactoryTest implements InfoDatabaseFactory {

	protected static InfoDatabase database = null;
	protected static Configuration actualConfig = null;
	
	@Override
	public InfoDatabase newDatabase() {
		//assertDatabase();
		return database ;
	}

	@Override
	public InfoObject newObject(String infoClassName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoObject newObject(InfoClass infoClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoObject newObject(Object rawObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConfiguration(Configuration config, String databaseName) {
		// TODO Auto-generated method stub
		
	}

}
