package com.gpsoft.jevalexpr.functions;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

	public FSub() {
		super();
		
		this.name = "sub";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_sub; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function sub (-) work with two argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( expBin.getStep().get(idxOpd1).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_double &&
		     expBin.getStep().get(idxOpd1).getResType() != TypeData.E_date ) {
			Logger.error("function sub (-) work only with number or date arguments.");
			return false;
		}

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_int &&
		     expBin.getStep().get(idxOpd2).getResType() != TypeData.E_double && 
		     expBin.getStep().get(idxOpd2).getResType() != TypeData.E_date 
		     ) {
			Logger.error("function sub (-) work only with number or date arguments.");
			return false;
		}
		
		if ( (expBin.getStep().get(idxOpd1).getResType() == TypeData.E_date && 
              expBin.getStep().get(idxOpd2).getResType() != TypeData.E_date ) ||
             (expBin.getStep().get(idxOpd2).getResType() == TypeData.E_date && 
              expBin.getStep().get(idxOpd1).getResType() != TypeData.E_date ) ) {
			Logger.error("function sub (-) permits difference only between two dates.");
			return false;
		}
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_double ||
			 expBin.getStep().get(idxOpd2).getResType() == TypeData.E_double ||
			 expBin.getStep().get(idxOpd1).getResType() == TypeData.E_date ) {
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
		
		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( expBin.getStep().get(idxOpd2).getData().isNull() ) {
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

		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isDouble(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
    		Integer valueOp1 = (Integer) expBin.getStep().get(idxOpd1).getData().getValue();
		    Double valueOp2 = (Double) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Double>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		    expBin.getStep().get(idxStep).setNull(false);
		}
		
		if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) ) {

    		Double valueOp1 = (Double) expBin.getStep().get(idxOpd1).getData().getValue();
		    Integer valueOp2 = (Integer) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Double>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_double);
		    expBin.getStep().get(idxStep).setNull(false);
		}

		if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isInteger(expBin.getStep().get(idxOpd2).getData().getValue()) ) {

    		Integer valueOp1 = (Integer) expBin.getStep().get(idxOpd1).getData().getValue();
		    Integer valueOp2 = (Integer) expBin.getStep().get(idxOpd2).getData().getValue();

		    expBin.getStep().get(idxStep).setData(new DataValue<Integer>(execSub(valueOp1, valueOp2)));
     		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
		    expBin.getStep().get(idxStep).setNull(false);
		}
		
		if ( Utility.isDate(expBin.getStep().get(idxOpd1).getData().getValue()) &&
		     Utility.isDate(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
			
			 LocalDateTime localDateTime1 = null;
			 LocalDateTime localDateTime2 = null;
			 
			 LocalTime localTime1 = null;
			 LocalTime localTime2 = null;
			 
			 if ( Utility.isLocalDate(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
				 localDateTime1 = ((LocalDate)expBin.getStep().get(idxOpd1).getData().getValue()).atStartOfDay();
				 localTime1 = ((LocalDate)expBin.getStep().get(idxOpd1).getData().getValue()).atStartOfDay().toLocalTime();
			 } else if ( Utility.isLocalTime(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
				 localTime1 = (LocalTime)expBin.getStep().get(idxOpd1).getData().getValue();
			 } else {
				 localDateTime1 = (LocalDateTime)expBin.getStep().get(idxOpd1).getData().getValue();
				 localTime1 = ((LocalDateTime)expBin.getStep().get(idxOpd1).getData().getValue()).toLocalTime();
			 }

			 if ( Utility.isLocalDate(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
				 localDateTime2 = ((LocalDate)expBin.getStep().get(idxOpd2).getData().getValue()).atStartOfDay();
				 localTime2 = ((LocalDate)expBin.getStep().get(idxOpd2).getData().getValue()).atStartOfDay().toLocalTime();
			 } else if ( Utility.isLocalTime(expBin.getStep().get(idxOpd2).getData().getValue()) ) {
				 localTime2 = (LocalTime)expBin.getStep().get(idxOpd2).getData().getValue();
			 } else {
				 localDateTime2 = (LocalDateTime)expBin.getStep().get(idxOpd2).getData().getValue();
				 localTime2 = ((LocalDateTime)expBin.getStep().get(idxOpd1).getData().getValue()).toLocalTime();
			 }
			 
			 if ( localDateTime1 != null && localDateTime2 != null ) {
				double daysBetween = Duration.between(localDateTime1, localDateTime2).toDays();	
			    expBin.getStep().get(idxStep).setData(new DataValue<Double>(daysBetween));
			   	expBin.getStep().get(idxStep).setResType(TypeData.E_double);
			    expBin.getStep().get(idxStep).setNull(false);
			 } else if ( localTime1 != null && localTime2 != null ) {
				   	   double secondsBetween = Duration.between(localTime1, localTime2).getSeconds();	
			           expBin.getStep().get(idxStep).setData(new DataValue<Double>(secondsBetween));
		    	       expBin.getStep().get(idxStep).setResType(TypeData.E_double);
			           expBin.getStep().get(idxStep).setNull(false);				 
			 } else {
					Logger.error("function sub (-) different types impossible substract date from time or vice versa.");
					return false;
			 }
		}
		
		return true;
	}
}