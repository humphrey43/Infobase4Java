/**
 * 
 */
package solutions.infobase.flexdata;

import java.util.Iterator;

/**
 * @author Hardy Haardt
 *
 * Dieser Iterator liefert kein Ergebnis
 */
public class NullIterator implements Iterator<Object> {

	/** 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return false;
	}

	/**
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
		return null;
	}

	/**
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
	}

}
