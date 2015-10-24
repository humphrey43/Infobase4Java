package solutions.infobase.core.interfaces;

public interface InfoObject {
	public InfoClass getInfoClass();
	public String getInfoClassName();
	public Object getRawObject();
	public void setRawObject(Object rawObject);
	
	public InfoDatabase getDatabase();
	public void setDatabase(InfoDatabase database);
	
	public void setValue(String name, Object value);
	public Object getValue(String name);
	public String getString(String name);
	
	public void save();
}