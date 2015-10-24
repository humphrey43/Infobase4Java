package solutions.infobase.test.simple;

import org.apache.commons.configuration.Configuration;

import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.interfaces.InfoObjectFactory;

public class InfoObjectFactoryTest implements InfoObjectFactory {

//	@Override
//	public InfoObject newInfoObject(String infoClassName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public InfoObject newInfoObject(InfoClass infoClass) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public InfoObject newInfoObject(Object rawObject) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public void setConfiguration(Configuration config, String databaseName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfoObject newInfoObject(InfoDatabase database, Object rawObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
