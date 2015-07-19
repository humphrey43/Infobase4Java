package solutions.infobase.core;

import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.interfaces.InfoClass;

/**
 * Hello world!
 *
 */
public abstract class InfoObjectBasic implements InfoObject {

	protected InfoClass objectClass = null;
	
	public InfoObjectBasic() {
	}
	
	@Override
	public String getString(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public InfoObjectBasic(Object rawObject) {
		this.rawObject = rawObject;
	}
	public InfoObjectBasic(InfoDatabase database) {
		this.database = database;
	}
	
	protected InfoClass infoClass = null;
	@Override
	public InfoClass getInfoClass() {
		return infoClass;
	}
	
	protected String infoClassName = "";
	@Override
	public String getInfoClassName() {
		return infoClassName;
	}
	
	protected Object rawObject = null;
	@Override
	public Object getRawObject() {
		return rawObject;
	}
	
	protected InfoDatabase database = null;
	@Override
	public InfoDatabase getDatabase() {
		return database;
	}
	
	@Override
	public void save() {
		
	}
}
