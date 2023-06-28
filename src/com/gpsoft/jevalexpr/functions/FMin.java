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

public class FMin extends Function{

	public FMin() {
		super();
		
		this.name = "min";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_min; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpdn;
		
		if (step.getOpnd().size() < 2 ) {
			Logger.error("Function min work with at least two arguments.");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		TypeData firstArg = expBin.getStep().get(idxOpd).getResType();
		if ( firstArg == TypeData.E_boolean || firstArg == TypeData.E_date ) {
			Logger.error("Function min work only with Strings or numbers (integer or double).");
			return false;
		}
		
		for(int i=1;i<step.getOpnd().size(); i++) {
		
			idxOpdn = step.getOpnd().get(i);
			
			if ( expBin.getStep().get(idxOpdn).getResType() == TypeData.E_boolean || 
				 expBin.getStep().get(idxOpdn).getResType() == TypeData.E_date ) {
				Logger.error("Function min work only with Strings or numbers (integer or double).");
				return false;
			}	
			
			if ( (firstArg == TypeData.E_int || firstArg == TypeData.E_double) && 
				 (expBin.getStep().get(idxOpdn).getResType() != TypeData.E_int && 
				  expBin.getStep().get(idxOpdn).getResType() != TypeData.E_double) ) {
			      Logger.error("Function min all arguments must be of same type Strings or numbers (integer or double).");
			      return false;
			} else if ( firstArg == TypeData.E_string && expBin.getStep().get(idxOpdn).getResType() != TypeData.E_string ) {
			      Logger.error("Function min all arguments must be of same type Strings or numbers (integer or double).");
			      return false;
			} 
		}
		
		expBin.getStep().get(idxStep).setResType(firstArg);
		
		return true;
	}
	
	private boolean compareData(DataValue<?> data1, DataValue<?> data2 ) {
	
		if ( data1.getTypeData() == TypeData.E_string ) {
			String value1 = (String)data1.getValue();
			String value2 = (String)data2.getValue();
			
			//Logger.error("Compare String '" + value1 + "' with '" + value2 + "'");
			
			if ( value1.compareTo(value2) > 0 ) {
				return true;
			} else {
				return false;
			}
		} else if ( data1.getTypeData() == TypeData.E_double || data1.getTypeData() == TypeData.E_int ) {
			double value1 = 0.0;
			double value2 = 0.0;
			if ( data1.getTypeData() == TypeData.E_double ) {
				value1 = (Double)data1.getValue();
			}
			if ( data1.getTypeData() == TypeData.E_int ) {
				int value_int = (Integer)data1.getValue();
				value1 = value_int;
			}
			if ( data2.getTypeData() == TypeData.E_double ) {
				value2 = (Double)data2.getValue();
			}
			if ( data2.getTypeData() == TypeData.E_int ) {
				int value_int = (Integer)data2.getValue();
				value2 = value_int;
			}
			
			//Logger.error("In func confronta " + value1 + " con " + value2);
			
			if ( value1 > value2 ) {
				return true;
			} else {
				return false;
			}
		} else {
		  return false;
		}
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FMin");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		
		int minArg = 0;
		int idxOpd1 = step.getOpnd().get(0);
		int idxOpdn = 0;
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		for(int i=1;i<step.getOpnd().size();i++) {
			
			idxOpdn = step.getOpnd().get(i);
				
			if ( !expBin.getStep().get(idxOpdn).getFunction().exec(expBin, idxOpdn) ) return false;

			if ( expBin.getStep().get(idxOpdn).isNull() ) {
				//Logger.error("continua");
				continue;
			} else {
//				Logger.error("Compare " + expBin.getStep().get(minArg).getData().getValue() + " with " + 
//						                  expBin.getStep().get(idxOpdn).getData().getValue());
				if ( compareData(expBin.getStep().get(minArg).getData(), expBin.getStep().get(idxOpdn).getData()) ) {
					Logger.error("true");
					minArg = i;	
				} else {
					Logger.error("false");
					
				}
			}
		} 
		
		expBin.getStep().get(idxStep).setTypeData(expBin.getStep().get(minArg).getData().getTypeData());
		expBin.getStep().get(idxStep).setData(expBin.getStep().get(minArg).getData());
		expBin.getStep().get(idxStep).setNull(false);	
				
		return true;
	}
}