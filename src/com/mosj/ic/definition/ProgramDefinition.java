package com.mosj.ic.definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramDefinition implements Definition {

	private static final long serialVersionUID = 6958775983258128354L;

	private String name = "";
	private Map<String, StructDefinition> structs = new Hashtable<>();
	private Map<String, VariableDefinition> globals = new Hashtable<>();
	private Map<String, FunctionDefinition> functions = new Hashtable<>();

	public ProgramDefinition() { }
	public ProgramDefinition(String name, Map<String, StructDefinition> structs, Map<String, VariableDefinition> globals, Map<String, FunctionDefinition> functions) {
		this();
		this.name = name;
		this.structs = structs;
		this.globals = globals;
		this.functions = functions;
	}
	public ProgramDefinition(String name, List<StructDefinition> structs, List<VariableDefinition> globals, List<FunctionDefinition> functions) {
		this(name,
				structs.stream().collect(Collectors.toMap(s -> s.getName(), s -> s)),
				globals.stream().collect(Collectors.toMap(s -> s.getName(), s -> s)),
				functions.stream().collect(Collectors.toMap(s -> s.getName(), s -> s)));
	}
	public ProgramDefinition(String name, List<StructDefinition> structs, List<VariableDefinition> globals, FunctionDefinition... functions) {
		this(name, structs, globals, Arrays.asList(functions));
	}
	public ProgramDefinition(String name, FunctionDefinition... functions) {
		this(name, new ArrayList<>(), new ArrayList<>(), Arrays.asList(functions));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, StructDefinition> getStructs() {
		return structs;
	}
	public void setStructs(Map<String, StructDefinition> structs) {
		this.structs = structs;
	}
	
	public Map<String, VariableDefinition> getGlobals() {
		return globals;
	}
	public void setGlobals(Map<String, VariableDefinition> globals) {
		this.globals = globals;
	}

	public Map<String, FunctionDefinition> getFunctions() {
		return functions;
	}
	public void setFunctions(Map<String, FunctionDefinition> functions) {
		this.functions = functions;
	}
	
	@Override
	public String toString() {
		return toJson();
	}
}
