package solutions.infobase.core.database;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;

import solutions.infobase.core.InfobaseDocumentClass;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.IDatabase;
import solutions.infobase.core.interfaces.IDocument;
import solutions.infobase.core.interfaces.IDocumentClass;
import solutions.infobase.core.interfaces.IDocumentFactory;

public abstract class InfobaseDatabase implements IDatabase, IDocumentFactory {
	
	protected IDocumentFactory documentFactory;
	protected Configuration config;
	protected Map<String, IDocumentClass> documentClasses;
	public InfobaseDatabase(Configuration config) {
		try {
			this.config = config;
			String documentfactoryClassname = config.getString("dbconfig.documentFactory","");
			documentFactory = (IDocumentFactory) Class.forName(documentfactoryClassname).newInstance();
			documentClasses = new HashMap<>();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IDocument newDocument(String infoClassName) {
		return documentFactory.newDocument(infoClassName);
	}
	
	public IDocument newDocument(InfobaseDocumentClass infoClass) {
		return documentFactory.newDocument(infoClass);
	}
	
	public IDocument newDocument(Object rawObject) {
		return documentFactory.newDocument(rawObject);
	}

	public IDocumentClass createDocumentClass(String classname, IDocumentClass superclass) {
		// TODO Auto-generated method stub
		return null;
	}

	public IDocumentClass getDocumentClass(String classname) {
		IDocumentClass erg = documentClasses.get(classname);
		if (erg == null) {
			erg = readDocumentClass(classname);
		}
		if (erg == null) {
			throw new InfobaseDatabaseException("Class " + classname + " not found");
		}
		return null;
	}
	
}
