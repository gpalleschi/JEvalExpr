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

public class FConc extends Function {
	
	public FConc() {
		super();
		this.name = "conc";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_two;
		this.operatorPriority = OperatorPriority.E_lev1;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_conc; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function " + this.name + " (+) work with two argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_string || expBin.getStep().get(idxOpd1).getResType() != expBin.getStep().get(idxOpd2).getResType() ) {
			Logger.error("function " + this.name + " (+) work only with strings.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_string);

		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FEq");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		int idxOpd2;
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);

		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( Utility.isString(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isString(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
			String val1 = (String)expBin.getStep().get(idxOpd1).getData().getValue(); 
			String val2 = (String)expBin.getStep().get(idxOpd2).getData().getValue(); 
			
			String ris = val1 + val2;
			
			if ( ris != null && ris.length() != 0 ) {
				expBin.getStep().get(idxStep).setData(new DataValue<String>(val1 + val2));
				expBin.getStep().get(idxStep).setNull(false);
			} else {
				expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
				expBin.getStep().get(idxStep).setNull(true);
			}
			
		} else {
			Logger.error("Binary Expression corrupted");
			return false;
		}
		
		return true;
	}


}