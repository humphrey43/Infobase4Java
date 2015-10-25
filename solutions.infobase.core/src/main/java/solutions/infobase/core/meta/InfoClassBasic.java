package solutions.infobase.core.meta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;

public class InfoClassBasic extends InfoObjectBasic implements InfoClass {
	
	String name = "";
	InfoClass superclass = null;
	String superclassname = "";
	Object rawclasstype = null;
	Map<String, InfoAttribute> attributes;
	
	public InfoClassBasic(InfoDatabase database, Object rawObject, String classname, InfoClass superclass) {
		super(database, rawObject);
		this.name = classname;
		setValue(Infobase.CLASS_NAME, classname);
		setInfoClass(this);
		this.superclass = superclass;
		superclassname = superclass.getName();
		readAttributes();
	}
	
	public InfoClassBasic(InfoDatabase database, Object rawObject, String classname) {
		super(database, rawObject);
		name = classname;
		setValue(Infobase.CLASS_NAME, classname);
		setInfoClass(this);
		readAttributes();
	}
	
	protected void readAttributes() {
		attributes = new LinkedHashMap<>();
		try {
			List<InfoAttribute> la = database.readAttributes(name);
			for (InfoAttribute a : la) {
				attributes.put(a.getName(), a);
			}
		} catch (InfobaseDatabaseException e) {
			e.printStackTrace();
		}
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
		return attributes.get(name);
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
