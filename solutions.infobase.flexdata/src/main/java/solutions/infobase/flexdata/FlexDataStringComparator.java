package solutions.infobase.flexdata;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlexDataStringComparator extends FlexDataComparator {

	protected List< SortCriteria> fields = null;
	
	public FlexDataStringComparator(String fieldlist) {
		 
		if (!fieldlist.trim().equals("")) {
			fields = new LinkedList<SortCriteria>();
			
			String help[] = fieldlist.split(",");
			for (int i = 0; i< help.length;i++) {
				SortCriteria crit = new SortCriteria();
				crit.asc = true;
				crit.name = help[i];
				if (crit.name.startsWith("+")) {
					crit.name = crit.name.substring(1);
				} else {
					if (crit.name.startsWith("-")) {
						crit.name = crit.name.substring(1);
						crit.asc = false;
					}
				}
				fields.add(crit);
			}
		} else {
			fields = null;
		}
	}
    protected int compareFlexData(FlexData o1, FlexData o2) {
    	int erg = 0;
    	if (fields == null) {
    		erg = super.compareFlexData(o1,o2);
    	} else {
    		for (Iterator <SortCriteria> i = fields.iterator();i.hasNext();  ) {
    			SortCriteria sc = i.next();
    			if (o1.containsKey(sc.name)) {
	        		String sort1 = o1.getString(sc.name);
	        		String sort2 = o2.getString(sc.name);
	        		erg = sort1.compareTo(sort2);
	        		if (!sc.asc) erg = erg * -1;
	        		if (erg != 0) break;
    			}
    		}
//    		String sort1 = getSortHeader(o1);
//    		String sort2 = getSortHeader(o2);
//    		erg = sort1.compareTo(sort2);
    	}
        return erg;
    }
    
//	private String getSortHeader(FlexData fd) {
//		String erg = fd.getString("SortHeader");
//		if (erg == null || erg.equals("")) {
//			StringBuffer sb = new StringBuffer();
//    		for (int i=0;i<fields.length;i++) {
//    			if (sb.length() > 0) sb.append("#");
//    			sb.append(fd.getString(fields[i]));
//    		}
//    		erg = sb.toString();
//    		fd.setValue("SortHeader", erg);
//		}
//		return erg;
//	}
	
	class SortCriteria {
		public String name;
		public boolean asc ;
	}
}
