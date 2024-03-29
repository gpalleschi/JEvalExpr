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

public class FEq extends Function {
	
	public FEq() {
		super();
		this.name = "=";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_two;
		this.operatorPriority = OperatorPriority.E_lev2;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_eq; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function " + this.name + " (+) work with two argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( expBin.getStep().get(idxOpd1).getResType() != expBin.getStep().get(idxOpd2).getResType() ) {
			if ( !((expBin.getStep().get(idxOpd1).getResType() == TypeData.E_int && 
			     expBin.getStep().get(idxOpd2).getResType() == TypeData.E_double ) ||
			     (expBin.getStep().get(idxOpd2).getResType() == TypeData.E_int && 
			     expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double )) ) {

			Logger.error("function " + this.name + " (+) work only with same types of arguments.");
			return false;
				
			}
				
		}
		
    	expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);

		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Step<?> step = expBin.getStep().get(idxStep);

		Logger.error("Entro in Exec E_eq");
		
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
		
		Boolean ris = false;
		
		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
			if ( (Integer)expBin.getStep().get(idxOpd1).getData().getValue() == 
			     (Integer)expBin.getStep().get(idxOpd2).getData().getValue() ) {
				ris = true;
			} else {
				ris = false;
			}
		}
		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isDouble(expBin.getStep().get(idxOpd2).getData().getValue()) ) {

			Integer value1 = (Integer)expBin.getStep().get(idxOpd1).getData().getValue();
			Double value2 = (Double)expBin.getStep().get(idxOpd2).getData().getValue();
			
		    double valuet = value1;	

			ris = value2.equals(valuet);
		}
		if ( Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) &&
		     Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) ) {

			Integer value1 = (Integer)expBin.getStep().get(idxOpd2).getData().getValue();
			Double value2 = (Double)expBin.getStep().get(idxOpd1).getData().getValue();

		    double valuet = value1;	

			ris = value2.equals(valuet);
		}

		if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isDouble(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
			Double value1 = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
			Double value2 = (Double)expBin.getStep().get(idxOpd2).getData().getValue();
			
			ris = value1.equals(value2);
		}
		if ( Utility.isString(expBin.getStep().get(idxOpd1).getData().getValue()) &&
			 Utility.isString(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
			 String value1 = (String)(expBin.getStep().get(idxOpd1).getData().getValue());
			 String value2 = (String)(expBin.getStep().get(idxOpd2).getData().getValue());
			 
			 if ( value1.compareTo(value2) == 0 ) {
				ris = true;
			 } else {
				ris = false;
			 }
	    }
					
		if ( Utility.isBoolean(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isBoolean(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
				
			if ( (Boolean)expBin.getStep().get(idxOpd1).getData().getValue() == 
			     (Boolean)expBin.getStep().get(idxOpd2).getData().getValue() ) {
				ris = true;
			} else {
				ris = false;
			}
		}

		expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(ris));
     	expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		expBin.getStep().get(idxStep).setNull(false);
		
		return true;
	}


}
