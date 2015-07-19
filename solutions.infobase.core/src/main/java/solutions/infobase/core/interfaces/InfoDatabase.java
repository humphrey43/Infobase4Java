package solutions.infobase.core.interfaces;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;

/**
 * @author hardy
 *
 */
/**
 * @author hardy
 *
 */
public interface InfoDatabase {

	/**
	 * @author hardy
	 *
	 *	database information
	 */
	String getDatabaseName();
	void setDatabaseName(String databaseName);
	Object getRawDatabase();
	
	/**
	 * @author hardy
	 *
	 *	Access to the database
	 */

	/**
	 * @author hardy
	 *
	 *	open
	 */
	void open() throws InfobaseDatabaseException;
	boolean isOpen();

	/**
	 * @author hardy
	 *
	 *	startTransaction
	 */
	void startTransaction() throws InfobaseDatabaseException;

	/**
	 * @author hardy
	 *
	 *	setError
	 */
	void setError();
	 
	/**
	 * @author hardy
	 *
	 *	setError(Exception e)
	 */
	void setError(Exception e);
	 
	/**
	 * @author hardy
	 *
	 *	commit
	 */
	void commit() throws InfobaseDatabaseException;
	 
	/**
	 * @author hardy
	 *
	 *	rollback
	 */
	void rollback() throws InfobaseDatabaseException;
	
	/**
	 * @author hardy
	 *
	 *	endTransaction
	 */
	void endTransaction() throws InfobaseDatabaseException;
	
	/**
	 * @author hardy
	 *
	 *	close
	 */
	void close() throws InfobaseDatabaseRuntimeException;
	boolean isClosed();

	/**
	 * @author hardy
	 *
	 */
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 */
	
	 
	/**
	 * @author hardy
	 *
	 *	initBasicMetadata
	 */
	void initBasicMetadata();

	
	InfoClass createInfoClass(String classname, InfoClass superclass) throws InfobaseDatabaseException;
	InfoClass getInfoClass(String classname) throws InfobaseDatabaseException;
	InfoClass readInfoClass(String classname) throws InfobaseDatabaseException;
}
