package solutions.infobase.core.interfaces;

public interface InfoObjectFactory {
	InfoObject newObject(String infoClassName);
	InfoObject newObject(InfoClass infoClass);
	InfoObject newObject(Object rawObject);
}
