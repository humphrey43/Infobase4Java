package solutions.infobase.core.interfaces;

public interface IDocumentClass extends IDocument {
	IDocumentClass getSuperclass();
	IDocument newDocument();
}
