/**
 * 
 */
package solutions.infobase.flexdata;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import solutions.infobase.flexdata.FlexData.Type;

/**
 * @author Hardy Haardt
 *
 */
public interface IFlexData extends solutions.infobase.basics.ValueSource {

	/**
	 * sets the specified int value as an Integer using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	void setValue(String path, int value);

	/**
	 * sets the specified long value as a Long using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	void setValue(String path, long value);

	/**
	 * sets the specified long value as a Double using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	void setValue(String path, double value);

	/**
	 * sets the specified boolean value as a Boolean using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	void setValue(String path, boolean value);

	/**
	 * sets the specified value using the specified path
	 * 
	 * Depending on the path the value is directly saved or a more
	 * complex path is analyzed and traversed thereby creating all the intermediate objects.
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	void setValue(String path, Object value);

	/**
	 * @param path	Pfad zum Feld
	 * @return
	 */
	String getString(String path);
	String getString(int index);

	/**
	 * @param path	Pfad zum Feld
	 * @return
	 */
	int getInteger(String path);
	int getInteger(int index);

	/**
	 * @param path	Pfad zum Feld
	 * @return
	 */
	boolean getBoolean(String path);

	/**
	 * @param path	Pfad zum Feld
	 * @return
	 */
	long getLong(String path);

	/**
	 * @param path	Pfad zum Feld
	 * @return
	 */
	Date getDate(String path);

	/**
	 * @param path	Pfad zum Feld
	 * @return
	 */
	Date getDate(String path, String format);

	void add(int value);

	void add(long value);

	void add(boolean value);

	boolean getBoolean(int index);

	long getLong(int index);
	
	double getDouble(int index);

	double getDouble(String path);

	IFlexData getFlexData(String path);

	Object getValue(String path);

	IFlexData getFlexData(int index);

	Object getValue(int index);

	void add(Object value);
	
	int size();

	String getKeyNames();

	List<String> getKeyNameList();

	Iterator<? extends Object> getIterator();
	Iterator<Object> getValueIterator();

	Type getFlexDataType();

	void sort();
	void sort(FlexDataComparator comparator);

	void setFlexDataType(Type type);

	void append(FlexData other);

	void append(List<?> list);
}