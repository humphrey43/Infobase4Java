package solutions.infobase.flexdata;

public class FlexDataPath {

	protected FlexDataPathEntry pathfirst = null;
	
	/**
	 * @param entry
	 * @throws FlexDataBadSlotPathRuntimeException
	 */
	public FlexDataPath(String path) throws FlexDataBadSlotPathRuntimeException {
		analyzePath(path);
	}
	/**
	 * 
	 * 
	 * name[(["|']index["|'])][.name[(["|']index["|'])] .....
	 * 
	 * index may be:
	 * 
	 *     simple String with or witout quotes for associative access
	 *     number used as numeric index into fields
	 *     quoted number used as string for associative access
	 *     &1, &2, ...  parms to be replaced by actual access information
	 *
	 * @param path
	 * @throws FlexDataBadSlotPathRuntimeException
	 * 
	 * 
	 */
	private void analyzePath(String path) throws FlexDataBadSlotPathRuntimeException {
		int p;
		String work = path;
		FlexDataPathEntry spe_akt = null;
		FlexDataPathEntry spe_new;
		
		// einzelne Slot-Pfade extrahieren
		int delimiter = ' ';
		p = 0;
		for (int i = 0; i< work.length(); i++) {
			int c = work.charAt(i);
			if (delimiter == ' ') {
				if (c == '.') {
					spe_new = new FlexDataPathEntry(work.substring(p, i));
					p = i + 1;
					if (spe_akt == null) {
						pathfirst = spe_new;
					} else {
						spe_akt.setNext(spe_new);
					}
					spe_akt = spe_new;
				} else if (c == '"') {
					delimiter = c;
				} else if (c == '\'') {
					delimiter = c;
				} else if (c == ' ') {
					throw new FlexDataBadSlotPathRuntimeException(path);
				}
			} else {
				if (c == delimiter) {
					delimiter = ' ';
				}
			}
		}
		if (delimiter != ' ') {
			throw new FlexDataBadSlotPathRuntimeException(path);
		}
		if (!work.substring(p).equals("")) {
			spe_new = new FlexDataPathEntry(work.substring(p));
			if (spe_akt == null) {
				pathfirst = spe_new;
			} else {
				spe_akt.setNext(spe_new);
			}
		}
	}
	public String toString() {
		String erg = "";
		FlexDataPathEntry spe = pathfirst;
		boolean first = true;
		while (spe != null) {
			if (! first) {
				erg = erg + "." + spe.toString();
			} else {
				erg = erg + spe.toString();
				first = false;
			}
			spe = spe.next();
		}
		return erg;
	}
	public boolean isEmpty() {
		return (pathfirst == null);
	}
	public FlexDataPathEntry getFirst() {
		return pathfirst;
	}
}
