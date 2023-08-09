package com.gpsoft.jevalexpr;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

import com.gpsoft.jevalexpr.expr.Expression;
import com.gpsoft.jevalexpr.log.Logger;

/**
 * This class represents main of Test Tool JEvalExpr 
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public class JEvalExpr {
	
	private static String version = "1.0.0";
	
	public static void logoJEvalExpr() {
		System.out.print("\n\n	       _ ______          _ ______                 ");
		System.out.print("\n	      | |  ____|        | |  ____|                ");
		System.out.print("\n	      | | |____   ____ _| | |__  __  ___ __  _ __ ");
		System.out.print("\n	  _   | |  __\\ \\ / / _` | |  __| \\ \\/ / '_ \\| '__|");
		System.out.print("\n	 | |__| | |___\\ V / (_| | | |____ >  <| |_) | |   ");
		System.out.print("\n	  \\____/|______\\_/ \\__,_|_|______/_/\\_\\ .__/|_|   ");
		System.out.print("\n	                                      | |         ");
		System.out.print("\n	                                      |_| Ver. " + version);
		System.out.print("\n\n Java Library and Tool to parse and execute functions in a language SQL Like");
		System.out.print("\n It's possible to create and extend the library with new functions.");
		System.out.print("\n Released Under GNU General Public License v3.0");
		System.out.print("\n\n *** ONLINE TEST TOOL *** ");
		System.out.print("\n\n Variables preloaded : ");
		System.out.print("\n\n v1 (String) value : \"Hello World!!!\"");
		System.out.print("\n v2 (Boolean) value : true");
		System.out.print("\n v3 (Double) value : 1.234567");
		System.out.print("\n v4 (Integer) value : 2");
		System.out.print("\n v5 (String) value : \"ABCDEFGHI\"");
		System.out.print("\n v6 (Boolean) value : false");
		System.out.print("\n v7 (Double) value : 4567.34566");
		System.out.print("\n v8 (Integer) value : 7513\n\n");
	}

	public static void clearConsole() throws IOException, InterruptedException {
		ProcessBuilder pb;
		if( System.getProperty( "os.name" ).startsWith( "Window" ) ) {
		   new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		else {
		   pb = new ProcessBuilder("clear");
     	   pb.start();
		}
			
	}	
	
	public static void main(String args[]) throws Exception {
		
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    // Change Level of Log 
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
		  Logger.setLevel(8);
		  String Expression = "";

		  Expression expression = null;
		  
		  ArrayList<Variable<?>> variables = new ArrayList<Variable<?>>();
		  
		  variables.add(new Variable<String>("v1", "Hello World!!!"));
		  variables.add(new Variable<Boolean>("v2", true));
		  variables.add(new Variable<Double>("v3", 1.234567));
		  variables.add(new Variable<Integer>("v4", 2));
		  variables.add(new Variable<String>("v5", "ABCDEFGHI"));
		  variables.add(new Variable<Boolean>("v6", false));
		  variables.add(new Variable<Double>("v7", 4567.34566));
		  variables.add(new Variable<Integer>("v8", 7513));

		  for(int i=0;i<variables.size();i++) {
			     Logger.debug("v" + (i + 1) + " : " + variables.get(i).getValue());
		  }
		  Logger.debug("");
		  
		  while((Expression.compareTo("x") != 0) && (Expression.compareTo("X") != 0)) {
			    clearConsole();
			    logoJEvalExpr();
				
			    System.out.print("\nEnter Expression or X to Exit : ");
			    Expression = br.readLine();
			    System.out.print("\n\n");
			    
			    if ( Expression.length() > 0 && (Expression.compareTo("x") != 0) && (Expression.compareTo("X") != 0)) {
					  expression = new Expression(Expression,variables);
					  
					  if ( !expression.compExpr() ) {
						  Logger.error("During Compilation");
					  } else {
						  Logger.info("Compilation OK.");
						  if ( expression.execExpr(variables) != 0 ) {
							  Logger.error("During execution");
						  } else {
							  Logger.info("Execution OK.");
							  Logger.always("-----------------------------------------------");
							  Logger.always("Result : >" + expression.getResult().getValue() + "<");
							  Logger.always("Type : " + expression.getResult().getTypeData());
							  Logger.always("IsNull : " + expression.getResult().isNull());
						  }
					  }
					  
					  System.out.print("\nContinue (Y/N) : ");
					  String sOption = br.readLine();
					  
					  if ( (sOption.compareTo("N") == 0) || (sOption.compareTo("n") == 0) ) break;
			    }
			    
		  }
		  
	}

}
