package com.gpsoft.jevalexpr;

/**
 * This enum represents all functions configurated in the library
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public enum TypeStep {
	  E_constant,                 /* COSTANT                      */
	  E_variable,                 /* VARIABLE                    */
	  E_unary_plus,               /* + UNARY                     */
	  E_unary_minus,              /* - UNARY                     */
	  E_not,                      /* not LOGIC                   */
	  E_mul,                      /* * MOLTIPLICAZIONE            */
	  E_div,                      /* / DIVISION                  */
	  E_mod,                      /* % REST OF DIVISION         */
	  E_exp,                      /* ^ ESPONENTIAL               */
	  E_eq,                       /* = EQUAL LOGIC         */
	  E_ne,                       /* != DIFFERENT LOGIC          */
	  E_gt,                       /* > MORE LOGIN LOGIC           */
	  E_ge,                       /* >= MORE EQUAL LOGIC    */
	  E_lt,                       /* < LESS LOGIC              */
	  E_le,                       /* <= MINOR EQUAL LOGIC      */
	  E_and,                      /* & AND LOGIC                 */
	  E_sum,                      /* + BINARY                    */
	  E_sub,                      /* - BINARY                    */
	  E_xor,                      /* xor LOGIC                   */
	  E_or,                       /* or LOGIC                    */
	  E_conc,                     /* || CONCATENATION            */
	  E_in,                       /* in                           */
	  E_if_then,                  /* OPERATOR C ?:                */
	  E_to_char,                  /* FUNCTION to_char             */
	  E_is_number,                /* FUNCTION is_number           */
	  E_to_number,                /* FUNCTION to_number           */
	  E_to_date,                  /* FUNCTION to_date             */
	  E_rtrim,                    /* FUNCTION rtrim               */
	  E_ltrim,                    /* FUNCTION ltrim               */
	  E_trim,                     /* FUNCTION trim                */
	  E_rpad,                     /* FUNCTION rpad                */
	  E_lpad,                     /* FUNCTION lpad                */
	  E_decode,                   /* FUNCTION decode              */
	  E_translate,                /* FUNCTION translate           */
	  E_replace,                  /* FUNCTION replace             */
	  E_substr,                   /* FUNCTION substr              */
	  E_instr,                    /* FUNCTION instr               */
	  E_length,                   /* FUNCTION length              */
	  E_nvl,                      /* FUNCTION nvl                 */
	  E_is_null,                  /* FUNCTION is_null             */
	  E_is_date,                  /* FUNCTION is_date             */
	  E_like,                     /* OPERATOR like                */
	  E_upper,                    /* FUNCTION upper               */
	  E_lower,                    /* FUNCTION lower               */
	  E_initcap,                  /* FUNCTION initcap             */
	  E_reverse,                  /* FUNCTION reverse             */
	  E_sysdate,                  /* FUNCTION sysdate             */
	  E_to_hhmiss,                /* FUNCTION to_hhmiss           */
	  E_extr_array,               /* FUNCTION extr_array          */
	  E_PI,                       /* FUNCTION PI                  */
	  E_E,                        /* FUNCTION E (mathematical constant) */
	  E_sin,                      /* FUNCTION Sin                 */
	  E_cos,                      /* FUNCTION Cos                 */
	  E_tan,                      /* FUNCTION Tan                 */
	  E_asin,                     /* FUNCTION ASin                */
	  E_acos,                     /* FUNCTION ACos                */
	  E_atan,                     /* FUNCTION ATan                */
	  E_abs,                      /* FUNCTION abs                 */
	  E_round,                    /* FUNCTION round               */
	  E_ceil,                     /* FUNCTION ceil                */
	  E_trunc,                    /* FUNCTION trunc               */
	  E_min,                      /* FUNCTION min                 */
	  E_max,                      /* FUNCTION max                 */
	  E_regexp_like,              /* FUNCTION regexp_like         */
	  E_regexp_substr,            /* FUNCTION regexp_substr       */
	  E_regexp_instr,             /* FUNCTION regexp_instr        */
	  E_between_and               /* FUNCTION between_and         */
	  /* ******************************************************** */
	  /* Add below new Functions                                  */
}
