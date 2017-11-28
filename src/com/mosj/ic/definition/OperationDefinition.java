package com.mosj.ic.definition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OperationDefinition implements Definition {

	private static final long serialVersionUID = 4788690792441865789L;

	private String op = "";
	private List<String> args = new LinkedList<>();
	
	public OperationDefinition() { }
	public OperationDefinition(String op, List<String> args) {
		this();
		this.op = op;
		this.args = args;
	}
	public OperationDefinition(String op, String... args) {
		this(op, Arrays.asList(args));
	}
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	
	public List<String> getArgs() {
		return args;
	}
	public void setArgs(List<String> args) {
		this.args = args;
	}
	
	@Override
	public String toString() {
		return op + "(" + String.join(",", args) + ")";
	}
}
