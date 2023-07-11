package com.gpsoft.jevalexpr;

import java.util.ArrayList;

import com.gpsoft.jevalexpr.expr.Expression;
import com.gpsoft.jevalexpr.log.Logger;

public class JEvalExprTest {

	static ArrayList<Variable<?>> variables = new ArrayList<Variable<?>>();
	static Expression expression = null;
	static int testOk = 0;
	static int testKo = 0;
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	
	
	public static class TestData<T> {
		String testName;
		String expression;
		boolean isNull;
		TypeData typeData;
		T value;
		public TestData(String testName, String expression, boolean isNull, TypeData typeData, T value) {
			super();
			this.testName = testName;
			this.expression = expression;
			this.isNull = isNull;
			this.typeData = typeData;
			this.value = value;
		}
		public String getTestName() {
			return testName;
		}
		public String getExpression() {
			return expression;
		}
		public boolean isNull() {
			return isNull;
		}
		public TypeData getTypeData() {
			return typeData;
		}
		public T getValue() {
			return value;
		}
	}

	static ArrayList<TestData<?>> tests = new ArrayList<TestData<?>>();
	
	public static void init() {
		
		 Logger.setLevel(0);
		 // Create variables to test
		 variables.add(new Variable<String>("v1", "Hello World!!!"));
		 variables.add(new Variable<Boolean>("v2", true));
		 variables.add(new Variable<Double>("v3", 1.234567));
		 variables.add(new Variable<Integer>("v4", 2));
		 variables.add(new Variable<String>("v5", "ABCDEFGHI"));
		 variables.add(new Variable<Boolean>("v6", false));
		 variables.add(new Variable<Double>("v7", 4567.34566));
		 variables.add(new Variable<Integer>("v8", 7513));
		 
		 // Add Tests
		 // Name Test, Expression, is Null, TypeData, value 
		 tests.add(new TestData<Boolean>("eq 1","1=1", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("eq 2","1!=1", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("eq 3","1.23 != 1.23", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("eq 4","'PIPPO' = 'PLUTO'", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("eq 5","'PIPPO' = 'PIPPO'", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("eq 6","1.23 = 1.23", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Integer>("abs 1","abs(-123)", false, TypeData.E_int, 123));
		 tests.add(new TestData<Double>("abs 2","abs(-98.23)", false, TypeData.E_double, 98.23));
		 tests.add(new TestData<Integer>("abs 3","abs(364)", false, TypeData.E_int, 364));
		 tests.add(new TestData<Double>("acos 1","acos(0)", false, TypeData.E_double, 1.5707963267948966));
		 tests.add(new TestData<Double>("acos 2","acos(180)", false, TypeData.E_double, Double.NaN));
		 tests.add(new TestData<Boolean>("and 1","1 and 1", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("and 2","1 and 0", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Double>("asin 1","asin(0)", false, TypeData.E_double, 0.0));
		 tests.add(new TestData<Double>("tan 1","tan(180)", false, TypeData.E_double, 1.3386902103511544));
		 tests.add(new TestData<Boolean>("between and 1","1 between 1 and 2", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("between and 2","3 between 1 and 2", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("between and 3","1.54 between 1.55 and 2.0", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("between and 4","5.345 between 4.23 and 6.73", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Integer>("ceil 1","ceil(5.345)", false, TypeData.E_int, 6));
		 tests.add(new TestData<Integer>("ceil 2","ceil(3.0)", false, TypeData.E_int, 3));
		 tests.add(new TestData<String>("conc 1","'pippo' || '1234'", false, TypeData.E_string, "pippo1234"));
		 tests.add(new TestData<Double>("cos 1","cos(0)", false, TypeData.E_double, 1.0));
		 tests.add(new TestData<Integer>("decode 1","decode('pippo','pluto',1,'paperino',2,3)", false, TypeData.E_int, 3));
		 tests.add(new TestData<String>("decode 2","decode(12,1,'A',2,'B',12,'C','D')", false, TypeData.E_string, "C"));
		 tests.add(new TestData<Double>("decode 3","decode(1.3,1.1,12.34,1.2,13.11,1.3,12.0)", false, TypeData.E_double, 12.0));
		 tests.add(new TestData<Double>("div 1","15/2", false, TypeData.E_double, 7.5));
		 tests.add(new TestData<Double>("div 2","8/2", false, TypeData.E_double, 4.0));
		 tests.add(new TestData<Double>("E 1","E", false, TypeData.E_double, 2.718281828459045));
		 tests.add(new TestData<Double>("Exp 1","2^3", false, TypeData.E_double, 8.0));
		 tests.add(new TestData<Double>("Exp 2","2^3.2", false, TypeData.E_double, 9.18958683997628));
		 tests.add(new TestData<Double>("Exp 3","1.2^1.8", false, TypeData.E_double, 1.388437205763783));
		 tests.add(new TestData<String>("extr array 1","extr_array('1,223,31,4987',2,',')", false, TypeData.E_string, "223"));
		 tests.add(new TestData<String>("extr array 2","extr_array('1,223,31,4987',6,',')", true, TypeData.E_string, ""));
		 tests.add(new TestData<Boolean>("gt 1"," 2 >= 1", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("gt 2"," 22.12 >= 22.13", false, TypeData.E_boolean, false));
		 tests.add(new TestData<String>("if_then_else 1"," (1=1) ? 'vero' : 'falso'", false, TypeData.E_string, "vero"));
		 tests.add(new TestData<String>("if_then_else 2"," (1!=1) ? 'vero' : 'falso'", false, TypeData.E_string, "falso"));
		 tests.add(new TestData<Integer>("if_then_else 3"," (1=1) ? 12 : 10", false, TypeData.E_int, 12));
		 tests.add(new TestData<Double>("if_then_else 4"," (0) ? 7.5 : 8.12", false, TypeData.E_double, 8.12));
		 tests.add(new TestData<Boolean>("in 1"," 'pippo' in ('pluto','paperino','minni','pluto') ", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("in 2"," 'pluto' in ('pippo','pluto','paperino','minni') ", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("in 3"," 12 in (10,12,13,11,10) ", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("in 4"," 12.3 in (11.0,12.4,11.0,12.3,19.34) ", false, TypeData.E_boolean, true));
		 tests.add(new TestData<String>("Initcap 1"," initcap('gianni') ", false, TypeData.E_string, "Gianni"));
		 tests.add(new TestData<String>("Initcap 2"," initcap('JOHN') ", false, TypeData.E_string, "John"));
		 tests.add(new TestData<Integer>("Instr 1"," instr('aXaaXaaaX','X') ", false, TypeData.E_int, 2));
		 tests.add(new TestData<Integer>("Instr 2"," instr('aXaaXaaaX','Y') ", false, TypeData.E_int, 0));
		 tests.add(new TestData<Integer>("Instr 3"," instr('aXaaXaaaXaaaaX','X',3) ", false, TypeData.E_int, 5));
		 tests.add(new TestData<Integer>("Instr 4"," instr('aXaaXaaaXaaaaX','X',1,3) ", false, TypeData.E_int, 9));
		 tests.add(new TestData<Integer>("Instr 5"," instr('xxYYVxxxxxxYYZxxxxYY','YY',1) ", false, TypeData.E_int, 3));
		 tests.add(new TestData<Boolean>("Is_date 1"," is_date('20230710')", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("Is_date 2"," is_date('20230732','yyyyMMdd')", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("Is_date 3"," is_date('2023/07/09','yyyy/MM/dd')", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("Is_null 1"," is_null('')", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("Is_null 2"," is_null(trim(' '))", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("Is_null 3"," is_null(1)", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("Is_number 1"," is_number('1')", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("Is_number 2"," is_number('12.3')", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("Is_number 3"," is_number(('1=2'))", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("Is_number 4"," !is_number('A')", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("le 1"," 1 <= 3", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("le 2"," 1 <= 1", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("le 3"," 22.15 <= 22.13", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("le 4"," 763.63 <= 763.63", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Integer>("length 1"," length('pippo')", false, TypeData.E_int, 5));
		 tests.add(new TestData<Integer>("length 2"," length(trim(' '))", false, TypeData.E_int, 0));
		 tests.add(new TestData<Boolean>("like 1","'pippo' like 'p%'", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("like 2","'pippo' like '%%'", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("like 3","'pippo' like '%u%'", false, TypeData.E_boolean, false));
		 tests.add(new TestData<String>("lower 1","lower('JOHN')", false, TypeData.E_string, "john"));
		 tests.add(new TestData<String>("lower 2","lower('fR13nd')", false, TypeData.E_string, "fr13nd"));
		 tests.add(new TestData<String>("lpad 1","lpad('12345',7)", false, TypeData.E_string, "  12345"));
		 tests.add(new TestData<String>("lpad 2","lpad('12345',10,'_')", false, TypeData.E_string, "_____12345"));
		 tests.add(new TestData<String>("lpad 3","lpad('12345',10,'xyz')", false, TypeData.E_string, "yzxyz12345"));
		 tests.add(new TestData<Boolean>("lt 1"," 1 < 3", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("lt 2"," 22.15 < 22.13", false, TypeData.E_boolean, false));
		 tests.add(new TestData<String>("ltrim 1","ltrim('   3456 ')", false, TypeData.E_string, "3456 "));
		 tests.add(new TestData<String>("ltrim 2","ltrim('ABCD  ')", false, TypeData.E_string, "ABCD  "));
		 tests.add(new TestData<String>("ltrim 3","ltrim('ABCD')", false, TypeData.E_string, "ABCD"));
		 tests.add(new TestData<Integer>("max 1","max(1,2,8,4,5,3)", false, TypeData.E_int, 8));
		 tests.add(new TestData<Double>("max 2","max(1.9,2,8,4,8.1,3)", false, TypeData.E_double, 8.1));
		 tests.add(new TestData<String>("max 3","max('C','D','A','L','Z','M')", false, TypeData.E_string, "Z"));
		 tests.add(new TestData<Integer>("min 1","min(1,2,8,4,5,3)", false, TypeData.E_int, 1));
		 tests.add(new TestData<Double>("min 2","min(1.9,2,8,1.1,8.1,3)", false, TypeData.E_double, 1.1));
		 tests.add(new TestData<String>("min 3","min('C','D','A','L','Z','M')", false, TypeData.E_string, "A"));
		 tests.add(new TestData<Integer>("mod 1","2%2", false, TypeData.E_int, 0));
		 tests.add(new TestData<Integer>("mod 2","77%2", false, TypeData.E_int, 1));
		 tests.add(new TestData<Double>("mod 3","1.2%2", false, TypeData.E_double, 1.2));
		 tests.add(new TestData<Double>("mod 4","4.5%4.5", false, TypeData.E_double, 0.0));
		 tests.add(new TestData<Double>("mod 5","4.5%2.0", false, TypeData.E_double, 0.5));
		 tests.add(new TestData<Double>("Mul 1","4.5*2", false, TypeData.E_double, 9.0));
		 tests.add(new TestData<Integer>("Mul 2","4*2", false, TypeData.E_int, 8));
		 tests.add(new TestData<Double>("Mul 3","5.12*3.45", false, TypeData.E_double, 17.664));
		 tests.add(new TestData<Boolean>("ne 1"," 1 != 3", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("ne 2"," 1.5 != 1.5", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Boolean>("ne 3"," (1=1) != (1=0)", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("ne 4"," 'A' != 'AB' ", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("not 1"," 1 != 3", false, TypeData.E_boolean, true));
		 tests.add(new TestData<Boolean>("not 2"," !(1 = 1)", false, TypeData.E_boolean, false));
		 tests.add(new TestData<Integer>("not 3"," !1", false, TypeData.E_int, 0));
		 // Add New Tests
		 // Name Test, Expression, Bool is Null, TypeData, value 
		 
	}
	
    public static <T> boolean execTest(String testName, String testExpression, TypeData typeData, Object value, boolean isNull ) {
    	
    	expression = new Expression(testExpression,variables);
		  if ( !expression.compExpr() ) {
			  System.out.println(ANSI_RED + "Test " + testName + " error during compilation." + ANSI_RED);
			  testKo++;
			  return false;
		  } else {
			  if ( expression.execExpr(variables) != 0 ) {
    			  System.out.println(ANSI_RED + "Test " + testName + " error during execution." + ANSI_RED);
    			  testKo++;
			      return false;
			  } else {
				  if ( !expression.getResult().getValue().equals(value) ) {
	    			     System.out.println(ANSI_RED + "Test " + testName + " return value expected : <" + value + "> returned : <" + expression.getResult().getValue() + ">" + ANSI_RED);
	    			     testKo++;
				         return false;
				  }
				  if ( expression.getResult().getTypeData() != typeData ) {
    			     System.out.println(ANSI_RED + "Test " + testName + " type data expected : " + typeData + " returned : " + expression.getResult().getTypeData() + ANSI_RED);
    			     testKo++;
			         return false;
				  }
				  if ( expression.getResult().isNull() != isNull ) {
	    		     System.out.println(ANSI_RED + "Test " + testName + " flag is null expected : " + isNull + " returned : " + expression.getResult().isNull() + ANSI_RED);
	    		     testKo++;
				     return false;
				  }

			  }
		  }
		  System.out.println(ANSI_GREEN + "Test " + testName + " : OK." + ANSI_GREEN);
		  testOk++;
    	
    	  return true;
	}
	
	public static void main(String args[]) throws Exception {
		
		init();
		
		for(int i=0;i<tests.size();i++) {
			execTest(tests.get(i).getTestName(), 
					 tests.get(i).getExpression(), 
					 tests.get(i).getTypeData(), 
					 tests.get(i).getValue(), 
					 tests.get(i).isNull());
		}
		
		System.out.println(ANSI_GREEN + "\n-----------------------------------");
		System.out.println(ANSI_GREEN + "Test  OK   : " +  testOk + ANSI_GREEN); 
		System.out.println(ANSI_RED + "Test  KO   : " +  testKo + ANSI_RED); 
		System.out.println(ANSI_GREEN + "Total Test : " +  tests.size()); 
		System.out.println(ANSI_GREEN +"-----------------------------------\n");
		 
	}
}
