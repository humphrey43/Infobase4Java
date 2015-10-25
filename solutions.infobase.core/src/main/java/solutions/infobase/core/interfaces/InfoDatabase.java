package solutions.infobase.core.interfaces;

import java.util.List;
import java.util.Map;

import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;

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
	public String getDatabaseName();
	public Object getRawDatabase();
	
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
	public void open() throws InfobaseDatabaseException;
	public boolean isOpen();

	/**
	 * @author hardy
	 *
	 *	startTransaction
	 */
	public void startTransaction() throws InfobaseDatabaseException;

	/**
	 * @author hardy
	 *
	 *	setError
	 */
	public void setError();
	 
	/**
	 * @author hardy
	 *
	 *	setError(Exception e)
	 */
	public void setError(Exception e);
	 
	/**
	 * @author hardy
	 *
	 *	commit
	 */
	public void commit() throws InfobaseDatabaseException;
	 
	/**
	 * @author hardy
	 *
	 *	rollback
	 */
	public void rollback() throws InfobaseDatabaseException;
	
	/**
	 * @author hardy
	 *
	 *	endTransaction
	 */
	public void endTransaction() throws InfobaseDatabaseException;
	
	/**
	 * @author hardy
	 *
	 *	close
	 */
	public void close() throws InfobaseDatabaseRuntimeException;
	public boolean isClosed();

	/**
	 * @author hardy
	 *
	 *	Access to the database
	 */

	public InfoClass getInfoClass(String classname) throws InfobaseDatabaseException;
	
	public InfoClass readInfoClass(String classname) throws InfobaseDatabaseException;
	public InfoClass createInfoClass(String classname, InfoClass superclass) throws InfobaseDatabaseException;
	public void save(InfoObject infoObject);
	public InfoObject newInfoObject(InfoClass infoClass);
	public Object getValue(Object rawObject, String name);
	public void setValue(Object rawObject, String name, Object value);
	public List<InfoObject> queryObjects(String query) throws InfobaseDatabaseException;
	public InfoObject queryObject(String query) throws InfobaseDatabaseException;
	public List<InfoAttribute> readAttributes(String classname) throws InfobaseDatabaseException;
	public void assertMetadata() throws InfobaseDatabaseException;
	public void clearMetadata() throws InfobaseDatabaseException;
}
