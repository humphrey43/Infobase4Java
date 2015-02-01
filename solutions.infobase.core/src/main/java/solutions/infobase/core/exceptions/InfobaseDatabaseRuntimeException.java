package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseDatabaseRuntimeException extends InfobaseException {
	public InfobaseDatabaseRuntimeException() {
		super();
	}
	public InfobaseDatabaseRuntimeException(String text) {
		super(text);
	}
	public InfobaseDatabaseRuntimeException(Exception e) {
		super(e);
	}
}
