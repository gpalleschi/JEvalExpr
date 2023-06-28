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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FRegexp_like extends Function{

	public FRegexp_like() {
		super();
		
		this.name = "regexp_like";
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_regexp_like;
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		
		if (step.getOpnd().size() != 2 ) {
			Logger.error("Function regexp_like work only with two arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function regexp_like first argument must be a string.");
			return false;
		}

		idxOpd2 = step.getOpnd().get(1);

		if ( expBin.getStep().get(idxOpd2).getResType() != TypeData.E_string ) {
			Logger.error("function regexp_like second argument must be a string.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_boolean);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FRegexp_like");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd1;
		int idxOpd2;

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
			String expr = (String)expBin.getStep().get(idxOpd2).getData().getValue();
			
		    Pattern pattern = Pattern.compile(expr, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(value);
		    boolean matchFound = matcher.find();

			expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(matchFound));
			expBin.getStep().get(idxStep).setNull(false);
			
		} else {
			Logger.error("Binary Expression Corrupted.");
			return false;
		}
		
		return true;
	}
}