package solutions.infobase.basics;

public class CascadingValueSource implements ValueSource {

	protected ValueSource valueSource1;
	public ValueSource getValueSource1() {
		return valueSource1;
	}

	public ValueSource getValueSource2() {
		return valueSource2;
	}

	protected ValueSource valueSource2;	
	public CascadingValueSource(ValueSource valueSource1, ValueSource valueSource2) {
		this.valueSource1 = valueSource1; 
		this.valueSource2 = valueSource2; 
	}
	
	public String getHandledValue(String key) {
		return getHandledValue(key, null);
	}

	public String getHandledValue(String key, String defaultvalue) {
		String erg = defaultvalue;
		String value = valueSource1.getHandledValue(key);
		if (value != null && !value.equals("")) {
			erg = value;
		} else {
			value = valueSource2.getHandledValue(key);
			if (value != null && !value.equals("")) {
				erg = value;
			}
		}
		return erg;
	}

	public void setHandledValue(String key, String value) {
		throw new RuntimeException("Function 'void setHandledValue(String, String)' not available");
	}

}
