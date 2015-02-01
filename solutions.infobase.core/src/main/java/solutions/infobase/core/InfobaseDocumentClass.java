package solutions.infobase.core;

import solutions.infobase.core.interfaces.IDocument;
import solutions.infobase.core.interfaces.IDocumentClass;
import solutions.infobase.core.interfaces.IDocumentFactory;

public class InfobaseDocumentClass extends InfobaseDocument implements IDocumentClass {
	
	InfobaseDocumentClass superclass = null;
	
	public InfobaseDocumentClass() {
	}
	
	public InfobaseDocumentClass(InfobaseDocumentClass superclass) {
		this.superclass = superclass;
	}
	
	public InfobaseDocumentClass(Object rawObject) {
		super(rawObject);
		this.superclass = superclass;
	}
	
	public IDocument newDocument() {
		return ((IDocumentFactory) database).newDocument(this);
	}

	public IDocumentClass getSuperclass() {
		return superclass;
	}
	

}
