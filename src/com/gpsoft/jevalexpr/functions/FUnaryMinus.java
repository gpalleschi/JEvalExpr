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

public class FUnaryMinus extends Function {
	public FUnaryMinus() {
		super();
		
		this.name = "-";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_unary_minus; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function unary_minus (+) work with one argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_double ) {
			Logger.error("function unary_minus (+) work only with number argument.");
			return false;
		}

		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ) {
			expBin.getStep().get(idxStep).setResType(TypeData.E_double);	
		} else {
			expBin.getStep().get(idxStep).setResType(TypeData.E_int);	
		}
		
		return true;
	}
	
	public boolean exec(ExpBin<?> expBin, int idxStep) {
		Logger.debug("In Exec FUnaryMinus");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;

		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			
    		Double valueOp1 = (Double) expBin.getStep().get(idxOpd1).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Double>(valueOp1*-1));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		    expBin.getStep().get(idxStep).setNull(false);

		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			
    		Integer valueOp1 = (Integer) expBin.getStep().get(idxOpd1).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Integer>(valueOp1*-1));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
		    expBin.getStep().get(idxStep).setNull(false);
		}
		
		return true;
	}

}
