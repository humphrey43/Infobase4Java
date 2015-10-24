package solutions.infobase.flexdata;

import java.util.Iterator;
import java.util.StringTokenizer;

import solutions.infobase.flexdata.FlexData.Type;

/**
 * @author tha1hh
 *
 */
public class FlexDataJSON {
	
	private final static String tokensString1 = "\"";
	private final static String tokensString2 = "'";
	private final static String tokensStart = "{[";
	private final static String tokens1 = "'\"{[";
	private final static String tokensArray1 = "'\",[{]";
	private final static String tokensObject1 = "'\"";
	private final static String tokensObject2a = "'";
	private final static String tokensObject2b = "\"";
	private final static String tokensObject3 = ":";
	private final static String tokensObject4 = "'\"[{}";
	public static String FlexData2JSON(IFlexData flexdata) {
		String erg = "";
		Type type = flexdata.getFlexDataType();

		StringBuffer sb = new StringBuffer();
		for (Iterator<?> i=flexdata.getIterator();i.hasNext();) {
			java.util.Map.Entry<String, Object> e = (java.util.Map.Entry<String, Object>) i.next();
			if (sb.length() > 0) sb.append(',');
			String key = "";
			if (type.equals(Type.OBJECT)) {
				key = "'" + e.getKey() + "':";
			}
			if (e.getValue() instanceof IFlexData ) {
				sb.append(key + FlexData2JSON((IFlexData) e.getValue()));
			} else if (e.getValue() instanceof String ) {
				sb.append(key + "'" + e.getValue() + "'");
			} else {
				sb.append(key + e.getValue().toString());
			}
		}
		//if (sb.length() > 0) {
			if (type.equals(Type.OBJECT)) {
				erg = "{" + sb.toString() + "}";
			} else {
				erg = "[" + sb.toString() + "]";
		//	}
		}
		return erg;
	}
	public static IFlexData JSON2FlexData(String json) throws RuntimeException {
		IFlexData erg = new FlexData();
		return JSON2FlexData(json, erg);
	}
	public static IFlexData JSON2FlexData(String json, IFlexData flexdata)  throws RuntimeException {
		StringTokenizer tokenizer = new StringTokenizer(json, "'\",{[]}:");
		return JSON2FlexData(flexdata, tokenizer);
	}
	private static IFlexData JSON2FlexData(IFlexData flexdata, StringTokenizer tokenizer) throws RuntimeException {
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
		}
		return flexdata;
	}
}
