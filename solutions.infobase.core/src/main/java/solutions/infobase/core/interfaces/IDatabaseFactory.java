package solutions.infobase.core.interfaces;

import org.apache.commons.configuration.Configuration;

public interface IDatabaseFactory {
	public IDatabase newDatabase();
	public void setConfiguration(Configuration config);
}
