package solutions.infobase.core.interfaces;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;

public interface IDatabase {
	void open() throws InfobaseDatabaseException;
	void close() throws InfobaseDatabaseRuntimeException;
	
	void startTransaction() throws InfobaseDatabaseRuntimeException;
	void endTransaction() throws InfobaseDatabaseRuntimeException;
	void commit() throws InfobaseDatabaseRuntimeException;
	void rollback() throws InfobaseDatabaseRuntimeException;
	Object getRawDatabase();
	
	IDocumentClass createDocumentClass(String classname, IDocumentClass superclass) throws InfobaseDatabaseException;
	IDocumentClass getDocumentClass(String classname) throws InfobaseDatabaseException;
	IDocumentClass readDocumentClass(String classname) throws InfobaseDatabaseException;
}
