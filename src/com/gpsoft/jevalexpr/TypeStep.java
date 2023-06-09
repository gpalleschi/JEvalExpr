package com.gpsoft.jevalexpr;

public enum TypeStep {
	  E_constant,                 /* COSTANTE                     */
	  E_variable,                 /* VARIABILE                    */
	  E_unary_plus,               /* + UNARIO                     */
	  E_unary_minus,              /* - UNARIO                     */
	  E_not,                      /* not LOGICO                   */
	  E_mul,                      /* * MOLTIPLICAZIONE            */
	  E_div,                      /* / DIVISIONE                  */
	  E_mod,                      /* % RESTO DI DIVISIONE         */
	  E_exp,                      /* ^ ESPONENZIALE               */
	  E_eq,                       /* = UGUAGLIANZA LOGICA         */
	  E_ne,                       /* != DIVERSITA LOGICA          */
	  E_gt,                       /* > MAGGIORE LOGICO            */
	  E_ge,                       /* >= MAGGIORE UGUALE LOGICO    */
	  E_lt,                       /* < MINORE LOGICO              */
	  E_le,                       /* <= MINORE UGUALE LOGICO      */
	  E_and,                      /* & AND LOGICO                 */
	  E_sum,                      /* + BINARIO                    */
	  E_sub,                      /* - BINARIO                    */
	  E_xor,                      /* xor LOGICO                   */
	  E_or,                       /* or LOGICO                    */
	  E_conc,                     /* || CONCATENAZIONE            */
	  E_in,                       /* in                           */
	  E_if_then,                  /* L'OPERATORE C ?:             */
	  E_to_char,                  /* FUNZIONE to_char             */
	  E_is_number,                /* FUNZIONE is_number           */
	  E_to_number,                /* FUNZIONE to_number           */
	  
	  E_to_date,                  /* FUNZIONE to_date   TODO      */
	  E_to_secs,                  /* FUNZIONE to_secs   TODO      */
	  
	  E_rtrim,                    /* FUNZIONE rtrim               */
	  E_ltrim,                    /* FUNZIONE ltrim               */
	  E_trim,                     /* FUNZIONE trim                */
	  
	  E_rpad,                     /* FUNZIONE rpad                */
	  E_lpad,                     /* FUNZIONE lpad                */
	  E_decode,                   /* FUNZIONE decode              */
	  E_translate,                /* FUNZIONE translate           */
	  E_replace,                  /* FUNZIONE replace             */
	  E_substr,                   /* FUNZIONE substr              */
	  E_instr,                    /* FUNZIONE instr               */
	  E_length,                   /* FUNZIONE length              */
	  E_nvl,                      /* FUNZIONE nvl                 */
	  E_is_null,                  /* FUNZIONE is_null             */
	  E_is_like,                  /* OPERATORE is_like            */
	  E_upper,                    /* FUNZIONE upper               */
	  E_lower,                    /* FUNZIONE lower               */
	  E_initcap,                  /* FUNZIONE initcap             */
	  E_diffdate,                 /* FUNZIONE diffdate            */
	  E_reverse,                  /* FUNZIONE reverse             */
	  E_systime,                  /* FUNZIONE systime             */
	  E_to_hhmiss,                /* FUNZIONE to_hhmiss           */
	  E_extr_array,               /* FUNZIONE extr_array          */
	  E_search_array,             /* FUNZIONE search_array        */
	  E_is_date,                  /* FUNZIONE is_date             */
	  E_chk_tab_field,
	  E_get_tab_field,            /* FUNZIONE get_tab_field       */
	  E_regexp_like,              /* FUNZIONE regexp_like         */
	  E_regexp_substr,            /* FUNZIONE regexp_substr       */
	  E_regexp_instr              /* FUNZIONE regexp_substr       */
}
