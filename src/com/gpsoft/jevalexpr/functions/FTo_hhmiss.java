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

public class FTo_hhmiss extends Function {

	public FTo_hhmiss() {
	
		super();
		
		this.name = "to_hhmiss";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_to_hhmiss; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		DateTimeFormatter formatter = null;
		int idxOpd;
		int idxOpd2;
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function to_hhmiss work with one argument instead of " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_double &&
		     expBin.getStep().get(idxOpd).getResType() != TypeData.E_int ) {
			Logger.error("function to_date first argument is only a double or integer.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_string);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FTo_hhmiss");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		
		double seconds = 0;
		
		int hours = 0;
		int mins = 0;
		int secs = 0;
		
		int idxOpd1;
		
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ) {
     	   seconds = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_int ) {
		   seconds = (Integer)expBin.getStep().get(idxOpd1).getData().getValue();
		} else {
			Logger.error("Expression corrupted");
			return false;
		}	
		hours = Double.valueOf(seconds/3600).intValue();
		mins = Double.valueOf((seconds % 3600) / 60).intValue();
		secs = Double.valueOf((seconds % 60)).intValue();
		
		String ris = String.format("%d:%d:%d", hours, mins, secs);
		
		expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
		expBin.getStep().get(idxStep).setData(new DataValue<String>(ris));
			
		return true;
	}
}