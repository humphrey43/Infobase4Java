package solutions.infobase.core.interfaces;

public interface IDocument {
	public IDocumentClass getInfoClass();
	public String getInfoClassName();
	public Object getRawObject();
	
	public IDatabase getDatabase();
}