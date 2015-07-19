package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseException extends Exception {

	public InfobaseException(String text, Exception e) {
		super(text, e);
	}
	public InfobaseException(String text) {
		super(text);
	}
	public InfobaseException(Exception e) {
		super(e);
	}

}
