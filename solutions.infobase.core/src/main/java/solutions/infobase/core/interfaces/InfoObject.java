package solutions.infobase.core.interfaces;

public interface InfoObject {
	InfoClass getInfoClass();
	String getInfoId();
	String getInfoClassName();
	void setInfoClassName(String infoClassName);
	Object getRawObject();
	void setRawObject(Object rawObject);
	
	InfoDatabase getDatabase();
	void setDatabase(InfoDatabase database);
	
	void setValue(String name, Object value);
	void setValueDirect(String name, Object value);
	void setValue(String name, boolean value);
	Object getValue(String name);
	Object getValueDirect(String name);
	String getString(String name, String defaultValue);
	String getString(String name);
	
	void save();
	long getLong(String name);
	long getLong(String name, long defaultValue);
}