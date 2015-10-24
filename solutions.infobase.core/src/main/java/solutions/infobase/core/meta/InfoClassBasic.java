package solutions.infobase.core.meta;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;

public class InfoClassBasic extends InfoObjectBasic implements InfoClass {
	
	String name = "";
	InfoClass superclass = null;
	String superclassname = "";
	Object rawclasstype = null;
	
	public InfoClassBasic(InfoDatabase database, Object rawObject, String classname, InfoClass superclass) {
		super(database, rawObject);
		this.name = classname;
		setValue(Infobase.CLASS_NAME, classname);
		setInfoClass(this);
		this.superclass = superclass;
		superclassname = superclass.getName();
	}
	
	public InfoClassBasic(InfoDatabase database, Object rawObject, String classname) {
		super(database, rawObject);
		name = classname;
		setValue(Infobase.CLASS_NAME, classname);
		setInfoClass(this);
	}
	
	public InfoClass getSuperclass() {
		return superclass;
	}

	@Override
	public String getSuperClassName() {
		return superclassname;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public InfoAttribute getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoAttribute newAttribute(String name, AttributeType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getRawClassType() {
		return rawclasstype;
	}

	@Override
	public void setRawClassType(Object rawclasstype) {
		this.rawclasstype = rawclasstype;
	}
}
