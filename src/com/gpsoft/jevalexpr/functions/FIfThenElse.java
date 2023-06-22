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

public class FIfThenElse extends Function{

	public FIfThenElse() {
		super();
		
		this.name = "ifThenElse";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_three;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_if_then; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);

		int idxOpd1;
		int idxOpd2;
		int idxOpd3;
		
		if (step.getOpnd().size() != 3 ) {
			Logger.error("Function " + this.name + " (+) work with three arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);

		/* TODO: CHeck Arguments
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_boolean ||
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int
		   ) {
			Logger.error("function " + this.name + " (+) first operand may be boolean or integer.");
			return false;
		}
		*/
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		Logger.debug("In Exec FLe");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		int idxOpd2;
		int idxOpd3;
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		idxOpd3 = step.getOpnd().get(2);

		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		boolean ris = false;
		
		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			
		   if ( (Integer)expBin.getStep().get(idxOpd1).getData().getValue() != 0 ) {
			   ris = true;
		   } else {
			   ris = false;
		   }
		}
		
		if ( Utility.isBoolean(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
			
		   if ( (Boolean)expBin.getStep().get(idxOpd1).getData().getValue() ) {
			   ris = true;
		   } else {
			   ris = false;
		   }
		}
		
		int opdToExecute;
		
		if ( ris ) {
			opdToExecute = idxOpd2;	
		} else {
			opdToExecute = idxOpd3;
		}

		// Operand 2
		if ( !expBin.getStep().get(opdToExecute).getFunction().exec(expBin, opdToExecute) ) return false;
		
		if ( Utility.isString(expBin.getStep().get(opdToExecute).getData().getValue()) ) {
			
			String valor = (String)expBin.getStep().get(opdToExecute).getData().getValue();
			
			expBin.getStep().get(idxStep).setData(new DataValue<String>(valor));
	     	expBin.getStep().get(idxStep).setResType(TypeData.E_string);
			expBin.getStep().get(idxStep).setNull(false);			
		} else if ( Utility.isInteger(expBin.getStep().get(opdToExecute).getData().getValue()) ) {

			Integer valor = (Integer)expBin.getStep().get(opdToExecute).getData().getValue();
			
			expBin.getStep().get(idxStep).setData(new DataValue<Integer>(valor));
	     	expBin.getStep().get(idxStep).setResType(TypeData.E_int);
			expBin.getStep().get(idxStep).setNull(false);			
		} else if ( Utility.isBoolean(expBin.getStep().get(opdToExecute).getData().getValue()) ) {

			Boolean valor = (Boolean)expBin.getStep().get(opdToExecute).getData().getValue();
			
			expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(valor));
	     	expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
			expBin.getStep().get(idxStep).setNull(false);			
		} else if ( Utility.isDouble(expBin.getStep().get(opdToExecute).getData().getValue()) ) {

			Double valor = (Double)expBin.getStep().get(opdToExecute).getData().getValue();
			
			expBin.getStep().get(idxStep).setData(new DataValue<Double>(valor));
	     	expBin.getStep().get(idxStep).setResType(TypeData.E_double);
			expBin.getStep().get(idxStep).setNull(false);			
		} else {
			Logger.error("Binary expression corrupted.");
			return false;
		}
		
		return true;
	}

}

