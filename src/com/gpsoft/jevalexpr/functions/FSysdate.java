package com.gpsoft.jevalexpr.functions;

import java.time.LocalDateTime;
import com.gpsoft.jevalexpr.DataValue;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FSysdate extends Function {

	public FSysdate() {
	
		super();
		
		this.name = "sysdate";
		this.typeToken = TypeToken.E_value;
		this.operatorSyntaxType = OperatorSyntaxType.E_funi;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_sysdate; 
		this.stepRef = 0;
		this.typeData = TypeData.E_date;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {
		expBin.getStep().get(idxStep).setResType(TypeData.E_date);
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FSysdate");	
		
		LocalDateTime dateTime = LocalDateTime.now();
			
		expBin.getStep().get(idxStep).setTypeData(TypeData.E_date);
		expBin.getStep().get(idxStep).setData(new DataValue<LocalDateTime>(dateTime));
		expBin.getStep().get(idxStep).setNull(false);	
		
		return true;
	}
}