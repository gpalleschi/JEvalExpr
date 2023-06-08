package com.gpsoft.jevalexpr;

public class DataValue<T> {

	TypeData typeData;
	boolean isNull;
	T value;
	
	public DataValue() {
		super();
	}

	public DataValue(T value) {
		super();
		this.value = value;
	}
	
	public TypeData getTypeData() {
		return typeData;
	}
	public void setTypeData(TypeData typeData) {
		this.typeData = typeData;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}

}
