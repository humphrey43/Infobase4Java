/* Utility - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package solutions.infobase.basics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Utility
{
    private static final String zeroes = "00000000000000000000";
    
    public static String fillZeroes(int number, int length) {
    	String erg = Integer.toString(number);
    	int p = length - erg.length();
    	if (p > 0)
    	    erg = new StringBuilder(zeroes.substring(0, p))
    		      .append
    		      (erg).toString();
    	return erg;
       }

    public static String fillZeroes(long number, int length) {
    	String erg = Long.toString(number);
    	int p = length - erg.length();
    	if (p > 0)
    	    erg = new StringBuilder(zeroes.substring(0, p))
    		      .append
    		      (erg).toString();
    	return erg;
        }
    public static List<String> createListFromString(String text) {
    	return createListFromString(text, ",");
    }
    public static List<String> createListFromString(String text, String delimiter) {
    	List<String> erg = new ArrayList<String>();
    	String parts[]  = text.split(delimiter);
    	for (int i=0;i<parts.length;i++) {
    		erg.add(parts[i].trim());
    	}
    	return erg;
    }
    public static String createStringFromList(List<?> liste) {
    	return createStringFromList(liste, ",");
    }
    public static String createStringFromList(List<?> liste, String delimiter) {
    	String erg = "";
    	for (Iterator<?> i=liste.iterator();i.hasNext();) {
    		String v = i.next().toString();
    		if (!erg.equals("")) erg = erg + delimiter;
    		erg = erg + v;
    	}
    	return erg;
    }
    public static boolean checkTyped(String type, String actualValue, String refValue) {
    	return checkTyped(type, actualValue, refValue, "");
    }
    public static boolean checkTyped(String type, String actualValue, String refValue1, String refValue2) {
		boolean erg = false;	// default is false
		
		// if value exists, check all rule types 
		if (actualValue != null) {	
			if (type.equals("EQ")) {
				erg = refValue1.equals(actualValue);
			} else if (type.equals("NE")) {
				erg = ! refValue1.equals(actualValue);
			} else if (type.equals("BW")) {         // inklusive
				erg = (refValue1.compareTo(actualValue) <= 0 && refValue2.compareTo(actualValue) >= 0) ;
			} else if (type.equals("GT")) {
				erg = (refValue1.compareTo(actualValue) > 0) ;
			} else if (type.equals("GE")) {
				erg = (refValue1.compareTo(actualValue) >= 0) ;
			} else if (type.equals("LT")) {
				erg = (refValue1.compareTo(actualValue) < 0) ;
			} else if (type.equals("LE")) {
				erg = (refValue1.compareTo(actualValue) <= 0) ;
			} else if (type.equals("LI")) {
				erg = checkValueLike(actualValue, refValue1);
			} else if (type.equals("IN")) {
				erg = checkValueList(actualValue, refValue1);
			} else if (type.equals("NI")) {
				erg = !checkValueList(actualValue, refValue1);
			} else if (type.equals("ST")) {
				erg = actualValue.startsWith(refValue1);
			} else if (type.equals("FU")) {
			}
		}
		
		return erg;
    }
    
	public static boolean checkValueList(String actualValue, String value) {
		boolean erg = false;
		String[] values = value.split(",");
		for (int i=0;i<values.length;i++) {
			if (values[i].equals(actualValue)) {
				erg = true;
				break;
			}
		}
		return erg;
	}

	private static boolean checkValueLike(String actualValue, String value) {
		Pattern regex = createLikePattern(value);
		return checkValueLike(regex, actualValue);
	}
	
	private static boolean checkValueLike(Pattern regex, String value) {
		return regex.matcher(value).matches();
	}
	
	public static Pattern createLikePattern(String pattern) {
		StringBuilder regex = new StringBuilder();
		for (int i=0;i<pattern.length();i++) {
			char c = pattern.charAt(i);
			if (c == '*') {
				regex.append(".*");
			} else if (c == '?') {
				regex.append(".");
			} else {
				if ("+?.*^$()[]{}|\\\"".indexOf(c) >= 0) regex.append("\\");
				regex.append(c);
			}
		}
		return Pattern.compile(regex.toString());
	}

}
