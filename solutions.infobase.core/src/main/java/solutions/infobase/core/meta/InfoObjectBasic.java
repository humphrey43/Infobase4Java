package solutions.infobase.core.meta;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;

/**
 * Hello world!
 *
 */
public class InfoObjectBasic implements InfoObject {

	public InfoObjectBasic() {
	}
	
//	public InfoObjectBasic(Object rawObject) {
//		this.rawObject = rawObject;
//	}
	public InfoObjectBasic(InfoDatabase database, Object rawObject) {
		this.rawObject = rawObject;
		this.database = database;
//		this.infoClassName = (String) database.getValue(rawObject, Infobase.OBJECT_INFO_CLASS_NAME);
	}
	
	protected InfoClass infoClass = null;
	@Override
	public InfoClass getInfoClass() {
		return infoClass;
	}
	public void setInfoClass(InfoClass infoClass) {
		this.infoClass = infoClass;
		setInfoClassName(infoClass.getName());
	}
	
	protected String infoClassName = "";
	@Override
	public String getInfoClassName() {
		return infoClassName;
	}
	@Override
	public void setInfoClassName(String infoClassName) {
		this.infoClassName = infoClassName;
		String n = (String) getValueDirect(Infobase.OBJECT_INFO_CLASS_NAME);
		if (n == null || n.equals("")) setValueDirect(Infobase.OBJECT_INFO_CLASS_NAME, infoClassName);
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
		InfoAttribute attr = infoClass.getAttribute(name);
		if (attr != null || Infobase.ALL_META_CLASSES.indexOf("#" + infoClass.getName() + "#") >= 0) {
			database.setValue(rawObject, name, value);
		} else {
			throw new InfobaseDatabaseRuntimeException("Unknown attribute: " + name);
		}
	}

	@Override
	public void setValue(String name, boolean value) {
		InfoAttribute attr = infoClass.getAttribute(name);
		if (attr != null || Infobase.ALL_META_CLASSES.indexOf("#" + infoClass.getName() + "#") >= 0) {
			database.setValue(rawObject, name, value);
		} else {
			throw new InfobaseDatabaseRuntimeException("Unknown attribute: " + name);
		}
	}

	@Override
	public void setValueDirect(String name, Object value) {
		database.setValue(rawObject, name, value);
	}

	@Override
	public Object getValue(String name) {
		InfoAttribute attr = infoClass.getAttribute(name);
		if (attr != null || Infobase.ALL_META_CLASSES.indexOf("#" + infoClass.getName() + "#") >= 0) {
			return database.getValue(rawObject, name);
		} else {
			throw new InfobaseDatabaseRuntimeException("Unknown attribute: " + name);
		}
	}
	
	@Override
	public String getString(String name) {
		return getString(name, "");
	}
	
	@Override
	public String getString(String name, String defaultValue) {
		String erg = defaultValue;
		Object o = getValue(name);
		if (o != null) erg = o.toString();
		return erg;
	}
	
	@Override
	public long getLong(String name) {
		return getLong(name, 0);
	}
	
	@Override
	public long getLong(String name, long defaultValue) {
		long erg = defaultValue;
		Object o = getValue(name);
		if (o != null) erg = ((Long) o).longValue();
		return erg;
	}
	
	@Override
	public Object getValueDirect(String name) {
		return database.getValue(rawObject, name);
	}

	@Override
	public void save() {
		database.save(this);
	}

	@Override
	public String getInfoId() {
		return database.getInfoId(rawObject);
	}

}
