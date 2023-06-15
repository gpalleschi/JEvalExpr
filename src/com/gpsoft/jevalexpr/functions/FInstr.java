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

public class FInstr extends Function{

	public FInstr(String name) {
		super();
		
		this.name = name;
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_instr; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		int idxOpd3;
		int idxOpd4;
		
		if (step.getOpnd().size() < 2 || step.getOpnd().size() > 4 ) {
			Logger.error("Function instr work with two or three or four arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function instr first argument must be a string.");
			return false;
		}

		idxOpd2 = step.getOpnd().get(1);

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_string ) {
			Logger.error("function instr second argument must be a string.");
			return false;
		}
		
		if ( step.getOpnd().size() == 3 ) {
			idxOpd3 = step.getOpnd().get(2);
			if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_int ) {
				Logger.error("function instr third argument must be a integer.");
				return false;
			}			
		}

		if ( step.getOpnd().size() == 4 ) {
			idxOpd4 = step.getOpnd().get(3);
			if ( expBin.getStep().get(idxOpd4).getResType() != TypeData.E_int ) {
				Logger.error("function instr fourth argument must be a integer.");
				return false;
			}			
		}			

		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FInstr");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		int startPosition = 1;
		int numberOccurences = 1;
		
		int idxOpd1;
		int idxOpd2;
		int idxOpd3;
		int idxOpd4;
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd2).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_string &&
		     expBin.getStep().get(idxOpd2).getResType() == TypeData.E_string
		   ) {
			
			String value = (String)expBin.getStep().get(idxOpd1).getData().getValue();
			String subValue = (String)expBin.getStep().get(idxOpd2).getData().getValue();
			
			if ( step.getOpnd().size() == 3 ) {
				idxOpd3 = step.getOpnd().get(2);
				
				if ( !expBin.getStep().get(idxOpd3).getFunction().exec(expBin, idxOpd3) ) return false;
				
				if ( expBin.getStep().get(idxOpd3).isNull() ) {
					expBin.getStep().get(idxStep).setNull(true);
					return true;
				}				
				
				if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_int ) {
					Logger.error("function instr third argument must be a integer.");
					return false;					
				}
				
				startPosition = (Integer)expBin.getStep().get(idxOpd3).getData().getValue();
				
				if ( startPosition < 0 ) {
					Logger.error("function instr third argument can't be negative.");
					return false;
				}
				
			} 
			
			if ( step.getOpnd().size() == 4 ) {
				idxOpd4 = step.getOpnd().get(3);
				
				if ( !expBin.getStep().get(idxOpd4).getFunction().exec(expBin, idxOpd4) ) return false;
				
				if ( expBin.getStep().get(idxOpd4).isNull() ) {
					expBin.getStep().get(idxStep).setNull(true);
					return true;
				}				
				
				if ( expBin.getStep().get(idxOpd4).getResType() != TypeData.E_int ) {
					Logger.error("function instr fourth argument must be a integer.");
					return false;					
				}
				
				numberOccurences = (Integer)expBin.getStep().get(idxOpd4).getData().getValue();
				
				if ( numberOccurences <= 0 ) {
					Logger.error("function instr third argument can't be iqual to 0 or negative.");
					return false;
				}
				
			} 
				
    		expBin.getStep().get(idxStep).setData(new DataValue<Integer>(Utility.instr(value, subValue, startPosition, numberOccurences)));
			expBin.getStep().get(idxStep).setNull(false);
			
		} else {
			return false;
		}
		
		return true;
	}
}