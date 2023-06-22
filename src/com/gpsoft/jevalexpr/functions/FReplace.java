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

public class FReplace extends Function{

	public FReplace() {
		super();
		
		this.name = "replace";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_replace; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		int idxOpd3;
		
		if (step.getOpnd().size() != 2 && step.getOpnd().size() != 3 ) {
			Logger.error("Function replace work with two or three arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(0);
		
		if ( step.getOpnd().size() == 3 ) {
			idxOpd3 = step.getOpnd().get(0);
     		if ( expBin.getStep().get(idxOpd3).getResType() != TypeData.E_string ) {
     			Logger.error("function replace third argument must be a string.");
     			return false;
     		}
		}
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string &&
		     expBin.getStep().get(idxOpd2).getResType() != TypeData.E_string ) {
			Logger.error("function replace all arguments must be strings.");
			return false;
		}

		expBin.getStep().get(idxStep).setResType(TypeData.E_string);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FReplace");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		String value = "";
		String oldString = "";
		String newString = "";
		String ris = null;
		int idxOpd1;
		int idxOpd2;
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd2).isNull() ) {
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}

		if ( step.getOpnd().size() == 3 ) {
			int idxOpd3;
			idxOpd3 = step.getOpnd().get(2);
			
			if ( !expBin.getStep().get(idxOpd3).getFunction().exec(expBin, idxOpd3) ) return false;
			
			if ( !expBin.getStep().get(idxOpd3).isNull() ) {
				newString = (String)expBin.getStep().get(idxOpd3).getData().getValue();
			}
		}

		
		value = (String)expBin.getStep().get(idxOpd1).getData().getValue();
		oldString = (String)expBin.getStep().get(idxOpd2).getData().getValue();

		
		ris = value.replace(oldString,newString);

		if ( ris != null && ris.length() !=0 ) {
 		   expBin.getStep().get(idxStep).setData(new DataValue<String>(ris));
		   expBin.getStep().get(idxStep).setNull(false);
		} else {
 		   expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
		   expBin.getStep().get(idxStep).setNull(true);
		}
		
		return true;
	}
}