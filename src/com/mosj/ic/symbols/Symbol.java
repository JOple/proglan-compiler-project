package com.mosj.ic.symbols;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mosj.ic.value.Value;
import com.mosj.ic.value.Values;

public class Symbol implements SymbolProps {

	private String name;
	private SymbolType type;
	private Map<String, Value> props = new LinkedHashMap<>();

	public Symbol() { }
	
	public String getId() {
		return getScopeId() + "@" + getName();
	}
	
	public String getName() {
		return name;
	}
	public Symbol setName(String name) {
		this.name = name;
		return this;
	}
	
	public SymbolType getType() {
		return type;
	}
	public Symbol setType(SymbolType type) {
		this.type = type;
		return this;
	}

	@Override
	public Value prop(String name) {
		if(!getProps().containsKey(name))
			getProps().put(name, Values.ofAny(null));
		return getProps().get(name);
	}
	@Override
	public void prop(String name, Serializable val) {
		prop(name).set(val);
	}

	public Map<String, Value> getProps() {
		return props;
	}

	@Override
	public String toString() {
		return "Symbol [name=" + name + ", type=" + type + ", props=" + props + "]";
	}
}
