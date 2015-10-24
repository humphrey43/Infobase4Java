package solutions.infobase.flexdata;

public class FlexDataAccess {

	protected FlexData flexdata;
	protected FlexDataPath path;
	protected boolean create;
	private static final int READ = 0;
	private static final int WRITE = 1;
	
	public FlexDataAccess(FlexData flexdata, String path) {
		this.flexdata = flexdata;
		this.path = new FlexDataPath(path);
		this.create = false;
	}

	public FlexDataAccess(FlexData flexdata, String path, boolean create) {
		this.flexdata = flexdata;
		this.path = new FlexDataPath(path);
		this.create = create;
	}

	protected Object actionOnPath(int action, Object value) {
		
		Object erg = null;
		
		// angegebenes FlexData-Objekt als Anfangspunkt merken
		FlexData data = flexdata;
		
		// Zugriffspfad von vorn abarbeiten
		FlexDataPathEntry entry = path.pathfirst;
		while (entry != null && data != null) {
			
			// wenn es noch weitere Einträge gibt, ist es nur ein Zwischenpunkt
			if (entry.nextEntry != null || entry.entryIndex != null) {
				
				//Zwischenpunkt holen, ggf. anlegen
				data = getData(data, entry.entryName);
				
				// bei einem indizierten Eintrag das Ganze wiederholen
				if (entry.entryIndex != null && data != null) {
					
					// wenn es noch weitere Einträge gibt, ist es nur ein Zwischenpunkt
					if (entry.nextEntry != null) {
						data = getData(data, entry.entryIndex.toString());
					
					} else {
						// sonst ist es der Endpunkt und die Aktion wird ausgeführt
						switch (action) {
						case WRITE:
							data.setValueDirect(entry.entryIndex.toString(), value);
							break;
						case READ:
							erg = data.getValueDirect(entry.entryIndex);
							break;
						}
					}
				}
			
			} else {
				// sonst ist es der Endpunkt und die Aktion wird ausgeführt
				switch (action) {
				case WRITE:
					data.setValueDirect(entry.entryName, value);
					break;
				case READ:
					erg = data.getValueDirect(entry.entryName);
					break;
				}
			}
			entry = entry.nextEntry;
		}
		
		return erg;
	}
	protected void setToPath(Object value) {
		actionOnPath(WRITE, value);
	}

	protected Object getFromPath() {
		return actionOnPath(READ, null);
	}

	protected FlexData getData(FlexData flexdata, String key) {

		FlexData erg = null;
		
		// Eintrag lesen, wenn es noch kein FlexData ist, dann evtl. anlegen
		// dabei wird ggf. ein vorhandener Eintrag überschrieben
		try {
			erg = (FlexData) flexdata.getValueMap(key);
		} catch (ClassCastException e) {
		}
		if (erg == null && create) {
			erg = new FlexData();
			flexdata.setValueMap(key, erg);
		}
		
		return erg;
	}
}
