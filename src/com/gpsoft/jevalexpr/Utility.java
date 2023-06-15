package com.gpsoft.jevalexpr;
import java.util.regex.Pattern;

import com.gpsoft.jevalexpr.log.Logger;

import java.util.regex.Matcher;

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
	
	public static boolean isDate(Object obj) {
		if ( obj.getClass().getName().compareTo("java.time.LocalDateTime") == 0 ||
		     obj.getClass().getName().compareTo("java.time.LocalDate") == 0  ||
		     obj.getClass().getName().compareTo("java.time.LocalTime") == 0 ) {
			return true;
		} else {
			return false;
		}
	}

    public static String rpad(String input, int length, String paddingString) {
        StringBuilder result = new StringBuilder(input);
        
        while (result.length() < length) {
            result.append(paddingString);
        }
        
        return result.toString().substring(0,length);
    }
    
    public static String lpad(String input, int length, String paddingString) {
        StringBuilder result = new StringBuilder();
        
        while (result.length() + input.length() < length) {
            result.append(paddingString);
        }
        
        result.append(input);
        
        String ris = result.toString();
        int lengthToSub = ris.length()-length;
        if ( lengthToSub < 0 ) {
        	lengthToSub*=-1;
        }
        
        return ris.substring(lengthToSub);
    }

    public static String initCap(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        
        String[] words = input.toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }
    
    public static int instr(String input, String searchStr, int startPosition, int occurrenceCount) {
        if (input == null || searchStr == null || input.isEmpty() || searchStr.isEmpty() || startPosition <= 0 || occurrenceCount <= 0) {
            return 0;
        }
        
        int currentIndex = startPosition - 1;
        int occurrence = 0;
        
        while (occurrence < occurrenceCount && currentIndex < input.length()) {
            int index = input.indexOf(searchStr, currentIndex);
            if (index == -1) {
                break;
            }
            
            currentIndex = index + 1;
            occurrence++;
        }
        
        if (occurrence == occurrenceCount) {
            return currentIndex;
        } else {
            return 0;
        }
    }
    
    public static boolean like(String input, String pattern) {
        if (input == null || pattern == null) {
            return false;
        }
        
        String regexPattern = pattern.replace(".", "\\.")
                                     .replace("?", ".")
                                     .replace("%", ".*")
                                     .replace("_", ".");
        
        return input.matches("(?i)" + regexPattern);
    }
    
}
