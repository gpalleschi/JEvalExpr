package com.gpsoft.jevalexpr;

import java.util.ArrayList;

import com.gpsoft.jevalexpr.functions.Function;

public class Step<T> {
	
	TypeStep      typeStep;
	TypeData      resType;
	TypeData      typeData;
	DataValue<?>  data;
	boolean       isNull;
	int util_area;
	Function      function;
	
	ArrayList<Integer> opnd = new ArrayList<Integer>();
	
	public Object getValue() {
		return data.getValue();
	}

	public TypeStep getTypeStep() {
		return typeStep;
	}

	public TypeData getResType() {
		return resType;
	}

	public DataValue<?> getData() {
		return data;
	}

	public void setData(DataValue<?> data) {
		this.data = data;
		if ( Utility.isString(data.value) ) {
			data.typeData = TypeData.E_string;
		} else if ( Utility.isInteger(data.value) ) {
			data.typeData = TypeData.E_int;
		} else if ( Utility.isDouble(data.value) ) {
			data.typeData = TypeData.E_double;
		} else if ( Utility.isBoolean(data.value) ) {
			data.typeData = TypeData.E_boolean;
		} else if ( Utility.isDate(data.value) ) {
			data.typeData = TypeData.E_date;
		} else {
			data.typeData = null;
		}
	}

	public boolean isNull() {
		return isNull;
	}


	public ArrayList<Integer> getOpnd() {
		return opnd;
	}
	
	public Step() {
		super();
	}

	public Step(TypeStep typeStep, TypeData resType, T value, boolean isNull, ArrayList<Integer> opnd, int util_area) {
		super();
		this.typeStep = typeStep;
		this.resType = resType;
		this.typeData = resType;
		
		if ( Utility.isBoolean(value) ) {
			data = new DataValue<Boolean>((Boolean)value);
		} else
		if ( Utility.isString(value) ) {
			data = new DataValue<String>((String)value);
		} else
		if ( Utility.isInteger(value) ) {
			data = new DataValue<Integer>((Integer)value);
		} else
		if ( Utility.isDouble(value) ) {
			data = new DataValue<Double>((Double)value);
		}	
		
		this.isNull = isNull;
		this.opnd = opnd;
		this.util_area = util_area;
	}
	
	public Step(TypeStep typeStep, TypeData resType, T value, boolean isNull, ArrayList<Integer> opnd, int util_area, Function function) {
		super();
		this.typeStep = typeStep;
		this.resType = resType;
		this.typeData = resType;
		this.isNull = isNull;
		
		if ( typeStep == TypeStep.E_constant ) {
			if ( Utility.isBoolean(value) ) {
				this.data = new DataValue<Boolean>((Boolean)value);
				this.data.typeData = TypeData.E_boolean;
			} else
			if ( Utility.isString(value) ) {
				this.data = new DataValue<String>((String)value);
				this.data.typeData = TypeData.E_string;
			} else
			if ( Utility.isInteger(value) ) {
				this.data = new DataValue<Integer>((Integer)value);
				this.data.typeData = TypeData.E_int;
			} else
			if ( Utility.isDouble(value) ) {
				this.data = new DataValue<Double>((Double)value);
				this.data.typeData = TypeData.E_double;
			}	
		} else {
			data = null;
		}
		
		this.opnd = opnd;
		this.util_area = util_area;
		this.function = function;
	}
	
	public TypeData getTypeData() {
		return typeData;
	}


	public int getUtil_area() {
		return util_area;
	}


	public void setUtil_area(int util_area) {
		this.util_area = util_area;
	}


	public void setTypeStep(TypeStep typeStep) {
		this.typeStep = typeStep;
	}


	public void setResType(TypeData resType) {
		this.resType = resType;
	}


	public void setTypeData(TypeData typeData) {
		this.typeData = typeData;
	}


	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}


	public void setOpnd(ArrayList<Integer> opnd) {
		this.opnd = opnd;
	}


	public Function getFunction() {
		return function;
	}


	public void setFunction(Function function) {
		this.function = function;
	}

	//TODO: Aggiungere comp_exp
	
}
