package solutions.infobase.core.interfaces;

public interface InfoClass extends InfoObject {
	InfoClass getSuperclass();
	InfoObject newObject();
	String getName();
}
