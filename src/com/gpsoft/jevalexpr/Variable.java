package com.gpsoft.jevalexpr;

public class Variable<T> {
	private String name;
	private TypeData typeVariable;
	
	private T value;

	public Variable(String name, T value) {
		super();
		this.name = name;
		if ( Utility.isBoolean(value) ) this.typeVariable = TypeData.E_boolean;
		if ( Utility.isString(value) ) this.typeVariable = TypeData.E_string;
		if ( Utility.isInteger(value) ) this.typeVariable = TypeData.E_int;
		if ( Utility.isDouble(value) ) this.typeVariable = TypeData.E_double;
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeData getTypeVariable() {
		return typeVariable;
	}

	public void setTypeVariable(TypeData typeVariable) {
		this.typeVariable = typeVariable;
	}

}
