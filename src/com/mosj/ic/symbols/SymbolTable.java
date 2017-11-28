package com.mosj.ic.symbols;

import java.util.LinkedHashMap;

public class SymbolTable extends LinkedHashMap<String, Symbol> {

	private static final long serialVersionUID = 5507668089403815584L;
	
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
