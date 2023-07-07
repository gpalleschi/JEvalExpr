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
	  E_lim,                      /* LIMITE DELLA SEQUENZA DI TOKEN */
	  E_lbrc,                     /* PARENTESI TONDA APERTA         */
	  E_comma,                    /* VIRGOLA                        */
	  E_rbrc,                     /* PARENTESI TONDA CHIUSA         */
	  E_value,                    /* VALORE                         */
	  E_op                        /* PARTE DI UN OPERATORE          */
}