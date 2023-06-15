package com.gpsoft.jevalexpr.functions;

import java.util.HashMap;

public class Functions {
	
	static public final HashMap<String, Function> names = new HashMap<String, Function>() {
		private static final long serialVersionUID = 1L;
	{
	    put("to_number", new FTo_number("to_number"));
	    put("not", new FNot("not"));
	    put("mod", new FMod("mod"));
	    put("xor", new FXor("xor"));
	    put("or", new FOr("or"));
	    put("in", new FIn("in"));
	    put("and", new FAnd("and"));
	    put("to_char", new FTo_char("to_char"));
	    put("is_number", new FIs_number("is_number"));
	    put("rtrim", new FRtrim("rtrim"));
	    put("ltrim", new FLtrim("ltrim"));
	    put("trim", new FTrim("trim"));
	    put("to_date", new FTo_date("to_date"));
	    put("upper", new FUpper("upper"));
	    put("lower", new FLower("lower"));
	    put("rpad", new FRpad("rpad"));
	    put("lpad", new FLpad("lpad"));
	    put("substr", new FSubstr("substr"));
	    put("length", new FLength("length"));
	    put("initcap", new FInitcap("initcap"));
	    put("nvl", new FNvl("nvl"));
	    put("is_null", new FIsNull("is_null"));
	    put("instr", new FInstr("instr"));
	    put("reverse", new FReverse("reverse"));
	    put("translate", new FTranslate("translate"));
	    put("replace", new FReplace("replace"));
	    put("like", new FLike("like"));
	    put("decode", new FDecode("decode"));
	}};

}
