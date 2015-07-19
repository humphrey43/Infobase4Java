package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseDatabaseException extends InfobaseException {

	public InfobaseDatabaseException(String text, Exception e) {
		super(text, e);
	}
	public InfobaseDatabaseException(String text) {
		super(text);
	}
	public InfobaseDatabaseException(Exception e) {
		super(e);
	}
}
