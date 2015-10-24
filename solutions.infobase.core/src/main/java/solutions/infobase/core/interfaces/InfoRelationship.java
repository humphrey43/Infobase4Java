package solutions.infobase.core.interfaces;

public interface InfoRelationship {
	public InfoClass getInfoClass();
	public String getInfoClassName();
	public Object getRawObject();
	
	public InfoDatabase getDatabase();
	
	public void setValue(String name, Object value);
	public Object getValue(String name);
	public String getString(String name);
	
	public void save();

}
