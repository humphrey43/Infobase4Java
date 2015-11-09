package solutions.infobase.core.interfaces;

import java.util.List;
import java.util.Map;

import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.Infobase.CardinalityType;
import solutions.infobase.core.Infobase.OptionalityType;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.exceptions.InfobaseException;

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
	 *	Access to the database
	 */

	InfoClass getInfoClass(String classname) throws InfobaseDatabaseException;
	InfoClass getInfoClassId(String infoId) throws InfobaseDatabaseException;
	InfoClass readInfoClass(String classname) throws InfobaseDatabaseException;
	InfoClass readInfoClassId(String infoId) throws InfobaseDatabaseException;
	InfoClass createInfoClass(String classname, InfoClass superclass) throws InfobaseDatabaseException;

	InfoAttribute createInfoAttribute(InfoClass infoclass, String name, AttributeType type) throws InfobaseDatabaseException;

	void save(InfoObject infoObject);
	InfoObject newInfoObject(InfoClass infoClass);
	InfoObject newInfoObject(String infoClassName) throws InfobaseException;
	Object getValue(Object rawObject, String name);
	void setValue(Object rawObject, String name, Object value);
	List<InfoObject> queryObjects(String query) throws InfobaseDatabaseException;
	InfoObject queryObject(String query) throws InfobaseDatabaseException;
//	public List<InfoAttribute> readAttributes(String classname) throws InfobaseDatabaseException;
	void assertMetadata() throws InfobaseDatabaseException;
	void clearMetadata() throws InfobaseDatabaseException;
	void createMetadata() throws InfobaseDatabaseException;
	List<InfoAttribute> readAttributes(InfoClass infoclass) throws InfobaseDatabaseException;
	String getInfoId(Object rawObject);
	InfoClass getClassFrom(InfoRelationship relationship);
	InfoClass getClassFrom(InfoRelationship relationship, InfoClass start);
	InfoClass getClassTo(InfoRelationship relationship);
	InfoClass getClassTo(InfoRelationship relationship, InfoClass start);
	InfoRelationship getRelationship(String relationName) throws InfobaseDatabaseException;
	InfoRelationship createRelationship(String relationName, InfoClass c1, InfoClass c2, CardinalityType cardinalityFrom, OptionalityType optionalityFrom, CardinalityType cardinalityTo, OptionalityType optionalityTo, boolean directional) throws InfobaseDatabaseException;
}
