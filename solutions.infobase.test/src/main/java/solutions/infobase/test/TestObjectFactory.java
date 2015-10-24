package solutions.infobase.test;

import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import solutions.infobase.core.Infobase;
import solutions.infobase.core.meta.InfoObjectFactoryConfig;

public class TestObjectFactory extends InfoObjectFactoryConfig {

	public String getClassName(Object rawObject) {
		return ((OrientVertex) rawObject).getProperty(Infobase.OBJECT_INFO_CLASS_NAME);
	}
}
