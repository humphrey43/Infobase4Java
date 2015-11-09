package solutions.infobase.core.interfaces;

import solutions.infobase.core.Infobase.CardinalityType;
import solutions.infobase.core.Infobase.OptionalityType;

public interface InfoRelationship extends InfoObject {
	String getName();
	void setName(String name);
	String getDesignationFrom();
	void setDesignationFrom(String designation);
	String getDesignationTo();
	void setDesignationTo(String designation);
	boolean isDirectional();
	void setDirectional(boolean directional);
	InfoClass getClassFrom();
	void setClassFrom(InfoClass infoClass);
	InfoClass getClassTo();
	void setClassTo(InfoClass infoClass);
	String getDescription();
	void setDescription(String description);
	CardinalityType getCardinalityFrom();
	void setCardinalityFrom(CardinalityType cardinality);
	CardinalityType getCardinalityTo();
	void setCardinalityTo(CardinalityType cardinality);
	OptionalityType getOptionalityFrom();
	void setOptionalityFrom(OptionalityType optionality);
	OptionalityType getOptionalityTo();
	void setOptionalityTo(OptionalityType optionality);
}
