package com.gpsoft.jevalexpr;

import java.util.ArrayList;

public class ExpBin<T> {
	ArrayList<Step<?>> step;
	private Variable<?>[] variables;

	public ExpBin() {
		super();
		step = new ArrayList<Step<?>>();
	}

	public ArrayList<Step<?>> getStep() {
		return step;
	}

	public void setVariables(Variable<?>[] variables) {
		this.variables = variables;
	}

	public Variable<?>[] getVariables() {
		return variables;
	}

	
}
