package com.mosj.ic.symbols;

import java.io.Serializable;

import com.mosj.ic.value.ValueType;

public class NullSymbol extends Symbol {

	public static final NullSymbol INSTANCE = new NullSymbol();
	
	private SymbolType type = Symbols.ofType(ValueType.VOID);
	
	public NullSymbol() {
		super.setValue(null);
		super.setConstant(false);
		super.setAbsent(true);
		super.setScopeId(0);
		super.setArraySize(0);
	}
	
	@Override
	public SymbolType getType() {
		return type;
	}

	@Override
	public void prop(String name, Serializable val) { }
}
