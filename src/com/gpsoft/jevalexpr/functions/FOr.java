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

public class FOr extends Function {
	
	public FOr() {
		super();
		this.name = "or";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_two;
		this.operatorPriority = OperatorPriority.E_lev4;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_or; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function or (+) work with two argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		Logger.debug("idxOpd1 : " + idxOpd1);
		Logger.debug("idxOpd2 : " + idxOpd2);

		Logger.debug("ResType idxOpd1 : " + expBin.getStep().get(idxOpd1).getResType());
		Logger.debug("ResType idxOpd2 : " + expBin.getStep().get(idxOpd2).getResType());
		
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_boolean ) {
			Logger.error("function or (+) work only with number or boolean arguments.");
			return false;
		}

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd2).getResType() != TypeData.E_boolean ) {
			Logger.error("function or (+) work only with number or boolean arguments.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FOr");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		int idxOpd2;
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);

		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( expBin.getStep().get(idxOpd2).getData().isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		Boolean valueOp1;
		Boolean valueOp2;
		
		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			if ( (Integer)expBin.getStep().get(idxOpd1).getData().getValue() == 0 ) {
				valueOp1 = false;
			} else {
				valueOp1 = true;
			}
		} else {
        	valueOp1 = (Boolean) expBin.getStep().get(idxOpd1).getData().getValue();
		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			if ( (Integer)expBin.getStep().get(idxOpd2).getData().getValue() == 0 ) {
				valueOp2 = true;
			} else {
				valueOp2 = false;
			}
		} else {
        	valueOp2 = (Boolean) expBin.getStep().get(idxOpd2).getData().getValue();
		}
		
		if ( valueOp1 || valueOp2 ) {
		    expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(true));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		    expBin.getStep().get(idxStep).setNull(false);
		} else {
		    expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(false));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		    expBin.getStep().get(idxStep).setNull(false);
		}
		
		return true;
	}

}