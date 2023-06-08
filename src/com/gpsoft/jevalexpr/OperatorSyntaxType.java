package com.gpsoft.jevalexpr;

public class OperatorSyntaxType {
	  public static int E_fun = -1;                   /* FUNZIONE GENERICA - SINTASSI: F([.[,.]])  */
	  public static int E_one = 0;                    /* OPERATORE 1 ARIO  - SINTASSI: O.          */
	  public static int E_two = 1;                    /* OPERATORE 2 ARIO  - SINTASSI: .O.         */
	  public static int E_three = 2;                  /* OPERATORE 3 ARIO  - SINTASSI: .O.O.       */
	  public static int E_four = 3;                   /* OPERATORE 4 ARIO  - SINTASSI: .O.O.O.     */
	  public static int E_nmb_op_chain_syn_type = 4;  /* NUMERO DI OPERATORI CON SINTASSI A CATENA */
	  public static int E_undef = 5;                  /* OPERATORE n ARIO  - SINTASSI: .O(.[,.])   */
	  
}
