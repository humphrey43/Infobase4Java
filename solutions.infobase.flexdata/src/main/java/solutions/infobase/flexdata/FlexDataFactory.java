package solutions.infobase.flexdata;

import solutions.infobase.flexdata.FlexData;
import solutions.infobase.flexdata.FlexData.Type;

public class FlexDataFactory 
{
	private static FlexDataFactory factory = null;
	
	public static IFlexData newInstance()
	{
		if (factory == null)
		{
			factory = new FlexDataFactory();
		}
		return factory.createInstance();
	}
	
	public static IFlexData newInstance(Type type)
	{
		if (factory == null)
		{
			factory = new FlexDataFactory();
		}
		IFlexData erg = factory.createInstance();
		erg.setFlexDataType(type);
		return erg;
	}
	
	protected IFlexData createInstance()
	{
		return new FlexData();
	}
	
	public static void setFactory(FlexDataFactory factory)
	{
		FlexDataFactory.factory = factory;
	}
}
