package solutions.infobase.test.simple;

import solutions.infobase.core.database.InfoDatabaseBasic;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.exceptions.InfobaseDatabaseRuntimeException;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoObjectFactory;
import solutions.infobase.flexdata.FlexData;
import solutions.infobase.flexdata.IFlexData;

public class InfoDatabaseTest extends InfoDatabaseBasic {

	public InfoDatabaseTest(InfoObjectFactory infoObjectFactory) {
		super(infoObjectFactory);
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
	public void initBasicMetadata() {
		// TODO Auto-generated method stub
		
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

}
