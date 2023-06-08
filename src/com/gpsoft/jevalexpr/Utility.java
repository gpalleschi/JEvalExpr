package com.gpsoft.jevalexpr;

public class Utility {
	
	public static int getString(String string, int start_idx, char sep) {
		int ret = start_idx;
		int idx = 0;
		for(idx=start_idx+1;idx<string.length();idx++) {
			if ( string.charAt(idx) == sep ) {
				
				if ( idx+1 < string.length() && string.charAt(idx+1) == sep ) {
					idx++;
					continue;
				} else {
				  ret = idx;	
				  break;	
				}
			}
		}
		
		if ( idx >= string.length() ) ret = -1;
		
		return ret;
	}
	
	public static boolean isDouble(Object obj) {
		return obj.getClass().getName().compareTo("java.lang.Double") == 0 ? true : false;
	}

	public static boolean isString(Object obj) {
		return obj.getClass().getName().compareTo("java.lang.String") == 0 ? true : false;
	}

	public static boolean isInteger(Object obj) {
		return obj.getClass().getName().compareTo("java.lang.Integer") == 0 ? true : false;
	}

	public static boolean isBoolean(Object obj) {
		return obj.getClass().getName().compareTo("java.lang.Boolean") == 0 ? true : false;
	}


}
