package solutions.infobase.core.exceptions;

@SuppressWarnings("serial")
public class InfobaseRuntimeException extends RuntimeException {

	public InfobaseRuntimeException() {
		super();
	}
	public InfobaseRuntimeException(String text) {
		super(text);
	}
	public InfobaseRuntimeException(Exception e) {
		super(e);
	}

}
