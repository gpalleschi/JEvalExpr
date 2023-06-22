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
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FIs_date extends Function {

	public FIs_date() {
	
		super();
		
		this.name = "is_date";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_is_date; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		
		DateTimeFormatter formatter = null;

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		if (step.getOpnd().size() != 1 && step.getOpnd().size() != 2) {
			Logger.error("Function is_date work with one or two arguments instead of " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function is_date first argument is only a string.");
			return false;
		}
		
		if ( step.getOpnd().size() == 2 ) {
     		idxOpd2 = step.getOpnd().get(1);
     		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_string ) {
     			Logger.error("function is_date second argument is only a string.");
     			return false;
     		}
     		
     		String formatDate = (String)expBin.getStep().get(idxOpd2).getData().getValue();
    		try {
    			formatter = DateTimeFormatter.ofPattern(formatDate);
    		} catch( Exception e) {
    			Logger.error("to_date error wrong format string '" + formatDate + "'");
    			return false;
    		}     	
			
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FTo_date");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		int idxOpd2;
		String formatDate = "yyyyMMdd";
		DateTimeFormatter formatter = null;
		
		
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( step.getOpnd().size() > 1 ) {
			idxOpd2 = step.getOpnd().get(1);
			
			if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
			if ( expBin.getStep().get(idxOpd2).getResType() == TypeData.E_string ) {
				formatDate = (String)expBin.getStep().get(idxOpd2).getData().getValue();		
			}
		}
		try {
			formatter = DateTimeFormatter.ofPattern(formatDate);
		} catch( Exception e) {
			Logger.error("is_date error wrong format string '" + formatDate + "'");
			return false;
		}
		
		try {
			LocalDateTime dateTime = LocalDateTime.parse((String)expBin.getStep().get(idxOpd1).getData().getValue(), formatter);
			
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_boolean);
			expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(true));
		} catch( Exception e) {
			try {
				LocalDate date = LocalDate.parse((String)expBin.getStep().get(idxOpd1).getData().getValue(), formatter);
				expBin.getStep().get(idxStep).setTypeData(TypeData.E_boolean);
				expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(true));
			} catch( Exception er) {
				try {
					LocalTime time = LocalTime.parse((String)expBin.getStep().get(idxOpd1).getData().getValue(), formatter);
					expBin.getStep().get(idxStep).setTypeData(TypeData.E_boolean);
					expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(true));
				} catch( Exception err) {
        			expBin.getStep().get(idxStep).setTypeData(TypeData.E_boolean);
        			expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(false));
				}
			}
			
		}
			
		return true;
	}
}