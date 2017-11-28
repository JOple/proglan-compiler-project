package com.mosj.ic.definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StructDefinition implements Definition {

	private static final long serialVersionUID = -8719466065211324762L;

	private String name = "";
	private List<VariableDefinition> members = new ArrayList<>();
	
	public StructDefinition() { }
	public StructDefinition(String name, List<VariableDefinition> members) {
		this();
		this.name = name;
		this.members = members;
	}
	public StructDefinition(String name, VariableDefinition... members) {
		this(name, Arrays.asList(members));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<VariableDefinition> getMembers() {
		return members;
	}
	public void setMembers(List<VariableDefinition> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return toJson();
	}
}
