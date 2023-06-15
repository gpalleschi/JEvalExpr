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

public class FIsNull extends Function{

	public FIsNull(String name) {
		super();
		
		this.name = name;
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_is_null; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function is_null work with two arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FIs_Null");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			
				expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(true));
				expBin.getStep().get(idxStep).setNull(true);
		} else {
				expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(false));
				expBin.getStep().get(idxStep).setNull(false);
		}
	
		return true;
	}
}