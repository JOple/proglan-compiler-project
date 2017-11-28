package com.mosj.ic.definition;

import com.mosj.ic.value.ValueType;

public class VariableDefinition implements Definition {

	private static final long serialVersionUID = 4176402263016518606L;

	private String name = "";
	private String type = "";
	private String value = "";
	private boolean isConstant = false;

	
	public VariableDefinition() { }
	public VariableDefinition(String name, String type, String value, boolean isConstant) {
		this();
		this.name = name;
		this.type = type;
		this.value = value;
		this.isConstant = isConstant;
	}
	public VariableDefinition(String name, ValueType type, String value, boolean isConstant) {
		this(name, type.getId(), value, isConstant);
	}
	public VariableDefinition(String name, ValueType type, String value) {
		this(name, type.getId(), value, false);
	}
	public VariableDefinition(String name, ValueType type) {
		this(name, type.getId(), null, false);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public boolean isConstant() {
		return isConstant;
	}
	public void setConstant(boolean isConstant) {
		this.isConstant = isConstant;
	}
	
	@Override
	public String toString() {
		return toJson();
	}
}
