package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseDatabaseException extends InfobaseRuntimeException {
	public InfobaseDatabaseException() {
		super();
	}
	public InfobaseDatabaseException(String text) {
		super(text);
	}
	public InfobaseDatabaseException(Exception e) {
		super(e);
	}
}
