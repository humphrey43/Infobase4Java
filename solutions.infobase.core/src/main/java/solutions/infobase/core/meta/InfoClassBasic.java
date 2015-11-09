package solutions.infobase.core.meta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import solutions.infobase.core.Infobase;
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
		attributes = new LinkedHashMap<>();
		this.name = classname;
		setInfoClassName("InfoClass");
		String n = (String) getValueDirect(Infobase.CLASS_NAME);
		if (n == null || n.equals("")) setValueDirect(Infobase.CLASS_NAME, classname);
		this.superclass = superclass;
		superclassname = superclass.getName();
		readAttributes();
	}
	
	public InfoClassBasic(InfoDatabase database, Object rawObject, String classname) {
		super(database, rawObject);
		attributes = new LinkedHashMap<>();
		name = classname;
		setInfoClassName("InfoClass");
		readAttributes();
//		setValueDirect(Infobase.CLASS_NAME, classname);
	}
	
	protected void readAttributes() {
//		attributes = new LinkedHashMap<>();
		try {
			List<InfoAttribute> la = database.readAttributes(this);
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
	public void setAttribute(InfoAttribute attribute) {
		attributes.put(attribute.getName(), attribute);
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
