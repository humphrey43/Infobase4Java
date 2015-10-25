package solutions.infobase.core.meta;

import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;

/**
 * Hello world!
 *
 */
public class InfoObjectBasic implements InfoObject {

	public InfoObjectBasic() {
	}
	
	@Override
	public String getString(String name) {
		// TODO Auto-generated method stub
		return null;
	}

//	public InfoObjectBasic(Object rawObject) {
//		this.rawObject = rawObject;
//	}
	public InfoObjectBasic(InfoDatabase database, Object rawObject) {
		this.rawObject = rawObject;
		this.database = database;
	}
	
	protected InfoClass infoClass = null;
	@Override
	public InfoClass getInfoClass() {
		return infoClass;
	}
	public void setInfoClass(InfoClass infoClass) {
		this.infoClass = infoClass;
		this.infoClassName = infoClass.getName();
	}
	
	protected String infoClassName = "";
	@Override
	public String getInfoClassName() {
		return infoClassName;
	}
	public void setInfoClassName(String infoClassName) {
		this.infoClassName = infoClassName;
	}
	
	protected Object rawObject = null;
	@Override
	public Object getRawObject() {
		return rawObject;
	}
	@Override
	public void setRawObject(Object rawObject) {
		this.rawObject = rawObject;
	}
	
	protected InfoDatabase database = null;
	@Override
	public void setDatabase(InfoDatabase database) {
		this.database = database;
	}

	@Override
	public InfoDatabase getDatabase() {
		return database;
	}
	
	@Override
	public void setValue(String name, Object value) {
		database.setValue(rawObject, name, value);
	}

	@Override
	public Object getValue(String name) {
		InfoAttribute attr = infoClass.getAttribute(name);
		if (attr != null) {
			return database.getValue(rawObject, name);
		} else {
			throw new InfobaseDatabaseRuntimeException("Unknown attribute: " + name);
		}
	}

	@Override
	public void save() {
		database.save(this);
	}

}
