# JEvalExpr

Very effective Java Library and Tool to parse, compile and execute functions in a language SQL Like. Library is easy estensible with new functions, and it's possible passing a list of variables changing their values between executions.

<hr/>

## Table of contents

1. [Overview](#Overview)  
2. [Compile Function](#Compile-function)  
3. [Execution Function](#Execution-function)  
4. [Examples](#Examples)  
5. [List Functions](#List-Functions)  
   5.1 [rpad](#rpad)
   5.2 [lpad](#lpad)
6. [Line Command Tool](#Lct)  
7. [Add New Function](#Addnewfunction)  
8. [Authors](#Authors)  
9. [Prerequisites](#Prerequisites)  
10. [Built With](#Built-With)  
11. [Licence](#Licence)  

<hr/>

## Overview

The **JEvalExpr** library allows interpret and compile even complex expressions in a language like SQL. It's possible to specify an array of variable names and their type (**Integer, Double, String and Boolean**) during the compilation. 

The compiled expression is simplified into an array of steps it can be executed, if there are variables during execution the array of variable names and relative values ​​must be passed (It is essential that the array has the same order used in compilation stage). Once compiled, the expression can be executed several times and obviously the values ​​of the variables, if present, can be modified between the various executions.  

<hr/>

## Compile function

To compile an expression the following steps must be performed :  

1. **Instantiate an object of type Expression** :  

```
	public Expression(String expr, ArrayList<Variable<?>> var) {
		super();
		this.humanExpr = expr;
		this.variables = var;
	}
```

Contructor accept two arguments :  
- String  
- ArrayList of Variable objects  

Variable class :  

```
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
```

2. **Execute method compExpr() of object Expression** :  

```
public boolean compExpr() { ... }
```
This method returns true if compilation is ok or false if nok. All logs are produced in stdout (see. Logger class).   

<hr/>

## Execution function

To execute a compiled expression the following steps must be performed :  

1. **Execute method execExpr(...) of object Expression** :

```
public int execExpr(ArrayList<Variable<?>> variables) { ... }  
```

It's very important that arrayList of variables has the same order and types of data used during compilaling.   
This method returns true if compilation is ok or false if nok. All logs are produced in stdout (see. Logger class).   

3. **Execute method getResult(...) of object Expression** :

```
	public DataValue<?> getResult() {
		int idx = expBin.getStep().size()-1;
		if ( idx < 0 ) idx = 0;
		return expBin.getStep().get(idx).getData();
	}
```

This method returns an object DataValue with the result expression's result and its type.  

```
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
```

To get de type of result execute method getTypeData that returns a TypeData enum :  

```
public enum TypeData {
	  E_int,                      /* Type Integer                   */
	  E_double,                   /* Type Double                    */
	  E_string,                   /* Type String                    */
	  E_boolean,                  /* Type Boolean                   */
	  E_date,                     /* Type date (LocalDate, LocalDateTime or LocalTime) */
}
```

<hr/>

## Authors

* **Giovanni Palleschi** - [gpalleschi](https://github.com/gpalleschi)  

<hr/>

## Prerequisites

`>= Java 1.7`  

<hr/>

## Built With

* [Eclipse 2023-03](https://www.eclipse.org/) 

<hr/>

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE 3.0 License - see the [LICENSE](LICENSE) file for details
