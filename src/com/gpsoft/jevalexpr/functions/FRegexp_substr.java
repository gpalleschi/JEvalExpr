package com.gpsoft.jevalexpr.functions;

import com.gpsoft.jevalexpr.DataValue;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.Step;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.Utility;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FRegexp_substr extends Function{
	
	// regexp_substr(value, regexp, [startpos, occurrences])

	public FRegexp_substr() {
		super();
		
		this.name = "regexp_substr";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_regexp_substr;
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		int idxOpd3;
		int idxOpd4;
		
		if (step.getOpnd().size() < 2 || step.getOpnd().size() > 4 ) {
			Logger.error("Function regexp_substr work only with two, three or four arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function regexp_substr first argument must be a string.");
			return false;
		}

		idxOpd2 = step.getOpnd().get(1);

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_string ) {
			Logger.error("function regexp_substr second argument must be a string.");
			return false;
		}
		
		if ( step.getOpnd().size() >= 3 ) {
			idxOpd3 = step.getOpnd().get(2);

			if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_int ) {
				Logger.error("function regexp_substr third argument must be an integer.");
				return false;
			}

			if ( step.getOpnd().size() == 4 ) {
				idxOpd4 = step.getOpnd().get(3);
	
				if ( expBin.getStep().get(idxOpd4).getResType() != TypeData.E_int ) {
					Logger.error("function regexp_substr fourth argument must be an integer.");
					return false;
				}
			}
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_string);
		
		return true;
	}
	
	private String regexpSubstr(String input, String pattern, int occurrence, int startPosition) {
	        Pattern compiledPattern = Pattern.compile(pattern);
	        Matcher matcher = compiledPattern.matcher(input);
	        int matchCount = 0;

	        // Imposta la posizione di partenza
	        matcher.region(startPosition, input.length());

	        while (matcher.find()) {
	            matchCount++;
	            if (matchCount == occurrence) {
	                return matcher.group();
	            }
	        }

	        return null; // Se non viene trovato un match corrispondente all'occorrenza specificata
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FRegexp_substr");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		
		int startPos = 0;
		int occurrences = 1;
		String value = null;
		String expr = null;
		
		int idxOpd1;
		int idxOpd2;
		int idxOpd3;
		int idxOpd4;

		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( !Utility.isString(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
		   Logger.error("Value must be a String.");
		   return false;				
		}
		
		value = (String)expBin.getStep().get(idxOpd1).getData().getValue();

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd2).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( !Utility.isString(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			   Logger.error("Value must be a String.");
			   return false;				
		}
			
		expr = (String)expBin.getStep().get(idxOpd2).getData().getValue();
		
		if ( step.getOpnd().size() >= 3 ) {
			idxOpd3 = step.getOpnd().get(2);
			
			if ( !expBin.getStep().get(idxOpd3).getFunction().exec(expBin, idxOpd3) ) return false;
			
			if ( expBin.getStep().get(idxOpd3).isNull() ) {
				expBin.getStep().get(idxStep).setNull(true);
				return true;
			}			
			
			if ( Utility.isInteger(expBin.getStep().get(idxOpd3).getData().getValue()) ) {
				startPos = (Integer)expBin.getStep().get(idxOpd3).getData().getValue();
				
				if ( startPos < 0 ) {
				   Logger.error("Start Position must be positive value.");
				   return false;				
				}
			} else {
				Logger.error("Binary Expression Corrupted.");
				return false;				
			}
			
			if ( step.getOpnd().size() == 4 ) {
				idxOpd4 = step.getOpnd().get(3);
				
				if ( !expBin.getStep().get(idxOpd4).getFunction().exec(expBin, idxOpd4) ) return false;
				
				if ( expBin.getStep().get(idxOpd4).isNull() ) {
					expBin.getStep().get(idxStep).setNull(true);
					return true;
				}			
				
				if ( Utility.isInteger(expBin.getStep().get(idxOpd4).getData().getValue()) ) {
					occurrences = (Integer)expBin.getStep().get(idxOpd4).getData().getValue();
					if ( occurrences < 0 ) {
					   Logger.error("Number Occurences must be positive value.");
					   return false;				
					}
				} else {
					Logger.error("Binary Expression Corrupted.");
					return false;				
				}

			}
		}
		
		String ris = regexpSubstr(value, expr, occurrences, startPos);
		
		if ( ris == null ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		} else {
 		   expBin.getStep().get(idxStep).setData(new DataValue<String>(ris));
		   expBin.getStep().get(idxStep).setNull(false);
		}
		
		return true;
	}
}