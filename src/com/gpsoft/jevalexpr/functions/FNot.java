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

public class FNot extends Function{

	public FNot() {
		super();
		
		this.name = "not";
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
		Step<?> step = expBin.getStep().get(idxStep);
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function " + this.name + " (+) work with one argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		Logger.debug("In Exec FLe");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		
		idxOpd1 = step.getOpnd().get(0);

		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_boolean 
		   ) {
				Logger.error("function " + this.name + " (+) work only with integer or boolean argument.");
				return false;
		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			
			int ris;
			
			if ( (Integer)expBin.getStep().get(idxOpd1).getData().getValue() != 0 ) {
				ris = 0;
			} else {
				ris = 1;
			}
			expBin.getStep().get(idxStep).setData(new DataValue<Integer>(ris));
	     	expBin.getStep().get(idxStep).setResType(TypeData.E_int);
			expBin.getStep().get(idxStep).setNull(false);
		} else {
			boolean ris;
			if ( (Boolean)expBin.getStep().get(idxOpd1).getData().getValue() ) {
				ris = false;
			} else {
				ris = true;
			}
			expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(ris));
	     	expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
			expBin.getStep().get(idxStep).setNull(false);
		}
		
		return true;
	}

}
