package solutions.infobase.test.simple;

import java.util.List;

import solutions.infobase.core.database.InfoDatabaseBasic;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoObject;
import solutions.infobase.core.interfaces.InfoObjectFactory;
import solutions.infobase.flexdata.FlexData;
import solutions.infobase.flexdata.IFlexData;

public class InfoDatabaseTest extends InfoDatabaseBasic {

	public InfoDatabaseTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected static IFlexData objects = null;
	
	@Override
	public void open() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws InfobaseDatabaseRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTransaction() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endTransaction() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getRawDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoClass readInfoClass(String classname)
			throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	protected void assertDatabase() {
		if (objects == null) {
			objects = new FlexData();
		}
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void finish() throws InfobaseDatabaseRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfoClass createInfoClass(String classname, InfoClass superclass) throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void assertConnectionOpened() throws InfobaseDatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(InfoObject infoObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfoObject newInfoObject(InfoClass infoClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(Object rawObject, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object rawObject, String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InfoObject> queryObjects(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoObject queryObject(String command) {
		// TODO Auto-generated method stub
		return null;
	}

}
