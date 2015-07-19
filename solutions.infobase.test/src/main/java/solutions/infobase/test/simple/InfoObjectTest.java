package solutions.infobase.test.simple;

import solutions.infobase.core.InfoObjectBasic;
import solutions.infobase.flexdata.FlexData;
import solutions.infobase.flexdata.IFlexData;

public class InfoObjectTest extends InfoObjectBasic {

	IFlexData values;
	public InfoObjectTest() {
		values = new FlexData();
	}
	@Override
	public void setValue(String name, Object value) {
		values.setValue(name, value);
	}

	@Override
	public Object getValue(String name) {
		return values.getValue(name);
	}

}
