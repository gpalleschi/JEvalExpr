package com.gpsoft.jevalexpr.log;

import java.util.ArrayList;

import com.gpsoft.jevalexpr.Token;

/**
 * This class represents log class actually all logs are printed in stdout <br>
 * Are permitted 4 levels of Logs (ERROR, WARNING, DEBUG and INFO) <br>
 * This log can be replaced by log4j utility.
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public class Logger {
	
	// Level 
	
	private static int level = 0;
	
	// Binary flags : ERROR - WARNING - DEBUG - INFO
	//                0       0         0       1      = 1 
	//                0       0         1       0      = 2 
	//                0       0         1       1      = 3 
	//                0       1         0       0      = 4 
	//                0       1         0       1      = 5 
	//                0       1         1       0      = 6 
	//                0       1         1       1      = 7 
	//                1       0         0       0      = 8 
	//                1       0         0       1      = 9 
	//                1       0         1       0      = 10
	//                1       0         1       1      = 11
	//                1       1         0       0      = 12
	//                1       1         0       1      = 13
	//                1       1         1       0      = 14
	//                1       1         1       1      = 15
	
	public static void setLevel(int level) {
		if ( level < 1 || level > 15 ) return;
		Logger.level = level;
	}
	
	
	
	public static <T> void always(T message) {
		if ( level == 0 ) return;
		System.out.println(message);
	}

	public static <T> void info(T message) {
		if ( level != 1 && level != 3 && level != 5 && level != 7 && level != 9 && level != 11 && level != 13 && level != 15 ) return;
		System.out.println("INFO : " + message);
	}

	public static <T> void debug(T message) {
		if ( level != 2 && level != 3 && level != 6 && level != 7 && level != 10 && level != 11 && level != 14 && level != 15 ) return;
		System.out.println("DEBUG : " + message);
	}

	public static <T> void warning(T message) {
		if ( level != 4 && level != 5 && level != 6 && level != 7 && level != 12 && level != 13 && level != 14 && level != 15 ) return;
		System.out.println("WARNING : " + message);
	}

	public static <T> void error(T message) {
		if ( level < 8 ) return;
		System.out.println("ERROR : " + message);
	}
	
	public static void printExpAscii(ArrayList<Token<?>> tokens) {
		  if ( level == 0 ) return;
		  int idx;

		  System.out.print(tokens.get(1).getTokenName());
		  for ( idx = 2; idx < tokens.size() - 1; idx ++ )
		  {
  		    System.out.print(" " + tokens.get(idx).getTokenName());
		  }
 		  System.out.print("\n");

		  return;
	}

}
