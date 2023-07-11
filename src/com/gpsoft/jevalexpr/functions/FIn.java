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

public class FIn extends Function {
	
	//TODO: Do not work
	
	public FIn() {
		super();
		this.name = "in";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_undef;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_in; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		
		Logger.debug("In check in : " + step.getOpnd().size());
		
		idxOpd1 = step.getOpnd().get(0);
		
		for ( int idx=1;idx<step.getOpnd().size();idx++ ) {
			int idxOpdn = step.getOpnd().get(idx);
			Logger.debug("Type 1 : " + expBin.getStep().get(idxOpd1).getResType() + " Type " + idx + " " + expBin.getStep().get(idxOpdn).getResType());
			if ( expBin.getStep().get(idxOpd1).getResType() != expBin.getStep().get(idxOpdn).getResType() ) {
				Logger.error("function " + this.name + " (+) work only with same types of arguments.");
				return false;
			}
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);

		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FEq");	
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1 = step.getOpnd().get(0);
		int idxOpdn = -1;
		
		for ( int idx=0;idx<step.getOpnd().size();idx++ ) {
			idxOpdn = step.getOpnd().get(idx);
			if ( !expBin.getStep().get(idxOpdn).getFunction().exec(expBin, idxOpdn) ) return false;
		}
		
		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		boolean ris = false;

		if ( Utility.isString(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
	     	String value1 = (String)(expBin.getStep().get(idxOpd1).getData().getValue());
	     	int idx=1;
			
			for ( idx=1;idx<step.getOpnd().size();idx++ ) {
				
				idxOpdn = step.getOpnd().get(idx);
				
				 if ( !Utility.isString(expBin.getStep().get(idxOpdn).getData().getValue()) ) {
					Logger.error("Binary Expression corrupted");
					return false;
				 }
				
				 String value2 = (String)(expBin.getStep().get(idxOpdn).getData().getValue());
				 
				 if ( value1.compareTo(value2) == 0 ) break;
			}
			
			if ( idx != step.getOpnd().size() ) {
			  ris = true;	
			} else {
			  ris = false;	
			}
			
		} else if ( Utility.isInteger(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
	     	int value1 = (Integer)(expBin.getStep().get(idxOpd1).getData().getValue());
	     	int idx=1;
			
			for ( idx=1;idx<step.getOpnd().size();idx++ ) {
				idxOpdn = step.getOpnd().get(idx);
				
				 if ( !Utility.isInteger(expBin.getStep().get(idxOpdn).getData().getValue()) ) {
					Logger.error("Binary Expression corrupted");
					return false;
				 }
				
				 int value2 = (Integer)(expBin.getStep().get(idxOpdn).getData().getValue());
				 
				 if ( value1 == value2 ) break;
			}
			
			if ( idx != step.getOpnd().size() ) {
			  ris = true;	
			} else {
			  ris = false;	
			}
			
		} else if ( Utility.isBoolean(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
	     	boolean value1 = (Boolean)(expBin.getStep().get(idxOpd1).getData().getValue());
	     	int idx=1;
			
			for ( idx=1;idx<step.getOpnd().size();idx++ ) {
				 idxOpdn = step.getOpnd().get(idx);
				
				 if ( !Utility.isBoolean(expBin.getStep().get(idxOpdn).getData().getValue()) ) {
					Logger.error("Binary Expression corrupted");
					return false;
				 }
				
				 boolean value2 = (Boolean)(expBin.getStep().get(idxOpdn).getData().getValue());
				 
				 if ( value1 == value2 ) break;
			}
			
			if ( idx != step.getOpnd().size() ) {
			  ris = true;	
			} else {
			  ris = false;	
			}
			
		} else if ( Utility.isDouble(expBin.getStep().get(idxOpd1).getData().getValue()) ) {
	     	double value1 = (Double)(expBin.getStep().get(idxOpd1).getData().getValue());
	     	int idx=1;
			
			for ( idx=1;idx<step.getOpnd().size();idx++ ) {
				 idxOpdn = step.getOpnd().get(idx);
				
				 if ( !Utility.isDouble(expBin.getStep().get(idxOpdn).getData().getValue()) ) {
					Logger.error("Binary Expression corrupted");
					return false;
				 }
				
				 double value2 = (Double)(expBin.getStep().get(idxOpdn).getData().getValue());
				 
				 if ( value1 == value2 ) break;
			}
			
			if ( idx != step.getOpnd().size() ) {
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
