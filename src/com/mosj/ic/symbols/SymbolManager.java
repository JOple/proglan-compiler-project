package com.mosj.ic.symbols;

public interface SymbolManager {

	void enterScope();
	SymbolTable exitScope();

	Symbol get(String symbolName);
	Symbol create(String symbolName);
	
	SymbolStack getStack();
	default SymbolTable getCurrentTable() {
		return getStack().isEmpty() ? null : getStack().peek();
	}
}
