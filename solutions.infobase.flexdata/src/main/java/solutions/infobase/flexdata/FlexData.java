package solutions.infobase.flexdata;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Hardy Haardt
 *
 */
public class FlexData implements IFlexData, Map<String, Object>, Cloneable, Serializable, Comparable<IFlexData> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum Type {UNKNOWN, OBJECT, LIST };
	
//	  public static final int UNKNOWN = 0;
//    public static final int OBJECT = 1;
//    public static final int LIST = 2;
    private static class FlexDataToken {
    	public static final String FLEXDATA = "FlexData";
    	public static final String ENTRY = "Entry";
    	public static final String VALUE = "Value";
    	public String tagName;
    	public String attrName;
    	public String value;
    	public boolean isEndTag;
    	public boolean isSingleTag;
    	public FlexDataToken(String extern) {
    		if (extern.startsWith("<")) {
    			attrName = "";
    			isEndTag = false;
    			isSingleTag = false;
    			value = "";
    			String s = extern.substring(1,extern.length()-1);
    			int p = s.indexOf(' ');
    			if (p > 0) {
    				tagName = s.substring(0,p);
    				s = s.substring(p+1);
    				p = s.indexOf("=");
    				if (s.endsWith("/")) {
        				s = s.substring(0,s.length()-1).trim();
            			isSingleTag = true;
    				} 
        			s = s.substring(p+2,s.length()-1);
   					attrName = s;
    			} else {
    				if (s.startsWith("/")) {
    					s = s.substring(1);
    					isEndTag = true;
    				}
    				tagName = s;
    			}
    			value = "";
    		} else {
    			tagName = VALUE;
    			attrName = "";
    			isEndTag = false;
    			isSingleTag = false;
    			value = extern;
    		}
    	}
	}

	private static class FlexDataTokenizer {

		private StringBuffer buffer;
		private int pos;
		private FlexDataToken last = null;
    	public FlexDataTokenizer(String extern) {
    		buffer = new StringBuffer(extern);
    		pos = 0;
    	}
    	public FlexDataToken nextToken() {
    		FlexDataToken erg = null;
    		if (last != null) {
    			erg = last;
    			last = null;
    		} else {
	    		int posend = 0;
	    		if (pos < buffer.length()) {
	    			if (buffer.charAt(pos) == '<') {
	    				posend = buffer.indexOf(">", pos) + 1;
	    			} else {
	    				posend = buffer.indexOf("<", pos);
	    			}
					erg = new FlexDataToken(buffer.substring(pos, posend));
					pos = posend;
	    		}
    		}
    		
			return erg;
    	}
		public void push(FlexDataToken token) {
			last = token;
		}
	}

//	protected Map entries;
    protected List<Object> entryCollectionList;
    public List<Object> getEntryCollectionList() {
		return assertList();
	}

	public Map<String, Object> getEntryCollectionMap() {
		return assertMap();
	}

	protected Map<String, Object> entryCollectionMap;
    protected Type type;
	protected String standardDateFormat;
    
	/****************************************************************
	 * 
	 * Static Members
	 * 
	 ****************************************************************/
    
    public static String getTypename(Type type)
    {
        switch(type)
        {
        case UNKNOWN: 
            return "unknown";
            
        case OBJECT: 
            return "object";

        case LIST: 
            return "list";
        }
        return "";
    }
    
    public static int compare(IFlexData fd1, IFlexData fd2, String path)
    {
        String v1 = fd1.getString(path);
        String v2 = fd2.getString(path);
        return v1.compareTo(v2);
    }
    
    public static int compare(IFlexData fd1, IFlexData fd2, String path1, String path2)
    {
        String v1 = fd1.getString(path1);
        String v2 = fd2.getString(path2);
        return v1.compareTo(v2);
    }
    
    public static void copy(IFlexData fd1, IFlexData fd2, String key) {
		fd2.setValue(key, fd1.getValue(key));
	}

    public static void copy(IFlexData fd1, IFlexData fd2, String key1, String key2) {
		fd2.setValue(key2, fd1.getValue(key1));
	}

    public static boolean equals(IFlexData fd1, IFlexData fd2, String key) {
		return fd1.getString(key).equals(fd2.getString(key));
	}

    public static boolean equals(IFlexData fd1, IFlexData fd2, String key1, String key2) {
		return fd1.getString(key1).equals(fd2.getString(key2));
	}

	/****************************************************************
	 * 
	 * Constructors
	 * 
	 ****************************************************************/
    
	/**
	 * empty Constructor for a FlexData object
	 */
	public FlexData() {
        entryCollectionList = null;
        entryCollectionMap = null;
        type = Type.UNKNOWN;
        standardDateFormat = "yyyy-MM-dd"; // DB2-Datum
	}

	/**
	 * Constructor for a FlexData object with a given type
	 */
	public FlexData(Type type) {
        entryCollectionList = null;
        entryCollectionMap = null;
        this.type = type;		
        standardDateFormat = "yyyy-MM-dd"; // DB2-Datum
	}

	/****************************************************************
	 * 
	 * Interface IFlexData
	 * 
	 ****************************************************************/
	
	/**
	 * sets the specified int value as an Integer using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	@Override
	public void setValue(String path, int value) {
		setValue(path, new Integer(value));
	}

	/**
	 * sets the specified long value as a Long using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	@Override
	public void setValue(String path, long value) {
		setValue(path, new Long(value));
	}

	/**
	 * sets the specified double value as a Double using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	@Override
	public void setValue(String path, double value) {
		setValue(path, new Double(value));
	}

	/**
	 * sets the specified boolean value as a Boolean using
	 * the specified path
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	@Override
	public void setValue(String path, boolean value) {
		setValue(path, new Boolean(value));
	}

	/**
	 * sets the specified value using the specified path
	 * 
	 * Depending on the path the value is directly saved or a more
	 * complex path is analyzed and traversed thereby creating all the intermediate objects.
	 * 
	 * @param path	the path where the value shall be stored
	 * @param value the value to be stored
	 */
	@Override
	public void setValue(String path, Object value) {
		if (path.indexOf('.') >= 0 || path.indexOf('(') >= 0 ) {
			FlexDataAccess access = new FlexDataAccess(this, path, true);
			access.setToPath(value);
		} else {
			setValueMap(path, value);
		}
	}
	
	/**
	 * @param path
	 * @param value
	 */
	@Override
	public void add(int value) {
		add(new Integer(value));
	}
	
	/**
	 * @param path
	 * @param value
	 */
	@Override
	public void add(long value) {
		add(new Long(value));
	}
	
	/**
	 * @param path
	 * @param value
	 */
	@Override
	public void add(boolean value) {
		add(new Boolean(value));
	}
	
	/**
	 * @param path
	 * @return
	 */
	@Override
	public String getString(String path) {
		String erg = "";
		Object o = getValue(path);
		if (o != null) {
			erg = o.toString().trim();
		}
		return erg;
	}
	
	/**
	 * @param path
	 * @return
	 */
	@Override
	public String getString(int index) {
		String erg = "";
		Object o = getValue(index);
		if (o != null) {
			erg = o.toString().trim();
		}
		return erg;
	}
	
	/**
	 * @param path
	 * @return
	 */
	@Override
    public int getInteger(String path)
    {
        int erg = 0;
        try
        {
            Object v = getValue(path);
            Number i = null;
            if (v instanceof Number) {
                i = (Number)v;
            } else {
	            if (v instanceof String) {
	                i = Integer.valueOf(Integer.parseInt((String)v));
	            }
            }
            if (i != null) {
                erg = i.intValue();
            }
        }
        catch(ClassCastException classcastexception) { }
        return erg;
    }

	
	/**
	 * @param path
	 * @return
	 */
	@Override
	public int getInteger(int index) {
        int erg = 0;
        try
        {
            Object v = getValue(index);
            Number i = null;
            if (v instanceof Number) {
                i = (Number)v;
            } else {
	            if (v instanceof String) {
	                i = Integer.valueOf(Integer.parseInt((String)v));
	            }
            }
            if (i != null) {
                erg = i.intValue();
            }
        }
        catch(ClassCastException classcastexception) { }
        return erg;
    }

	
	/**
	 * @param path
	 * @return
	 */
	@Override
	public double getDouble(String path) {
        double erg = 0;
        try
        {
            Object v = getValue(path);
            Number i = null;
            if (v instanceof Number) {
                i = (Number)v;
            } else {
	            if (v instanceof String) {
	                i = Double.valueOf(Double.parseDouble((String)v));
	            }
            }
            if (i != null) {
                erg = i.doubleValue();
            }
        }
        catch(ClassCastException classcastexception) { }
        return erg;
    }

	/**
	 * @param index
	 * @return
	 */
	@Override
	public double getDouble(int index) {
        double erg = 0;
        try
        {
            Object v = getValue(index);
            Number i = null;
            if (v instanceof Number) {
                i = (Number)v;
            } else {
	            if (v instanceof String) {
	                i = Double.valueOf(Double.parseDouble((String)v));
	            }
            }
            if (i != null) {
                erg = i.doubleValue();
            }
        }
        catch(ClassCastException classcastexception) { }
        return erg;
    }

	
	/**
	 * @param path
	 * @return
	 */
	@Override
	public boolean getBoolean(String path) {
		boolean erg = false;
        Object v = getValue(path);
        if (v instanceof Boolean) {
            erg = ((Boolean)v).booleanValue();
        } else if (v instanceof String) {
            erg = v.equals("X");
        } else if (v instanceof Number) {
            erg = (((Number)v).intValue() != 0);
        }
 		return erg;
	}

	/**
	 * @param path
	 * @return
	 */
	@Override
	public boolean getBoolean(int index) {
		boolean erg = false;
        Object v = getValue(index);
        if (v instanceof Boolean) {
            erg = ((Boolean)v).booleanValue();
        } else if (v instanceof String) {
            erg = v.equals("X");
        } else if (v instanceof Number) {
            erg = (((Number)v).intValue() != 0);
        }
 		return erg;
	}

	/**
	 * @param path
	 * @return
	 */
	@Override
	public long getLong(String path) {
		long erg = 0;
		try {
            Object v = getValue(path);
			//Number i = (Number) getValue(path);
			Number i = null;
            if (v instanceof Number) {
                i = (Number)v;
            } else {
	            if (v instanceof String) {
	                i = Long.valueOf(Long.parseLong((String)v));
	            }
            }
            if (i != null) {
                erg = i.longValue();
            }
		} catch (ClassCastException e) {
		}
		return erg;
	}
	/**
	 * @param path
	 * @return
	 */
	@Override
	public long getLong(int index) {
		long erg = 0;
		try {
            Object v = getValue(index);
            Number i = null;
            if (v instanceof Number) {
                i = (Number)v;
            } else {
	            if(v instanceof String) {
	                i = Long.valueOf(Long.parseLong((String)v));
	            }
            }
            if (i != null) {
                erg = i.longValue();
            }
		} catch (ClassCastException e) {
		}
		return erg;
	}

	/**
	 * @param path
	 * @return
	 */
	@Override
	public IFlexData getFlexData(String path) {
		FlexData erg = null;
		try {
			erg = (FlexData) getValue(path);
		} catch (ClassCastException e) {
		}
		return erg;
	}

	/**
	 * @param path
	 * @return
	 */
	@Override
	public IFlexData getFlexData(int index) {
		FlexData erg = null;
		try {
			erg = (FlexData) getValue(index);
		} catch (ClassCastException e) {
		}
		return erg;
	}
	/**
	 * @param key
	 * @return
	 */
	protected Object getValueDirect(Object key) {
        if(key instanceof Integer)
        {
            return getValueList((Integer)key);
        } else
        {
            return getValueMap(key);
        }
	}
	/**
	 * @param path
	 * @return
	 */
	@Override
	public Object getValue(String path) {
		Object erg = null;
		if (path.indexOf('.') >= 0 || path.indexOf('(') >= 0 ) {
			FlexDataAccess access = new FlexDataAccess(this, path, true);
			erg = access.getFromPath();
		} else {
			erg = getValueDirect(path);
		}
		return erg;
	}
	
	@Override
    public void add(Object value) {
        type = Type.LIST;
        assertList().add(value);
    }

	@Override
    public Object getValue(int index) {
        return assertList().get(index);
    }

	protected Object getValueList(Integer index) {
        return assertList().get(index.intValue());
    }

    protected Object getValueMap(Object key) {
        return assertMap().get(key);
    }

//	@Override
//    public Map getEntries() {
//        return assertMap();
//    }

//	@Override
//    public int getSize() {
//        switch (type) {
//        case LIST: 
//            return ((List)entryCollection).size();
//
//        case OBJECT: 
//            return ((Map)entryCollection).size();
//        }
//        return 0;
//    }

	@Override
    public String getKeyNames() {
        StringBuffer erg = new StringBuffer();
        FlexData f = null;
        if (type == Type.OBJECT) {
            f = this;
        } else
        if (!isEmpty()) {
            f = (FlexData)getValue(0);
        }
        if (f != null) {
            String n;
            for (Iterator<String> i = f.getEntries().keySet().iterator(); i.hasNext(); erg.append(n)) {
                n = (String)i.next();
                if (erg.length() > 0) {
                    erg.append(",");
                }
            }

        }
        return erg.toString();
    }

	@Override
    public List<String> getKeyNameList() {
        List<String> erg = null;
        FlexData f = null;
        if (type == Type.OBJECT) {
            f = this;
        } else
        if (!isEmpty()) {
            f = (FlexData)getValue(0);
        }
        if (f != null) {
            erg = new ArrayList<String>(f.getEntries().keySet());
        } else {
        	erg = new ArrayList<String>();
        }
        return erg;
    }

	@SuppressWarnings("incomplete-switch")
	@Override
    public boolean isEmpty() {
        switch (type) {
        case LIST: 
            return entryCollectionList.isEmpty();

        case OBJECT:
            return entryCollectionMap.isEmpty();
        }
        return true;
    }

	@SuppressWarnings("incomplete-switch")
	@Override
    public Iterator<? extends Object> getIterator() {
        switch (type) {
        case LIST:
            return entryCollectionList.iterator();

        case OBJECT:
            return entryCollectionMap.entrySet().iterator();
        }
        return new NullIterator();
    }

	@Override
    public Iterator<Object> getValueIterator() {
        switch (type) {
        case LIST:
            return entryCollectionList.iterator();

        case OBJECT:
            return entryCollectionMap.values().iterator();
		case UNKNOWN:
			break;
		default:
			break;
        }
        return new NullIterator();
    }

	public static IFlexData deserialize(String extern) {
    	return deserializeIntern(new FlexDataTokenizer(extern));
    }

    private static FlexData deserializeIntern(FlexDataTokenizer tokenizer) {
    	FlexData erg = null;
    	
    	FlexDataToken token = tokenizer.nextToken();
    	if (token != null && token.tagName.equals(FlexDataToken.FLEXDATA)) {
    		erg = new FlexData();
    		token = tokenizer.nextToken();
    		while (token != null && !(token.tagName.equals(FlexDataToken.FLEXDATA) && token.isEndTag)) {
    			// der erste ist immer ein Entry
    			String name = token.attrName;
    			Object value = "";
    			
    			// wenn single Token, dann fertig
    			if (token.isSingleTag) {
	    			if (name.equals("")) {
	    				erg.add(value);
					} else {
						erg.setValue(name, value);
					}
    				
    			} else {
    			// sonst weiter
	    			// Wert lesen
	    			token = tokenizer.nextToken();
	    			
	    			// wenn einfacher Wert, dann übernehmen
	    			if (token.tagName.equals(FlexDataToken.VALUE) ) {
	    				if (token.value.equals("java.lang.null")) {
	    					value = null;
	    				} else {
	    					value = token.value;
	    				}
	    				
	        			// wenn einfacher Wert, dann übernehmen
	    			} else if (token.tagName.equals(FlexDataToken.ENTRY) && token.isEndTag) {
	        			value = "";
	    				tokenizer.push(token);
	        			
	    			// sonst neue FlexData aufbauen
	    			} else {
	    				tokenizer.push(token);
	    				value = deserializeIntern(tokenizer);
	    				
	    			}
	    			if (name.equals("")) {
	    				erg.add(value);
					} else {
						erg.setValue(name, value);
					}
	    			
	    			// der letzte ist das Entry-Endetag, einfach überlesen
	    			token = tokenizer.nextToken();
    			}
    			
    			// und das nächste (entweder Entry oder FlexData-Ende-Tag
    			token = tokenizer.nextToken();
    		}
    	}
		return erg;
	}

	public Type getFlexDataType() {
        return type;
    }

    public void setFlexDataType(Type type) {
        if (type != this.type) {
            entryCollectionList = null;
            entryCollectionMap = null;
            this.type = type;
        }
        if (type == Type.OBJECT) {
            entryCollectionList = null;
            entryCollectionMap = new LinkedHashMap<String, Object>();
            this.type = Type.OBJECT;
        }
        if (type == Type.LIST) {
            entryCollectionList = new LinkedList<Object>();
            entryCollectionMap = null;
            this.type = Type.LIST;
        }
    }

	@Override
    public void append(FlexData other) {
        if ((type == Type.LIST || size() == 0) && other.type == Type.LIST) {
            for (Iterator<? extends Object> i = other.getIterator(); i.hasNext(); add(i.next())) { }
        }
    }

	@Override
    public void append(List<?> list) {
        if ((type == Type.LIST || size() == 0)) {
        	for(Iterator<?> i = list.iterator(); i.hasNext(); add(i.next())) { }
        }
    }
    

	@Override
    public void sort() {
        sort(new FlexDataComparator());
    }

	@SuppressWarnings("incomplete-switch")
	@Override
    public void sort(FlexDataComparator comparator) {
        switch (type) {
        case LIST: 
            sortList(comparator);
            break;

        case OBJECT: 
            sortMap(comparator);
            break;
        }
    }


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public int compareTo(IFlexData o) {
        int erg = 0;
        if (o instanceof FlexData) {
            FlexData other = (FlexData)o;
            if (type == other.type) {
                Comparator comparator = new FlexDataEntryComparator(type, new FlexDataComparator());
                Iterator<?> i = getIterator();
                for (Iterator<?> j = other.getIterator(); i.hasNext() && j.hasNext() && erg == 0; erg = comparator.compare(i.next(), j.next())) { }
            } else {
                erg = -1;
            }
        } else {
            erg = -1;
        }
        return erg;
    }
	
	@SuppressWarnings("rawtypes")
	protected class FlexDataEntryComparator implements Comparator {

        FlexDataComparator comparator;
        protected Type type;

        @SuppressWarnings({ "unchecked", "incomplete-switch" })
		@Override
		public int compare(Object o1, Object o2) {
            int erg = 0;
            switch (type) {
            case OBJECT: 
				java.util.Map.Entry<String, Object> e1 = (java.util.Map.Entry<String, Object>)o1;
                java.util.Map.Entry<String, Object> e2 = (java.util.Map.Entry<String, Object>)o2;
                erg = e1.getKey().toString().compareTo(e2.getKey().toString());
                if (erg == 0) {
                    erg = comparator.compare(e1.getValue(), e2.getValue());
                }
                break;

            case LIST:
                erg = comparator.compare(o1, o2);
                break;
            }
            return erg;
        }

        public FlexDataEntryComparator(Type type, FlexDataComparator comparator) {
            this.type = type;
            this.comparator = comparator;
        }
    }
	/* (non-Javadoc)
	 * @see com.airbus.bricks.flexdata.IFlexData#getDate(java.lang.String)
	 */
	@Override
	public Date getDate(String path, String format) {
		Date erg = null;
		String value = getString(path);
		if (!value.equals("")) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(format);
				erg = df.parse(value);
			} catch(Exception e) {
			}
		}
		return erg;
	}

	/* (non-Javadoc)
	 * @see com.airbus.bricks.flexdata.IFlexData#getDate(java.lang.String)
	 */
	@Override
	public Date getDate(String path) {
		Date erg = null;
		String value = getString(path);
		if (!value.equals("")) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(standardDateFormat);
				erg = df.parse(value);
			} catch(Exception e) {
			}
		}
		return erg;
	}

	/****************************************************************
	 * 
	 * Interface IFlexData
	 * 
	 ****************************************************************/
	@Override
	public void clear() {
        entryCollectionList = null;
        entryCollectionMap = null;
        type = Type.UNKNOWN;		
	}

	@Override
	public boolean containsKey(Object key) {
		return assertMap().containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return assertMap().containsValue(value);
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return assertMap().entrySet();
	}

	@Override
	public Object get(Object key) {
		return getValue(key.toString());
	}

	@Override
	public Set<String> keySet() {
		return assertMap().keySet();
	}

	@Override
	public Object put(String key, Object value) {
		setValue(key.toString(),value);
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		assertMap().putAll(m);
	}

	@Override
	public Object remove(Object key) {
		return assertMap().remove(key);
	}

	@Override
	public int size() {
        switch (type) {
        case LIST: 
            return entryCollectionList.size();

        case OBJECT: 
            return entryCollectionMap.size();
        }
        return 0;
	}

	@Override
	public Collection<Object> values() {
		Collection<Object> erg = null;
		if (type == Type.LIST) {
			erg = entryCollectionList;
		} else if (type == Type.OBJECT) {
			erg = entryCollectionMap.values();
		} 
		return erg;
	}

	/****************************************************************
	 * 
	 * Interface Cloneable
	 * 
	 ****************************************************************/
	@Override
	public Object clone() {
        switch (type) {
        case LIST: 
            return cloneList();

        case OBJECT: 
            return cloneMap();
        }
        return new FlexData();
    }

    protected Object cloneList() {
        FlexData erg = new FlexData();
        erg.type = type;
        List<Object> newentries = erg.assertList();
        for (Iterator<?> i = assertList().iterator(); i.hasNext();) {
            Object value = i.next();
            if (value instanceof FlexData) {
                newentries.add(((FlexData)value).clone());
            } else {
                newentries.add(value);
            }
        }

        return erg;
    }

    protected Object cloneMap() {
        FlexData erg = new FlexData();
        erg.type = type;
        Map<String, Object> newentries = erg.assertMap();
        for (Iterator<Map.Entry<String, Object>> i = assertMap().entrySet().iterator(); i.hasNext();) {
            java.util.Map.Entry<String, Object> e = i.next();
            Object value = e.getValue();
            if (value instanceof FlexData) {
                newentries.put(e.getKey(), ((FlexData)value).clone());
            } else {
                newentries.put(e.getKey(), value);
            }
        }

        return erg;
    }

	/****************************************************************
	 * 
	 * Public Members of FlexData
	 * 
	 ****************************************************************/
    public String serialize() {
        switch (type) {
        case LIST:
            return serializeList();

        case OBJECT:
            return serializeMap();

        case UNKNOWN:
            return serializeUnknown();
        }
        return "";
    }

	public String getStandardDateFormat() {
		return standardDateFormat;
	}

	public void setStandardDateFormat(String standardDateFormat) {
		this.standardDateFormat = standardDateFormat;
	}

    public String toString() {
        return serialize();
    }

	/****************************************************************
	 * 
	 * Internal Members of FlexData
	 * 
	 ****************************************************************/
    
	protected Map<String, Object> assertMap() {
        if (type == Type.LIST) {
            throw new FlexDataWrongTypeRuntimeException(type, Type.OBJECT); 
        }
        if (entryCollectionMap == null) {
            entryCollectionMap = new LinkedHashMap<String, Object>();
            type = Type.OBJECT;
        }
        return entryCollectionMap;
    }

    protected List<Object> assertList() {
        if (type == Type.OBJECT) {
            throw new FlexDataWrongTypeRuntimeException(type, Type.LIST); 
        }
        if (entryCollectionList == null) {
            entryCollectionList = new LinkedList<Object>();
            type = Type.LIST;
        }
        return entryCollectionList;
    }

    protected void setValueMap(String key, Object value) {
        assertMap().put(key, value);
    }

    protected String serializeList() {
        StringBuffer erg = new StringBuffer();
        erg.append("<FlexData type=\"");
        erg.append(FlexData.getTypename(type));
        erg.append("\">");
        for (Iterator<Object> i = assertList().iterator(); i.hasNext(); erg.append("</Entry>")) {
            Object v = i.next();
            erg.append("<Entry>");
            if (v instanceof FlexData) {
                erg.append(((FlexData)v).serialize());
            } else {
                erg.append(v.toString());
            }
        }

        erg.append("</FlexData>");
        return erg.toString();
    }
    protected String serializeMap() {
        StringBuffer erg = new StringBuffer();
        erg.append("<FlexData type=\"");
        erg.append(FlexData.getTypename(type));
        erg.append("\">");
        for (Iterator<Map.Entry<String, Object>> i = assertMap().entrySet().iterator(); i.hasNext(); erg.append("</Entry>")) {
            java.util.Map.Entry<String, Object> entry = i.next();
            Object k = entry.getKey();
            Object v = entry.getValue();
            if (v == null) {
            	v = "java.lang.null";
            }
            erg.append("<Entry");
            if (type == Type.OBJECT) {
                erg.append(" name=\"");
                erg.append(k.toString());
                erg.append("\"");
            }
            erg.append(">");
            if (v instanceof FlexData) {
                erg.append(((FlexData)v).serialize());
            } else {
                erg.append(v.toString());
            }
        }

        erg.append("</FlexData>");
        return erg.toString();
    }

    protected String serializeUnknown() {
        StringBuffer erg = new StringBuffer();
        erg.append("<FlexData type=\"");
        erg.append(FlexData.getTypename(Type.UNKNOWN));
        erg.append("\">");
        erg.append("</FlexData>");
        return erg.toString();
    }

    protected Map<String, Object> getEntries() {
        return assertMap();
    }
    
	/**
	 * @param key
	 * @param value
	 */
	protected void setValueDirect(String key, Object value) {
		setValueMap(key, value);
	}
	
    @SuppressWarnings("unchecked")
	protected void sortList(FlexDataComparator comparator) {
        if (size() > 1) {
            Object objects[] = entryCollectionList.toArray();
            Arrays.sort(objects, new FlexDataEntryComparator(type, comparator));
            entryCollectionList = new LinkedList<Object>();
            for (int i = 0; i < objects.length; i++) {
                add(objects[i]);
            }

        }
    }

    @SuppressWarnings("unchecked")
	protected void sortMap(FlexDataComparator comparator) {
        if (size() > 1) {
            Object objects[] = entryCollectionMap.entrySet().toArray();
            Arrays.sort(objects, new FlexDataEntryComparator(type, comparator));
            entryCollectionMap = new LinkedHashMap<String, Object>();
            for (int i = 0; i < objects.length; i++) {
                java.util.Map.Entry<String, Object> e = (java.util.Map.Entry<String, Object>)objects[i];
                setValueMap(e.getKey(), e.getValue());
            }

        }
    }

	@Override
	public String getHandledValue(String key) {
		return getHandledValue(key, null);
	}

	@Override
	public String getHandledValue(String key, String defaultvalue) {
		String erg = defaultvalue;
		Object o = getValue(key);
		if (o != null) {
			erg = o.toString().trim();
		}
		return erg;
	}

	@Override
	public void setHandledValue(String key, String value) {
		this.setValue(key, value);
	}
}
