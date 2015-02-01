package solutions.infobase.core.interfaces;

import solutions.infobase.core.InfobaseDocumentClass;


public interface IDocumentFactory {
	IDocument newDocument(String infoClassName);
	IDocument newDocument(InfobaseDocumentClass infoClass);
	IDocument newDocument(Object rawObject);
}
