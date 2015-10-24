package solutions.infobase.flexdata;

import java.util.Comparator;

/**
 * @author Hardy Haardt
 *
 */
public class FlexDataComparator
    implements Comparator<Object> {


    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
        int erg = 0;
        if (o1 != null && o2 != null) {
            if (o1 instanceof FlexData) {
                if (o2 instanceof FlexData) {
                    erg = compareFlexData((FlexData)o1, (FlexData)o2);
                } else {
                    erg = 1;
                }
            } else
            if (o2 instanceof FlexData) {
                erg = -1;
            } else {
                erg = compareNonFlexData(o1, o2);
            }
        } else
        if (o1 != null && o2 == null) {
            erg = 1;
        } else
        if (o1 == null && o2 != null) {
            erg = -1;
        }
        return erg;
    }

    protected int compareFlexData(FlexData o1, FlexData o2) {
        return o1.compareTo(o2);
    }

	@SuppressWarnings("unchecked")
	protected int compareNonFlexData(Object o1, Object o2) {
        int erg = -1;
        if (o1 instanceof Comparable) {
            erg = ((Comparable<Object>)o1).compareTo(o2);
        }
        return erg;
    }
}

