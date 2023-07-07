package com.gpsoft.jevalexpr;

import java.util.ArrayList;

/**
 * This class represents expression compiled
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public class ExpBin<T> {
	ArrayList<Step<?>> step;
	private ArrayList<Variable<?>> variables;

	public ExpBin() {
		super();
		step = new ArrayList<Step<?>>();
	}

	public ArrayList<Step<?>> getStep() {
		return step;
	}

	public void setVariables(ArrayList<Variable<?>> variables) {
		this.variables = variables;
	}

	public ArrayList<Variable<?>> getVariables() {
		return variables;
	}

	
}
