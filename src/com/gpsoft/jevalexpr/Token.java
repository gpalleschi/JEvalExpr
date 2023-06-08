package com.gpsoft.jevalexpr;

import com.gpsoft.jevalexpr.functions.Function;
import com.gpsoft.jevalexpr.log.Logger;

public class Token<T> {
	String                tokenName;           /* TOKEN                                           */

	TypeToken             typeToken;           /* TIPO DI TOKEN                                   */
	int                   operatorSyntaxType;  /* TIPO DI SINTASSI - OPERATORE                    */
	int                   operatorPriority;    /* PRIORITA - OPERATORE                            */
	int                   idxPartOpe;          /* INDICE DELLA PARTE DI OPERATORE - OPERATORE     */
	ValueType             valueType;           /* TIPO DI VALORE - VALORE                         */
	TypeStep              typeStep;            /* TIPO DI STEP CHE VERRA' GENERATO - NO RISULTATO */
	int                   stepRef;             /* STEP A CUI SI RIFERISCE - VALORE - RISULTATO    */
	TypeData              typeData;        
	DataValue<?>          data;               /* VALORE TOKEN                   */
	int                   nativeIdx;           /* INDICE DEL TOKEN NATIVO                         */
	Function              function;            /* FUNCTION */
	
	public Token(TypeToken typeToken, int nativeIdx) {
		super();
		this.typeToken = typeToken;
		this.nativeIdx = nativeIdx;
	}
	
	public Token(String tokenName, TypeToken typeToken, int opeartorSyntaxType,
			int operatorPriority, int idxPartOpe, ValueType valueType, TypeStep typeStep, int stepRef,
			TypeData typeData, T value, int nativeIdx) {
		super();
		this.tokenName = tokenName;
		this.typeToken = typeToken;
		this.operatorSyntaxType = opeartorSyntaxType;
		this.operatorPriority = operatorPriority;
		this.idxPartOpe = idxPartOpe;
		this.valueType = valueType;
		this.typeStep = typeStep;
		this.stepRef = stepRef;
		this.typeData = typeData;
		
		if ( value != null ) {
			if ( Utility.isBoolean(value) ) {
				data = new DataValue<Boolean>((Boolean)value);
			} else 		
			if ( Utility.isString(value) ) {
				data = new DataValue<String>((String)value);
			} else			
			if ( Utility.isInteger(value) ) {
				Logger.debug("Token Integer");
				data = new DataValue<Integer>((Integer)value);
			} else
			if ( Utility.isDouble(value) ) {
				Logger.debug("Token Double");
				data = new DataValue<Double>((Double)value);
			}	
			this.data.setTypeData(typeData);
			this.data.setNull(false);
		} else {
			data = new DataValue<T>(null);
			this.data.setTypeData(typeData);
			this.data.setNull(true);
		}
		this.nativeIdx = nativeIdx;
	}

	public Token(String tokenName, TypeToken typeToken, int opeartorSyntaxType,
			int operatorPriority, int idxPartOpe, ValueType valueType, TypeStep typeStep, int stepRef,
			TypeData typeData, T value, int nativeIdx, Function function) {
		super();
		this.tokenName = tokenName;
		this.typeToken = typeToken;
		this.operatorSyntaxType = opeartorSyntaxType;
		this.operatorPriority = operatorPriority;
		this.idxPartOpe = idxPartOpe;
		this.valueType = valueType;
		this.typeStep = typeStep;
		this.stepRef = stepRef;
		this.typeData = typeData;
		
		if ( value != null ) {
			if ( Utility.isBoolean(value) ) {
				data = new DataValue<Boolean>((Boolean)value);
			} else		
			if ( Utility.isString(value) ) {
				data = new DataValue<String>((String)value);
			} else
			if ( Utility.isInteger(value) ) {
				Logger.debug("Token Integer");
				data = new DataValue<Integer>((Integer)value);
			} else
			if ( Utility.isDouble(value) ) {
				Logger.debug("Token Double");
				data = new DataValue<Double>((Double)value);
			}	
			this.data.setTypeData(typeData);
			this.data.setNull(false);
		} else {
			data = new DataValue<T>(null);
			this.data.setTypeData(typeData);
			this.data.setNull(true);
		}

		this.nativeIdx = nativeIdx;
		this.function = function;
	}


	/****
	public Token(String tokenName, 
			     TypeToken typeToken, 
			     OperatorSyntaxType opeartorSyntaxType,
			     OperatorPriority operatorPriority, 
			     int idxPartOpe, 
			     ValueType valueType, 
			     TypeStep typeStep, 
			     int stepRef,
			     T value, 
			     int nativeIdx) {
		super();
		this.tokenName = tokenName;
		this.typeToken = typeToken;
		this.opeartorSyntaxType = opeartorSyntaxType;
		this.operatorPriority = operatorPriority;
		this.idxPartOpe = idxPartOpe;
		this.valueType = valueType;
		this.typeStep = typeStep;
		this.stepRef = stepRef;
		this.value = value;
		this.nativeIdx = nativeIdx;
	}
	****/

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public TypeToken getTypeToken() {
		return typeToken;
	}

	public void setTypeToken(TypeToken typeToken) {
		this.typeToken = typeToken;
	}

	public int getOperatorSyntaxType() {
		return operatorSyntaxType;
	}

	public void setOpeartorSyntaxType(int opeartorSyntaxType) {
		this.operatorSyntaxType = opeartorSyntaxType;
	}

	public int getOperatorPriority() {
		return operatorPriority;
	}

	public void setOperatorPriority(int operatorPriority) {
		this.operatorPriority = operatorPriority;
	}

	public int getIdxPartOpe() {
		return idxPartOpe;
	}

	public void setIdxPartOpe(int idxPartOpe) {
		this.idxPartOpe = idxPartOpe;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	public int getStepRef() {
		return stepRef;
	}

	public void setStepRef(int stepRef) {
		this.stepRef = stepRef;
	}

	public DataValue<?> getData() {
		return data;
	}

	/*
	public void setValue(T value) {
		if ( value != null ) {
			this.data.isNull = false;
		} else {
			this.data.isNull = true;
		}
		this.data.value = value;
		
	}*/

	public int getNativeIdx() {
		return nativeIdx;
	}

	public void setNativeIdx(int nativeIdx) {
		this.nativeIdx = nativeIdx;
	}

	public TypeStep getTypeStep() {
		return typeStep;
	}

	public TypeData getTypeData() {
		return typeData;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}	
	
	

}
