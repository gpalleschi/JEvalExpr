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

public class FSub extends Function{

	public FSub(String name) {
		super();
		
		this.name = name;
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_sum; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function sub (+) work with two argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_double ) {
			Logger.error("function sub (+) work only with number arguments.");
			return false;
		}

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd2).getResType() != TypeData.E_double ) {
			Logger.error("function sub (+) work only with number arguments.");
			return false;
		}
			
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ||
			 expBin.getStep().get(idxOpd2).getResType() == TypeData.E_double ) {
			expBin.getStep().get(idxStep).setResType(TypeData.E_double);	
		} else {
			expBin.getStep().get(idxStep).setResType(TypeData.E_int);	
		}
		
		return true;
	}
	
	private double execSub(Double op1, Double op2) {
		return op1 - op2;
	}
	
	private double execSub(Double op1, Integer op2) {
		return op1 - op2;
	}
	
	private double execSub(Integer op1, Double op2) {
		return op1 - op2;
	}
	
	private int execSub(Integer op1, Integer op2) {
		return op1 - op2;
	}
	
	public boolean exec(ExpBin<?> expBin, int idxStep) {
		Logger.debug("In Exec FSub");	
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		int idxOpd2;
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( expBin.getStep().get(idxOpd2).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isDouble(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
    		Double valueOp1 = (Double) expBin.getStep().get(idxOpd1).getData().getValue();
		    Double valueOp2 = (Double) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Double>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		    expBin.getStep().get(idxStep).setNull(false);

		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData()) &&
		     Utility.isDouble(expBin.getStep().get(idxOpd2).getData()) ) {
			
    		Integer valueOp1 = (Integer) expBin.getStep().get(idxOpd1).getData().getValue();
		    Double valueOp2 = (Double) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Double>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		    expBin.getStep().get(idxStep).setNull(false);
		}
		
		if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData()) &&
		     Utility.isInteger(expBin.getStep().get(idxOpd2).getData()) ) {

    		Double valueOp1 = (Double) expBin.getStep().get(idxOpd1).getData().getValue();
		    Integer valueOp2 = (Integer) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Double>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		    expBin.getStep().get(idxStep).setNull(false);
		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData()) &&
		     Utility.isInteger(expBin.getStep().get(idxOpd2).getData()) ) {

    		Integer valueOp1 = (Integer) expBin.getStep().get(idxOpd1).getData().getValue();
		    Integer valueOp2 = (Integer) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Integer>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
		    expBin.getStep().get(idxStep).setNull(false);
		}
		
//		Logger.debug("Type 1 : " + expBin.getStep().get(idxOpd1).getResType());
//		Logger.debug("Type 2 : " + expBin.getStep().get(idxOpd2).getResType());
//		
//		Logger.debug("Class name 1 : " + expBin.getStep().get(idxOpd1).getData().getClass().getName());
//		Logger.debug("Class name 2 : " + expBin.getStep().get(idxOpd2).getData().getClass().getName());
//
//		Logger.debug("Value 1 : " + expBin.getStep().get(idxOpd1).getData());
//		Logger.debug("Value 2 : " + expBin.getStep().get(idxOpd2).getData());
		
		return true;
	}
}