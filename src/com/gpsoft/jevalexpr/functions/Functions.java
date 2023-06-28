package com.gpsoft.jevalexpr.functions;

import java.util.HashMap;

public class Functions {
	
	static public final HashMap<String, Function> names = new HashMap<String, Function>() {
		private static final long serialVersionUID = 1L;
	{
	    put("to_number", new FTo_number());
	    put("not", new FNot());
	    put("mod", new FMod());
	    put("xor", new FXor());
	    put("or", new FOr());
	    put("in", new FIn());
	    put("and", new FAnd());
	    put("to_char", new FTo_char());
	    put("is_number", new FIs_number());
	    put("rtrim", new FRtrim());
	    put("ltrim", new FLtrim());
	    put("trim", new FTrim());
	    put("to_date", new FTo_date());
	    put("upper", new FUpper());
	    put("lower", new FLower());
	    put("rpad", new FRpad());
	    put("lpad", new FLpad());
	    put("substr", new FSubstr());
	    put("length", new FLength());
	    put("initcap", new FInitcap());
	    put("nvl", new FNvl());
	    put("is_null", new FIs_null());
	    put("instr", new FInstr());
	    put("reverse", new FReverse());
	    put("translate", new FTranslate());
	    put("replace", new FReplace());
	    put("like", new FLike());
	    put("decode", new FDecode());
	    put("sysdate", new FSysdate());
	    put("is_date", new FIs_date());
	    put("to_hhmiss", new FTo_hhmiss());
	    put("extr_array", new FExtr_array());
	    put("PI", new FPI());
	    put("E", new FEcm());
	    put("sin", new FSin());
	    put("cos", new FCos());
	    put("tan", new FTan());
	    put("asin", new FAsin());
	    put("acos", new FAcos());
	    put("atan", new FAtan());
	    put("abs", new FAbs());
	    put("round", new FRound());
	    put("ceil", new FCeil());
	    put("trunc", new FTrunc());
	    put("min", new FMin());
	    put("max", new FMax());
	    put("regexp_like", new FRegexp_like());
	    put("regexp_substr", new FRegexp_substr());
	    put("regexp_instr", new FRegexp_instr());
	  /* ******************************************************** */
	  /* Add below new Functions                                  */	    
	}};

}
