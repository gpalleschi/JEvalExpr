package com.gpsoft.jevalexpr.functions;

import java.util.HashMap;

public class Functions {
	
	static public final HashMap<String, Function> names = new HashMap<String, Function>() {
		private static final long serialVersionUID = 1L;
	{
	    put("to_number", new FTo_number("to_number"));
	    put("not", new FNot("not"));
	    put("or", new FOr("or"));
	}};

}
