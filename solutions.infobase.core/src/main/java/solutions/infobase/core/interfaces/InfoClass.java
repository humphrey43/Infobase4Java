package solutions.infobase.core.interfaces;

import solutions.infobase.core.Infobase.AttributeType;

public interface InfoClass extends InfoObject {
	String getName();
	InfoClass getSuperclass();
	String getSuperClassName();
	Object getRawClassType();
	void setRawClassType(Object rawclasstype);
	InfoAttribute getAttribute(String name);
	void setAttribute(InfoAttribute attribute);
}
