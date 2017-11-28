package com.mosj.ic.definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mosj.ic.value.ValueType;

public class FunctionDefinition implements Definition {

	private static final long serialVersionUID = -1743007630325466403L;

	private String name = "";
	private String returnType = "";
	private List<VariableDefinition> parameters = new ArrayList<>();
	private List<OperationDefinition> operations = new ArrayList<>();
	
	public FunctionDefinition() { }
	public FunctionDefinition(String name, String returnType, List<VariableDefinition> parameters, List<OperationDefinition> operations) {
		this();
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
		this.operations = operations;
	}
	public FunctionDefinition(String name, ValueType returnType, List<VariableDefinition> parameters, List<OperationDefinition> operations) {
		this(name, returnType.getId(), parameters, operations);
	}
	public FunctionDefinition(String name, String returnType, List<VariableDefinition> parameters, OperationDefinition... operations) {
		this(name, returnType, parameters, Arrays.asList(operations));
	}
	public FunctionDefinition(String name, ValueType returnType, List<VariableDefinition> parameters, OperationDefinition... operations) {
		this(name, returnType, parameters, Arrays.asList(operations));
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public List<VariableDefinition> getParameters() {
		return parameters;
	}
	public void setParameters(List<VariableDefinition> parameters) {
		this.parameters = parameters;
	}

	public List<OperationDefinition> getOperations() {
		return operations;
	}
	public void setOperations(List<OperationDefinition> operations) {
		this.operations = operations;
	}
	
	@Override
	public String toString() {
		return toJson();
	}
}
