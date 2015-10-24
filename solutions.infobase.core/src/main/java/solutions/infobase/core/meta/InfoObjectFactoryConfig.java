package solutions.infobase.core.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalConfiguration;

import solutions.infobase.core.interfaces.InfoDatabase;
import solutions.infobase.core.interfaces.InfoObject;

public abstract class InfoObjectFactoryConfig extends InfoObjectFactoryBasic {

	protected Map<String, String> objectClasses = new HashMap<>();
	
	public  InfoObjectFactoryConfig() {
		super();
	}
	
	protected abstract String getClassName(Object rawObject);
	
	@Override
	public InfoObject newInfoObject(InfoDatabase database, Object rawObject) {
		InfoObject erg = null;
		String classname = getClassName(rawObject);
		if (classname != null) {
			String fullclassname = objectClasses.get(classname);
			if (fullclassname != null) {
				try {
					erg = (InfoObject) Class.forName(classname).newInstance();
					erg.setDatabase(database);
					erg.setRawObject(rawObject);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		if (erg == null) erg = super.newInfoObject(database, rawObject);
		return erg;
	}

	public void setConfiguration(Configuration config, String databaseName) {
		List<HierarchicalConfiguration> classes = ((HierarchicalConfiguration) config).configurationsAt("dbconfig[@name='" + databaseName + "']/classes/class");
		for (HierarchicalConfiguration c : classes) {
			String name = c.getString("name");
			String full = c.getString("full");
			objectClasses.put(name, full);
		}
//		Properties props = config.getProperties("dbconfig[@name='" + databaseName + "']/classnames");
	}
}
