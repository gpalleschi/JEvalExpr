package com.gpsoft.jevalexpr.functions;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FConstant extends Function {

	public FConstant(String name) {
		super();
		this.name = name;
		this.typeToken = TypeToken.E_value;
		this.operatorSyntaxType = OperatorSyntaxType.E_undef;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_constant; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FConstant");	
		return true;
	}

}
