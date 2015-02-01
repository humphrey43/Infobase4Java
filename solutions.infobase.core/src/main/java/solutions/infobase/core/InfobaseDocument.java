package solutions.infobase.core;

import solutions.infobase.core.interfaces.IDatabase;
import solutions.infobase.core.interfaces.IDocument;
import solutions.infobase.core.interfaces.IDocumentClass;

/**
 * Hello world!
 *
 */
public abstract class InfobaseDocument implements IDocument {

	public InfobaseDocument() {
	}
	
	public InfobaseDocument(Object rawObject) {
		this.rawObject = rawObject;
	}
	public InfobaseDocument(IDatabase database) {
		this.database = database;
	}
	
	protected InfobaseDocumentClass infoClass = null;
	public IDocumentClass getInfoClass() {
		return infoClass;
	}
	
	protected String infoClassName = "";
	public String getInfoClassName() {
		return infoClassName;
	}
	
	protected Object rawObject = null;
	public Object getRawObject() {
		return rawObject;
	}
	
	protected IDatabase database = null;
	public IDatabase getDatabase() {
		return database;
	}
}
