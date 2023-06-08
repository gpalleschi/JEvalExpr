package com.gpsoft.jevalexpr.functions;

import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;

public class FNot extends Function{

	public FNot(String name) {
		super();
		
		this.name = name;
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_one;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_not; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		return true;
	}

}
