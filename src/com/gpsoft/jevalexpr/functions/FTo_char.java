package com.gpsoft.jevalexpr.functions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

public class FTo_char extends Function{

	public FTo_char() {
		super();
		
		this.name = "to_char";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_to_char; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		if ( step.getOpnd().size() != 1 && step.getOpnd().size() != 2 ) {
			Logger.error("Function to_char work with one or two arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd).getResType() != TypeData.E_boolean &&
		     expBin.getStep().get(idxOpd).getResType() != TypeData.E_double &&
		     expBin.getStep().get(idxOpd).getResType() != TypeData.E_date 
		   ) {
			Logger.error("function to_char first argument must be int, double, boolean or date.");
			return false;
		}
		
		if ( expBin.getStep().get(idxOpd).getResType() == TypeData.E_date && step.getOpnd().size() != 2 ) {
			Logger.error("function to_char if first argument is a date we need a second argument with a format value.");
			return false;
		}
		
		if ( step.getOpnd().size() == 2 ) {
    	   idxOpd2 = step.getOpnd().get(1);
    	   
    	   if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_date ) {
			  Logger.error("function to_char second argument is permitted only if first argument is a date.");
			  return false;
    	   }
    	   
    	   if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_string ) {
    		   Logger.error("function to_char second argument must be string.");
    		   return false;
    	   }
			
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_string);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FTo_char");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		DateTimeFormatter formatter = null;
		String formatDate = null;
		String ris = null;
		
		int idxOpd1;
		int idxOpd2;
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_boolean ) {
			
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
			if ( (Boolean)expBin.getStep().get(idxOpd1).getData().getValue() ) {
     			expBin.getStep().get(idxStep).setData(new DataValue<String>("true"));
			} else {
     			expBin.getStep().get(idxStep).setData(new DataValue<String>("false"));
			}
			expBin.getStep().get(idxStep).setNull(false);
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_int ) {
			int valInt = (Integer)expBin.getStep().get(idxOpd1).getData().getValue();
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(String.valueOf(valInt)));
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ) {
			double valDouble = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(String.valueOf(valDouble)));
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_date ) {
			
			idxOpd2 = step.getOpnd().get(1);
			formatDate = (String)expBin.getStep().get(idxOpd2).getData().getValue();
			
			try {
				formatter = DateTimeFormatter.ofPattern(formatDate);
			} catch( Exception e) {
				Logger.error("to_char error wrong format string '" + formatDate + "'");
				return false;
			}
			
			if ( Utility.isLocalDateTime( expBin.getStep().get(idxOpd1).getData().getValue()) ) {
				LocalDateTime localDateTime = (LocalDateTime)expBin.getStep().get(idxOpd1).getData().getValue();
				ris = localDateTime.format(formatter);
			} else if ( Utility.isLocalDate( expBin.getStep().get(idxOpd1).getData().getValue()) ) {
				LocalDate localDate = (LocalDate)expBin.getStep().get(idxOpd1).getData().getValue();
				ris = localDate.format(formatter);
			} else if ( Utility.isLocalTime( expBin.getStep().get(idxOpd1).getData().getValue()) ) {
				LocalTime localTime = (LocalTime)expBin.getStep().get(idxOpd1).getData().getValue();
				ris = localTime.format(formatter);
			} else {
				Logger.error("Generic Error in to_char");
				return false;
			}
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(ris));
			
		} else {
			return false;
		}
		
		return true;
	}
}
