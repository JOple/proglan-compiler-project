package com.mosj.ic.symbols;

import java.util.LinkedList;

public class StandardSymbolManager implements SymbolManager {

	private SymbolStack stack = new SymbolStack();
	private int scopeCount = 0;
	
	public void enterScope() {
		SymbolTable table = new SymbolTable();
		table.setId(scopeCount++);
		/*if(!getStack().isEmpty())
			table.putAll(stack.peek());*/
		getStack().push(table);
	}
	public SymbolTable exitScope() {
		return getStack().isEmpty() ? new SymbolTable() : getStack().pop();
	}
	
	@Override
	public Symbol get(String symbolName) {
		LinkedList<SymbolTable> s = new LinkedList<>(getStack());
		while(!s.isEmpty()) {
			SymbolTable t = s.removeLast();
			if(t.containsKey(symbolName))
				return t.get(symbolName);
		}
		return new NullSymbol()
				.setName(symbolName)
				.setScopeId(scopeCount);
	}
	@Override
	public Symbol create(String symbolName) {
		Symbol s = Symbols.of(symbolName)
				.setScopeId(getCurrentTable().getId());
		getCurrentTable().put(symbolName, s);
		return s;
	}
	
	@Override
	public SymbolStack getStack() {
		return stack;
	}
}
