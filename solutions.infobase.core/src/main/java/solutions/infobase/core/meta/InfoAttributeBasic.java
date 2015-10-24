package solutions.infobase.core.meta;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;

public class InfoAttributeBasic extends InfoObjectBasic implements InfoAttribute {

	protected InfoClass describedclass = null;
	protected AttributeType type  = null;

	public InfoAttributeBasic() {
	}
	
	public InfoAttributeBasic(InfoDatabase database, Object rawObject) {
		super(database, rawObject);
	}
	
	@Override
	public InfoClass getDescribedClass() {
		return describedclass;
	}

	@Override
	public void setDescribedClass(InfoClass describedclass) {
		this.describedclass = describedclass;
	}

	@Override
	public String getDescribedClassName() {
		String erg = null;
		
		if (describedclass != null) {
			erg = describedclass.getName();
		}
		
		return erg;
	}
	
	@Override
	public AttributeType getType() {
		if (type == null) {
			type = AttributeType.valueOf(getString(Infobase.ATTRIBUTE_TYPE));
		}
		return type;
	}

	@Override
	public void setType(AttributeType type) {
		this.type = type;
		setValue(Infobase.ATTRIBUTE_TYPE, type.toString());
	}

	@Override
	public String getName() {
		return getString(Infobase.ATTRIBUTE_NAME);
	}

	@Override
	public void setName(String name) {
		setValue(Infobase.ATTRIBUTE_NAME, name);
	}
	
	@Override
	public String getDesignation() {
		return getString(Infobase.ATTRIBUTE_DESIGNATION);
	}

	@Override
	public void setDesignation(String designation) {
		setValue(Infobase.ATTRIBUTE_DESIGNATION, designation);
	}

	@Override
	public String getDescription() {
		return getString(Infobase.ATTRIBUTE_DESCRIPTION);
	}

	@Override
	public void setDescription(String description) {
		setValue(Infobase.ATTRIBUTE_DESCRIPTION, description);
	}
}
