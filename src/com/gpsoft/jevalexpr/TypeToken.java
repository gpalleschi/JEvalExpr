package com.gpsoft.jevalexpr;

/**
 * This enum represents type of token <br><br>
 * E_lim   : TOKEN SEQUENCE LIMIT <br>
 * E_lbrc  : OPEN ROUND BRACKET <br>
 * Comma   : COMMA <br>
 * E_rbrc  : CLOSING ROUND BRACKET <br>
 * E_value : VALUE <br>
 * E_op    : PART OF AN OPERATOR <br>
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public enum TypeToken {
	  E_lim,                      /* TOKEN SEQUENCE LIMIT */
	  E_lbrc,                     /* OPEN ROUND BRACKET   */
	  E_comma,                    /* COMMA                */
	  E_rbrc,                     /* CLOSING ROUND BRACKET */
	  E_value,                    /* VALUE                 */
	  E_op                        /* PART OF AN OPERATOR   */
}