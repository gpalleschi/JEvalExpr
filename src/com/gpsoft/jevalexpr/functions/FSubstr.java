package com.gpsoft.jevalexpr.functions;

import com.gpsoft.jevalexpr.DataValue;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.Step;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FSubstr extends Function{

	public FSubstr() {
		super();
		
		this.name = "substr";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_substr; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		int idxOpd3;
		
		if (step.getOpnd().size() < 2 || step.getOpnd().size() > 3 ) {
			Logger.error("Function substr work with two or three arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function substr first argument must be a string.");
			return false;
		}

		idxOpd2 = step.getOpnd().get(1);

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_int ) {
			Logger.error("function substr second argument must be an integer.");
			return false;
		}
		
		if ( step.getOpnd().size() == 3 ) {
			idxOpd3 = step.getOpnd().get(2);
			if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_int ) {
				Logger.error("function substr third argument must be a integer.");
				return false;
			}			
		}

		expBin.getStep().get(idxStep).setResType(TypeData.E_string);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FSubstr");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		String ris = null;
		Integer length = 0;
		int idxOpd1;
		int idxOpd2;
		int idxOpd3;
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
        	expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd2).getData().isNull() ) {
        	expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_string &&
		     expBin.getStep().get(idxOpd2).getResType() == TypeData.E_int
		   ) {
			
			String value = (String)expBin.getStep().get(idxOpd1).getData().getValue();
			Integer start = (Integer)expBin.getStep().get(idxOpd2).getData().getValue();
			
			if ( start < 1 ) {
				Logger.error("function substr second argument can't be minus of 1.");
				return false;
			}
			
			if ( step.getOpnd().size() == 3 ) {
				idxOpd3 = step.getOpnd().get(2);
				
				if ( !expBin.getStep().get(idxOpd3).getFunction().exec(expBin, idxOpd3) ) return false;
				
				if ( expBin.getStep().get(idxOpd3).getData().isNull() ) {
         		    expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
					expBin.getStep().get(idxStep).setNull(true);
					return true;
				}				
				
				if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_int ) {
					Logger.error("function substr third argument must be a integer.");
					return false;					
				}
				
				length = (Integer)expBin.getStep().get(idxOpd3).getData().getValue();
				
				if ( length < 1 ) {
					Logger.error("function substr second argument can't be less than 1.");
					return false;
				}
				
				/*
 				if ( length < start ) {
					Logger.error("function substr second argument can't be less than first argument.");
					return false;
				}
				*/
				
				if ( length+start-1 > value.length() ) {
				  ris = value.substring(start-1);
				} else {
				  ris = value.substring(start-1,length+start-1);
				}
				
				
			} else {
				expBin.getStep().get(idxStep).setTypeData(TypeData.E_string);
				ris = value.substring(start-1);
			}
			
			if ( ris != null && ris.length() != 0 ) {
    		   expBin.getStep().get(idxStep).setData(new DataValue<String>(ris));
			   expBin.getStep().get(idxStep).setNull(false);
			} else {
    		   expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			   expBin.getStep().get(idxStep).setNull(true);
			}
			
		} else {
			return false;
		}
		
		return true;
	}
}