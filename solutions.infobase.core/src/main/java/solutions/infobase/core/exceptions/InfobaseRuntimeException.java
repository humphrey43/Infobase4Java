package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseRuntimeException extends RuntimeException {

	public InfobaseRuntimeException(String text, Exception e) {
		super(text, e);
	}
	public InfobaseRuntimeException(String text) {
		super(text);
	}
	public InfobaseRuntimeException(Exception e) {
		super(e);
	}
}
