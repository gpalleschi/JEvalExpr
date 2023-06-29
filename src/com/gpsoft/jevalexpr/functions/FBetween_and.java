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

public class FBetween_and extends Function {
	
	//TODO: Do not work
	
	public FBetween_and() {
		super();
		this.name = "between";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_three;
		this.operatorPriority = OperatorPriority.E_lev1;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_between_and; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		int idxOpd3;
		
		Logger.debug("In check in : " + step.getOpnd().size());
		if (step.getOpnd().size() != 3 ) {
			Logger.error("Function " + this.name + " (+) work with three arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		idxOpd3 = step.getOpnd().get(2);
		
		idxOpd1 = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_double ) {
			Logger.error("function " + this.name + " (+) work only with number arguments.");
			return false;
		}

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_int &&
			 expBin.getStep().get(idxOpd2).getResType() != TypeData.E_double ) {
			 Logger.error("function " + this.name + " (+) work only with number arguments.");
			 return false;
		}
		if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_int &&
			 expBin.getStep().get(idxOpd3).getResType() != TypeData.E_double ) {
			 Logger.error("function " + this.name + " (+) work only with number arguments.");
			 return false;
		}
		
     	expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FBetween_and");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1 = step.getOpnd().get(0);
		int idxOpd2 = step.getOpnd().get(1);
		int idxOpd3 = step.getOpnd().get(2);
		
		double value = 0.0;
		double from = 0.0;
		double to = 0.0;
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd3) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( expBin.getStep().get(idxOpd2).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( expBin.getStep().get(idxOpd3).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			value = (Integer)expBin.getStep().get(idxOpd1).getData().getValue();
		} else if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			value = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
		} else {
			 Logger.error("Binary Expression Corrupted");
			 return false;			
		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			from = (Integer)expBin.getStep().get(idxOpd2).getData().getValue();
		} else if ( Utility.isDouble(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			from = (Double)expBin.getStep().get(idxOpd2).getData().getValue();
		} else {
			 Logger.error("Binary Expression Corrupted");
			 return false;			
		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd3).getData().getValue()) ) {
			to = (Integer)expBin.getStep().get(idxOpd3).getData().getValue();
		} else if ( Utility.isDouble(expBin.getStep().get(idxOpd3).getData().getValue()) ) {
			to = (Double)expBin.getStep().get(idxOpd3).getData().getValue();
		} else {
			 Logger.error("Binary Expression Corrupted");
			 return false;			
		}
		
		boolean ris = false;
		
		Logger.debug("value : " + value + " between " + from + " to " + to);
		
		if ( value >= from && value <= to ) ris = true;

		expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(ris));
     	expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		expBin.getStep().get(idxStep).setNull(false);
		
		return true;
	}


}

