package com.mosj.ic.symbols;

import java.io.Serializable;
import java.util.Map;

import com.mosj.ic.value.Value;

public interface SymbolProps {

	String PROP_VALUE = "==value";
	String PROP_IS_CONSTANT = "==isConstant";
	String PROP_IS_ABSENT = "==isAbsent";
	String PROP_SCOPE_ID = "==scopeId";
	String PROP_ARRAY_SIZE = "==arraySize";
	
	Value prop(String name);
	void prop(String name, Serializable val);
	Map<String, Value> getProps();

	default String getValue() {
		return prop(PROP_VALUE).asString();
	}
	default Symbol setValue(String val) {
		prop(PROP_VALUE).set(val);
		return (Symbol) this;
	}
	
	default boolean isConstant() {
		return prop(PROP_IS_CONSTANT).asBool();
	}
	default Symbol setConstant(boolean isConstant) {
		prop(PROP_IS_CONSTANT).set(isConstant);
		return (Symbol) this;
	}

	default boolean isAbsent() {
		return prop(PROP_IS_ABSENT).asBool();
	}
	default Symbol setAbsent(boolean isPresent) {
		prop(PROP_IS_ABSENT).set(isPresent);
		return (Symbol) this;
	}

	default int getScopeId() {
		return prop(PROP_SCOPE_ID).asInt();
	}
	default Symbol setScopeId(int scopeId) {
		prop(PROP_SCOPE_ID).set(scopeId);
		return (Symbol) this;
	}

	default int getArraySize() {
		return prop(PROP_ARRAY_SIZE).asInt();
	}
	default Symbol setArraySize(int scopeId) {
		prop(PROP_ARRAY_SIZE).set(scopeId);
		return (Symbol) this;
	}
}
