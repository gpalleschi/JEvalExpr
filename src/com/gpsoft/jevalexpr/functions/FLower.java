package com.gpsoft.jevalexpr.functions;

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

public class FLower extends Function{

	public FLower() {
		super();
		
		this.name = "lower";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_lower; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function " + name + " work with one argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function " + name + " work only with a string argument.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_string);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FLower");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_string ) {
			
			String value = (String)expBin.getStep().get(idxOpd1).getData().getValue();
			
			String ris = value.toLowerCase();
			
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
			if ( ris != null ) {
    		   expBin.getStep().get(idxStep).setData(new DataValue<String>(ris));
			   expBin.getStep().get(idxStep).setNull(false);
			} else {
    		   expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			   expBin.getStep().get(idxStep).setNull(true);
			}
			
		} else {
			return false;
		}
		
		return true;
	}
}