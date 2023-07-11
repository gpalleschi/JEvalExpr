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

public class FAcos extends Function{

	public FAcos() {
		super();
		
		this.name = "acos";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_acos; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function acos work with one argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_double &&
		     expBin.getStep().get(idxOpd).getResType() != TypeData.E_int ) {
			Logger.error("function acos work only with a numeric argument.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FAcos");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		double value = 0.0;
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ) {
			value = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_int ) {
			value = (Integer)expBin.getStep().get(idxOpd1).getData().getValue();
		} else {
			Logger.error("Expression corrupted");
			return false;
		}
		expBin.getStep().get(idxStep).setTypeData(TypeData.E_int);
    	expBin.getStep().get(idxStep).setData(new DataValue<Double>(Math.acos(value)));
		expBin.getStep().get(idxStep).setNull(false);
		
		return true;
	}
}