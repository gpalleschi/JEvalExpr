package com.gpsoft.jevalexpr.functions;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.Token;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.ValueType;

public abstract class Function {

	String name;
	Token<Object> token;
	
	TypeToken typeToken;
	int operatorSyntaxType;
	int operatorPriority;
	int idxPartOpe;
	ValueType valueType;
	TypeStep typeStep;
	int stepRef;
	TypeData typeData;
	
	public boolean check(ExpBin<?> expBin, int stepIdx) {
		return false;
	}
	public boolean exec(ExpBin<?> expBin, int stepIdx) {
		return false;
	}
	public String getName() {
		return name;
	}

	public TypeToken getTypeToken() {
		return typeToken;
	}

	public int getOperatorSyntaxType() {
		return operatorSyntaxType;
	}

	public int getOperatorPriority() {
		return operatorPriority;
	}

	public int getIdxPartOpe() {
		return idxPartOpe;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public TypeStep getTypeStep() {
		return typeStep;
	}

	public int getStepRef() {
		return stepRef;
	}

	public TypeData getTypeData() {
		return typeData;
	}
	
	public void checkBefore(String substring, int expPos) {
	}
}
