package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseException extends Exception {

	public InfobaseException() {
		super();
	}
	public InfobaseException(String text) {
		super(text);
	}
	public InfobaseException(Exception e) {
		super(e);
	}

}
