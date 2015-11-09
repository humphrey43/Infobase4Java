package solutions.infobase.core.interfaces;

import solutions.infobase.core.Infobase.AttributeType;

public interface InfoAttribute extends InfoObject {
	InfoClass getDescribedClass();
	void setDescribedClass(InfoClass describedclass);
	String getDescribedClassName();
	AttributeType getType();
	void setType(AttributeType type);
	String getName();
	void setName(String name);
	String getDesignation();
	void setDesignation(String designation);
	String getDescription();
	void setDescription(String description);
	void setTypeDirect(AttributeType type);
}
