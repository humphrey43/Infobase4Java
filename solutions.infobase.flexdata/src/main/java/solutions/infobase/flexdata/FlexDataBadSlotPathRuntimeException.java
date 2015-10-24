package solutions.infobase.flexdata;

public class FlexDataBadSlotPathRuntimeException extends RuntimeException  {

	public FlexDataBadSlotPathRuntimeException(String path) {
		super("Wrong path structure: " + path);
	}
	private static final long serialVersionUID = 1L;

}
