package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseDatabaseRuntimeException extends InfobaseRuntimeException {

	public InfobaseDatabaseRuntimeException(String text, Exception e) {
		super(text, e);
	}
	public InfobaseDatabaseRuntimeException(String text) {
		super(text);
	}
	public InfobaseDatabaseRuntimeException(Exception e) {
		super(e);
	}
}
