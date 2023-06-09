package com.gpsoft.jevalexpr;

import com.gpsoft.jevalexpr.expr.Expression;
import com.gpsoft.jevalexpr.log.Logger;

public class JEvalExpr {
	
	public static void main(String args[]) throws Exception {

		  Expression expression = null;
		  
		  Variable<?>[] variables = {new Variable<String>("v1", "11"),
                                     new Variable<Boolean>("v2", true),
                                     new Variable<Double>("v3", 1.23),
                                     new Variable<Integer>("v4", 2),
				  };
		  
		  Logger.setLevel(8);
		  
		  Logger.debug(variables[0].getValue());
		  Logger.debug(variables[1].getValue());
		  
//		  expression = new Expression("((1 - 2 + to_number(v1)))",variables);
		  
		  String Expression = " rtrim('12222     ') ";
		  
		  expression = new Expression(Expression,variables);
		  
		  if ( !expression.compExpr() ) {
			  Logger.error("During Compilation");
		  } else {
			  Logger.info("Compilation OK.");
		  }
		  
		  if ( expression.execExpr(variables) != 0 ) {
			Logger.error("During execution");
		  } else {
			Logger.info("Compilation OK.");
		  }
	}

}
