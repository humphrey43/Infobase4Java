package solutions.infobase.core.meta;

import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoObject;

public abstract class InfoAttributeBasic implements InfoAttribute {

	public enum AttributeType {
		INTEGER,
		DECIMAL,
		STRING,
		TEXT,
		BOOLEAN,
		DATE,
		TIMESTAMP,
		TIME,
		OBJECT,
		RELATIONSHIP
	}
	@Override
	public InfoObject getInfoObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
