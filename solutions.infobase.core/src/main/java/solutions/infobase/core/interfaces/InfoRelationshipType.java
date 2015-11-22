package solutions.infobase.core.interfaces;

public interface InfoRelationshipType extends InfoObject {
	InfoRelationshipType getSuperclass();
	InfoRelationship newRelationship();
	String getName();
}
