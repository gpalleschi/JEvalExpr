package com.gpsoft.jevalexpr.functions;
import com.gpsoft.jevalexpr.DataValue;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FVariable extends Function {

	public FVariable(String name) {
		super();
		this.name = name;
		this.typeToken = TypeToken.E_value;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_variable; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}
	
	public boolean check(ExpBin<?> expBin, int idxStep) {
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		if ( expBin.getStep().get(idxStep).getOpnd().get(0) >= 0 ) {
			Logger.debug("In FVariable indice variabile : " + expBin.getStep().get(idxStep).getOpnd().get(0));
			
			int indVar = expBin.getStep().get(idxStep).getOpnd().get(0);
			
		    if ( expBin.getVariables()[indVar].getValue() == null ) {
   			   expBin.getStep().get(idxStep).setNull(true);
		    } else {
   			   expBin.getStep().get(idxStep).setNull(false);
		    }
			
			if ( expBin.getVariables()[indVar].getTypeVariable() == TypeData.E_string ) {
				String value = expBin.getVariables()[indVar].getValue().toString();
				Logger.debug("E_string : " + value);
				expBin.getStep().get(idxStep).setData(new DataValue<String>(value));
			    expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
			} else if ( expBin.getVariables()[indVar].getTypeVariable() == TypeData.E_int ) {
				Integer value = (Integer) expBin.getVariables()[indVar].getValue();
				expBin.getStep().get(idxStep).setData(new DataValue<Integer>(value));
			    expBin.getStep().get(idxStep).setTypeData(TypeData.E_int);
			} else if ( expBin.getVariables()[indVar].getTypeVariable() == TypeData.E_double ) {
				Double value = (Double) expBin.getVariables()[indVar].getValue();
				expBin.getStep().get(idxStep).setData(new DataValue<Double>(value));
			    expBin.getStep().get(idxStep).setTypeData(TypeData.E_double);
			} else if ( expBin.getVariables()[indVar].getTypeVariable() == TypeData.E_boolean ) {
				Boolean value = (Boolean) expBin.getVariables()[indVar].getValue();
				expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(value));
			    expBin.getStep().get(idxStep).setTypeData(TypeData.E_boolean);
			} else {
				return false;
			}
			
		} else {
		   return false;	
		}
		
		Logger.debug("Exit Exec FVariable");
		return true;
	}

}
			