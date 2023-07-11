package com.gpsoft.jevalexpr.functions;

import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.Step;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FNvl extends Function{

	public FNvl() {
		super();
		
		this.name = "nvl";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_nvl; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function nvl work with two arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( expBin.getStep().get(idxOpd).getResType() != expBin.getStep().get(idxOpd2).getResType() ) {
			Logger.error("function nvl work only with arguments of same type.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(expBin.getStep().get(idxOpd).getResType());
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FNvl");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;
		idxOpd1 = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
		if ( !expBin.getStep().get(idxOpd2).getFunction().exec(expBin, idxOpd2) ) return false;
		
		if ( expBin.getStep().get(idxOpd1).getData().isNull() ) {
			
			if ( expBin.getStep().get(idxOpd2).getData().isNull() ) {
				expBin.getStep().get(idxStep).setTypeData(expBin.getStep().get(idxOpd2).getTypeData());
				expBin.getStep().get(idxStep).setNull(true);
			} else {
				expBin.getStep().get(idxStep).setData(expBin.getStep().get(idxOpd2).getData());
				expBin.getStep().get(idxStep).setTypeData(expBin.getStep().get(idxOpd2).getTypeData());
				expBin.getStep().get(idxStep).setNull(false);
			}
		} else {
			expBin.getStep().get(idxStep).setData(expBin.getStep().get(idxOpd1).getData());
			expBin.getStep().get(idxStep).setTypeData(expBin.getStep().get(idxOpd1).getTypeData());
			expBin.getStep().get(idxStep).setNull(false);
		}
	
		return true;
	}
}