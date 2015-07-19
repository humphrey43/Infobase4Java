package solutions.infobase.core.meta;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;

public class InfoClassBasic implements InfoClass {
	
	InfoClass superclass = null;
	InfoObject baseObject = null;
	String name = "";
	
	public InfoClassBasic() {
	}
	
	public InfoClassBasic(InfoObject baseObject) {
		this.baseObject = baseObject;
		this.name = baseObject.getString(Infobase.CLASS_NAME);
	}
	
	public InfoClassBasic(String name) {
		this.name = name;
	}
	
	@Override
	public InfoObject newObject() {
		return null; // ((InfoObjectFactory) database).newObject(this);
	}

	public InfoClass getSuperclass() {
		return superclass;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public InfoClass getInfoClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfoClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getRawObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoDatabase getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
