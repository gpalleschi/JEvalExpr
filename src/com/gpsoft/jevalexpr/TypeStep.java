package com.gpsoft.jevalexpr;

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
	  E_if_then,                  /* OPERATOR C ?:             */
	  E_to_char,                  /* FUNCTION to_char             */
	  E_is_number,                /* FUNCTION is_number           */
	  E_to_number,                /* FUNCTION to_number           */
	  E_to_date,                  /* FUNCTION to_date             */
	  
	  E_to_secs,                  /* FUNCTION to_secs   TODO      */
	  
	  E_rtrim,                    /* FUNCTION rtrim               */
	  E_ltrim,                    /* FUNCTION ltrim               */
	  E_trim,                     /* FUNCTION trim                */
	  
	  E_rpad,                     /* FUNCTION rpad                */
	  E_lpad,                     /* FUNCTION lpad                */
	  E_decode,                   /* FUNCTION decode     TODO     */
	  E_translate,                /* FUNCTION translate           */
	  E_replace,                  /* FUNCTION replace             */
	  E_substr,                   /* FUNCTION substr              */
	  E_instr,                    /* FUNCTION instr               */
	  E_length,                   /* FUNCTION length              */
	  E_nvl,                      /* FUNCTION nvl                 */
	  E_is_null,                  /* FUNCTION is_null             */
	  E_like,                     /* OPERATOR like               */
	  E_upper,                    /* FUNCTION upper               */
	  E_lower,                    /* FUNCTION lower               */
	  E_initcap,                  /* FUNCTION initcap             */
	  E_diffdate,                 /* FUNCTION diffdate   TODO     */
	  E_reverse,                  /* FUNCTION reverse             */
	  E_systime,                  /* FUNCTION systime    TODO     */
	  E_to_hhmiss,                /* FUNCTION to_hhmiss  TODO     */
	  
	  E_extr_array,               /* FUNCTION extr_array    TODO  */
	  E_search_array,             /* FUNCTION search_array  TODO  */
	  
	  E_is_date,                  /* FUNCTION is_date       TODO  */
	  E_regexp_like,              /* FUNCTION regexp_like   TODO  */
	  E_regexp_substr,            /* FUNCTION regexp_substr TODO  */
	  E_regexp_instr              /* FUNCTION regexp_substr TODO  */
}
