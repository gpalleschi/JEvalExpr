package com.gpsoft.jevalexpr;

/**
 * This class represents type of syntax of functions 
 * 
 * E_funi                  : INTERNAL FUNCTION - SYNTAX : F<br> 
 * E_fun                   : GENERIC FUNCTION           : F([.[,.]])  <br>
 * E_one                   : OPERATOR 1 ARIO  - SYNTAX  : O.          <br>
 * E_two                   : OPERATOR 2 ARIO  - SYNTAX  : .O.         <br>
 * E_three                 : OPERATOR 3 ARIO  - SYNTAX  : .O.O.       <br>
 * E_four                  : OPERATOR 4 ARIO  - SYNTAX  : .O.O.O.     <br>
 * E_nmb_op_chain_syn_type : NUMBERS OF OPERATORS WITH CHAIN SYNTAX <br>
 * E_undef                 : OPERATOR n ARIO  - SYNTAX  : .O(.[,.])   <br>
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public class OperatorSyntaxType {
	  public static int E_funi = -2;                  /* INTERNAL FUNCTION - SYNTAX : F */
	  public static int E_fun = -1;                   /* GENERIC FUNCTION           : F([.[,.]])  */
	  public static int E_one = 0;                    /* OPERATOR 1 ARIO  - SYNTAX  : O.          */
	  public static int E_two = 1;                    /* OPERATOR 2 ARIO  - SYNTAX  : .O.         */
	  public static int E_three = 2;                  /* OPERATOR 3 ARIO  - SYNTAX  : .O.O.       */
	  public static int E_four = 3;                   /* OPERATOR 4 ARIO  - SYNTAX  : .O.O.O.     */
	  public static int E_nmb_op_chain_syn_type = 4;  /* NUMBERS OF OPERATORS WITH CHAIN SYNTAX */
	  public static int E_undef = 5;                  /* OPERATOR n ARIO  - SYNTAX  : .O(.[,.])   */
	  
}
