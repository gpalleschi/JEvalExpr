package com.gpsoft.jevalexpr.functions;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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

public class FTrunc extends Function{

	public FTrunc() {
		super();
		
		this.name = "trunc";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_trunc; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		if (step.getOpnd().size() != 1 && step.getOpnd().size() != 2) {
			Logger.error("Function trunc work with one or two arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_double &&
		     expBin.getStep().get(idxOpd).getResType() != TypeData.E_int ) {
			Logger.error("function trunc first argument must be numeric.");
			return false;
		}

		
		if ( step.getOpnd().size() == 2 ) {
			idxOpd2 = step.getOpnd().get(1);
			if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_int ) {
				Logger.error("function trunc second argument must be integer.");
				return false;
			}
		}
		
		if ( step.getOpnd().size() == 1 ) {
    		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
		} else {
			if ( expBin.getStep().get(idxOpd).getResType() == TypeData.E_int ) {
         		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
			} else {
    		    expBin.getStep().get(idxStep).setResType(TypeData.E_double);
			}
		}
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FCeil");	
		DecimalFormat df = null; 
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		int numDec = 0;
		idxOpd1 = step.getOpnd().get(0);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( !Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     !Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) 
		   ) {
			Logger.error("Expression corrupted - First Argument must be Integer or Double.");
			return false;			   	
		}
		
		if ( step.getOpnd().size() == 2 ) {
			idxOpd2 = step.getOpnd().get(1);
			if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
			
			if ( expBin.getStep().get(idxOpd2).isNull() ) {
				expBin.getStep().get(idxStep).setNull(true);
				return true;
			}
			
			if ( !Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
				Logger.error("Expression corrupted - Second Argument must be Integer.");
				return false;			   	
			}
			
			numDec = (Integer)expBin.getStep().get(idxOpd2).getData().getValue();
			
			if ( numDec < 1 || numDec > 10 ) {
				Logger.error("Second Argument must be positive with a value from 0 to 10.");
				return false;			   	
				
			}
			
			df = new DecimalFormat("#." + "##########".substring(0,numDec));
			df.setRoundingMode(RoundingMode.FLOOR);
		} else {
			df = new DecimalFormat("#");
			df.setRoundingMode(RoundingMode.FLOOR);
		}
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double && numDec != 0) {
			
			double value = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_double);
	    	expBin.getStep().get(idxStep).setData(new DataValue<Double>(Double.parseDouble(df.format(value).replace(',','.'))));
			expBin.getStep().get(idxStep).setNull(false);
			
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ) {
			double value = (Double)expBin.getStep().get(idxOpd1).getData().getValue();
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_int);
	    	expBin.getStep().get(idxStep).setData(new DataValue<Integer>(Integer.parseInt(df.format(value))));
			expBin.getStep().get(idxStep).setNull(false);
		} else if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_int ) {
			df = new DecimalFormat("#");
			df.setRoundingMode(RoundingMode.FLOOR);
			int value = (Integer)expBin.getStep().get(idxOpd1).getData().getValue();
			expBin.getStep().get(idxStep).setTypeData(TypeData.E_int);
	    	expBin.getStep().get(idxStep).setData(new DataValue<Integer>(Integer.parseInt(df.format(value))));
			expBin.getStep().get(idxStep).setNull(false);
		}
		
		return true;
	}
}