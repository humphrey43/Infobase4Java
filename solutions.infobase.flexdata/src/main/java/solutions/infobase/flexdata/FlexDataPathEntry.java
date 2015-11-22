package solutions.infobase.flexdata;

public class FlexDataPathEntry {
	protected String entryName = "";
	protected Object entryIndex = null;
	protected boolean isNumeric = false;
	protected char delimiter = ' ';
	protected FlexDataPathEntry nextEntry = null;
	
	/**
	 * @param entry
	 * @throws FlexDataBadSlotPathRuntimeException
	 */
	public FlexDataPathEntry(String entry) throws FlexDataBadSlotPathRuntimeException {
		analyzeEntry(entry);
	}
	/**
	 * @param entry
	 * @throws FlexDataBadSlotPathRuntimeException
	 */
	private void analyzeEntry(String entry) throws FlexDataBadSlotPathRuntimeException {
		isNumeric = false;
		String s = entry;
		int p = s.indexOf('(');
		if (p < 0) {
			entryName = s;
		} else {
			entryName = s.substring(0,p);
			s = s.substring(p+1);
			p = s.indexOf(')');
			if (p < 1) {
				throw new FlexDataBadSlotPathRuntimeException(entry);
			}
			analyzeIndex(s.substring(0,p));
			s = s.substring(p+1);
			if (!s.equals("")) {
				if (!s.equals("")) {
					throw new FlexDataBadSlotPathRuntimeException(entry);
				}
			}
		}
	}
	/**
	 * @param entry
	 * @throws FlexDataBadSlotPathRuntimeException
	 */
	private void analyzeIndex(String entry) throws FlexDataBadSlotPathRuntimeException {
		String s = entry;
		char c = s.charAt(0);
		if (c == '"' | c == '\'') {
			int p = s.indexOf(c,1);
			if (p < 0) {
				throw new FlexDataBadSlotPathRuntimeException(entry);
			} else {
				entryIndex = s.substring(1,p);
				try {
					Integer l = new Integer(entryIndex.toString());
					entryIndex = l;
				} catch (NumberFormatException e) {
				}
				s = s.substring(p+1);
				if (!s.equals("")) {
					throw new FlexDataBadSlotPathRuntimeException(entry);
				}
				delimiter = c;
			}
		} else {
			try {
				Integer l = new Integer(s);
				entryIndex = l;
				isNumeric = true;
			} catch (NumberFormatException e) {
				entryIndex = s;
			}
		}
	}
	public String toString() {
		String erg = entryName;
		if (entryIndex != null) {
			if (isNumeric) {
				erg = erg + "(" + entryIndex.toString() + ")" ;
			} else {
				if (delimiter != ' ') {
					erg = erg + "(" + delimiter + entryIndex.toString() + delimiter + ")" ;
				} else {
					erg = erg + "(" + entryIndex.toString() + ")" ;
				}
			}
		}
		return erg;
	}
	
	public boolean hasNext() {
		return (nextEntry != null);
	}
	
	public FlexDataPathEntry next() {
		return nextEntry;
	}
	
	public void setNext(FlexDataPathEntry next) {
		nextEntry = next;
	}
}
