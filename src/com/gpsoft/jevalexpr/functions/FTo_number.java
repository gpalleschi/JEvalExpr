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

public class FTo_number extends Function{

	public FTo_number(String name) {
		super();
		
		this.name = name;
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_to_number; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		if (step.getOpnd().size() != 1 ) {
			Logger.error("Function to_number work with one argument not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		
		if ( expBin.getStep().get(idxOpd).getResType() != TypeData.E_string ) {
			Logger.error("function to_number work only with string argument.");
			return false;
		}
		
		expBin.getStep().get(idxStep).setResType(TypeData.E_int);
		
		return true;
	}

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FTo_number");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1;
		idxOpd1 = step.getOpnd().get(0);
		
		Logger.debug("To_Number indice operando 1 : " + idxOpd1);
		
	
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			expBin.getStep().get(idxStep).setNull(true);
			return true;
		}
		
		Logger.debug("Operando : " + idxOpd1 + " : " + expBin.getStep().get(idxOpd1).getResType());
		
		if ( expBin.getStep().get(idxOpd1).getResType() == TypeData.E_string ) {
			
			
			if ( expBin.getStep().get(idxOpd1).getData().getValue().toString().indexOf('.') >= 0 ) {
				double ris;
				try {
					ris = Double.valueOf((String) expBin.getStep().get(idxOpd1).getData().getValue() );
					
					Logger.debug("Ris value : " + ris);
				} catch (NumberFormatException e) {
					Logger.error("String <" + (String) expBin.getStep().get(idxOpd1).getData().getValue() + "> not a number.");
					return false;
				}
				expBin.getStep().get(idxStep).setTypeData(TypeData.E_int);
				expBin.getStep().get(idxStep).setData(new DataValue<Double>(ris));
				expBin.getStep().get(idxStep).setNull(false);
				
			} else {
				int ris;
				try {
					ris = Integer.valueOf((String) expBin.getStep().get(idxOpd1).getData().getValue() );
					
					Logger.debug("Ris value : " + ris);
				} catch (NumberFormatException e) {
					Logger.error("String <" + (String) expBin.getStep().get(idxOpd1).getData().getValue() + "> not a number.");
					return false;
				}
				expBin.getStep().get(idxStep).setTypeData(TypeData.E_int);
				expBin.getStep().get(idxStep).setData(new DataValue<Integer>(ris));
				expBin.getStep().get(idxStep).setNull(false);
			}
			
			
		} else {
			return false;
		}
		
		return true;
	}
}
