package solutions.infobase.core.meta;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.Infobase.AttributeType;
import solutions.infobase.core.Infobase.CardinalityType;
import solutions.infobase.core.Infobase.OptionalityType;
import solutions.infobase.core.exceptions.InfobaseDatabaseException;
import solutions.infobase.core.interfaces.InfoAttribute;
import solutions.infobase.core.interfaces.InfoClass;
import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoRelationship;

public class InfoRelationshipBasic extends InfoObjectBasic implements InfoRelationship {

	protected String name;
	protected InfoClass classFrom = null;
	protected InfoClass classTo = null;
	protected boolean directional;
	protected CardinalityType cardinalityFrom  = null;
	protected CardinalityType cardinalityTo  = null;
	protected OptionalityType optionalityFrom  = null;
	protected OptionalityType optionalityTo  = null;

	public InfoRelationshipBasic() {
	}
	
	public InfoRelationshipBasic(InfoDatabase database, Object rawObject, InfoClass start) {
		super(database, rawObject);
		name = (String) getValueDirect(Infobase.RELATIONSHIP_NAME);
		name = (String) getValueDirect(Infobase.RELATIONSHIP_NAME);
		name = (String) getValueDirect(Infobase.RELATIONSHIP_NAME);
		name = (String) getValueDirect(Infobase.RELATIONSHIP_NAME);
		name = (String) getValueDirect(Infobase.RELATIONSHIP_NAME);
		name = (String) getValueDirect(Infobase.RELATIONSHIP_NAME);
		classFrom = database.getClassFrom(this, start);
		classTo = database.getClassTo(this, start);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		setValueDirect(Infobase.RELATIONSHIP_NAME, name);
	}

	@Override
	public String getDesignationFrom() {
		return getString(Infobase.RELATIONSHIP_DESIGNATION_FROM);
	}

	@Override
	public void setDesignationFrom(String designation) {
		setValue(Infobase.RELATIONSHIP_DESIGNATION_FROM, designation);
	}

	@Override
	public String getDesignationTo() {
		return getString(Infobase.RELATIONSHIP_DESIGNATION_TO);
	}

	@Override
	public void setDesignationTo(String designation) {
		setValue(Infobase.RELATIONSHIP_DESIGNATION_TO, designation);
	}

	@Override
	public boolean isDirectional() {
		return directional;
	}

	@Override
	public void setDirectional(boolean directional) {
		this.directional = directional;
		setValue(Infobase.RELATIONSHIP_DIRECTIONAL, directional);
	}

	@Override
	public InfoClass getClassFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClassFrom(InfoClass infoClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfoClass getClassTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClassTo(InfoClass infoClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		return getString(Infobase.RELATIONSHIP_DESCRIPTION);
	}

	@Override
	public void setDescription(String description) {
		setValue(Infobase.RELATIONSHIP_DESCRIPTION, description);
	}

	@Override
	public CardinalityType getCardinalityFrom() {
		if (cardinalityFrom == null) {
			cardinalityFrom = CardinalityType.valueOf(getString(Infobase.RELATIONSHIP_CARDINALITY_FROM));
		}
		return cardinalityFrom;
	}

	@Override
	public void setCardinalityFrom(CardinalityType cardinality) {
		this.cardinalityFrom = cardinality;
		setValue(Infobase.RELATIONSHIP_CARDINALITY_FROM, cardinality.toString());
	}

	@Override
	public CardinalityType getCardinalityTo() {
		if (cardinalityTo == null) {
			cardinalityTo = CardinalityType.valueOf(getString(Infobase.RELATIONSHIP_CARDINALITY_TO));
		}
		return cardinalityTo;
	}

	@Override
	public void setCardinalityTo(CardinalityType cardinality) {
		this.cardinalityTo = cardinality;
		setValue(Infobase.RELATIONSHIP_CARDINALITY_TO, cardinality.toString());
	}

	@Override
	public OptionalityType getOptionalityFrom() {
		if (optionalityFrom == null) {
			optionalityFrom = OptionalityType.valueOf(getString(Infobase.RELATIONSHIP_OPTIONALITY_FROM));
		}
		return optionalityFrom;
	}

	@Override
	public void setOptionalityFrom(OptionalityType optionality) {
		this.optionalityFrom = optionality;
		setValue(Infobase.RELATIONSHIP_OPTIONALITY_FROM, optionality.toString());
	}

	@Override
	public OptionalityType getOptionalityTo() {
		if (optionalityTo == null) {
			optionalityTo = OptionalityType.valueOf(getString(Infobase.RELATIONSHIP_OPTIONALITY_TO));
		}
		return optionalityTo;
	}

	@Override
	public void setOptionalityTo(OptionalityType optionality) {
		this.optionalityTo = optionality;
		setValue(Infobase.RELATIONSHIP_OPTIONALITY_TO, optionality.toString());
	}
}
