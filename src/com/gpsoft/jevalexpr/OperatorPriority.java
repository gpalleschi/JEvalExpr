package com.gpsoft.jevalexpr;

/**
 * This class represents priority of execution of functions (high 0 - low 4)
 * will be executed before function with priority E_lev0, after E_lev1, ...
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public class OperatorPriority {
	public static final int E_lev0 = 0;   /* PRIORITA ZERO      */
	public static final int E_lev1 = 1;   /* PRIORITA UNO       */
	public static final int E_lev2 = 2;   /* PRIORITA DUE       */
	public static final int E_lev3 = 3;   /* PRIORITA TRE       */
	public static final int E_lev4 = 4;   /* PRIORITA QUATTRO   */
}
