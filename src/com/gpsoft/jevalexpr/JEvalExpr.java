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
		  
		  Logger.setLevel(15);
		  
		  Logger.debug(variables[0].getValue());
		  Logger.debug(variables[1].getValue());
		  
//		  expression = new Expression("((1 - 2 + to_number(v1)))",variables);
		  
//		  String Expression = "to_number(v1) + 19 + decode(to_date('20210101','yyyyMMdd'), to_date('20210102','yyyyMMdd'),1,18)";
//		  String Expression = "to_date('20210101 000000','yyyyMMdd HHmmss') - to_date('010000','HHmmss')";
//		  String Expression = "to_hhmiss(10.0)";
		  String Expression = "(1 between 1 and 2) and (1=1)";
		  
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
				  Logger.always("Result : >" + expression.getResult().value + "<");
				  Logger.always("Type : " + expression.getResult().getTypeData());
				  Logger.always("IsNull : " + expression.getResult().isNull);
			  }
		  }
		  

	}

}
