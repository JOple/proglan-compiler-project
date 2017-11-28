package com.mosj.ic.compiler;

import com.mosj.ic.definition.VariableDefinition;
import com.mosj.ic.symbols.StandardSymbolManager;
import com.mosj.ic.symbols.Symbol;
import com.mosj.ic.symbols.SymbolType;
import com.mosj.ic.symbols.Symbols;

public class CodeSymbolManager extends StandardSymbolManager {

	public static CodeSymbolManager create() {
		return new CodeSymbolManager();
	}
	
	public Symbol create(String name, SymbolType type) {
		return create(name).setType(type);
	}
	public Symbol create(String name, String type) {
		return create(name, Symbols.ofType(type));
	}
	public Symbol createConst(VariableDefinition v) {
		return create(v.getName(), v.getType())
				.setConstant(true)
				.setValue(v.getValue());
	}
}
