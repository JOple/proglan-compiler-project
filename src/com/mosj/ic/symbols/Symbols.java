package com.mosj.ic.symbols;

import com.mosj.ic.definition.VariableDefinition;
import com.mosj.ic.value.ValueType;

public class Symbols {

	public static StandardSymbolManager manager() {
		return new StandardSymbolManager();
	}

	public static Symbol of() {
		return new Symbol().setAbsent(true);
	}
	public static Symbol of(String name) {
		return new Symbol().setName(name);
	}
	public static Symbol of(String name, String value, SymbolType type, boolean isConstant) {
		return of(name).setValue(value).setConstant(isConstant);
	}
	public static Symbol of(String name, String value, String type, boolean isConstant) {
		return of(name, value, ofType(type), isConstant);
	}
	public static Symbol of(VariableDefinition v) {
		return of(v.getName(), v.getValue(), v.getType(), v.isConstant());
	}
	public static Symbol ofV(String name, String value, String type) {
		return of(name, value, type, false);
	}
	public static Symbol ofV(String name, String type) {
		return of(name, null, type, false);
	}
	public static Symbol ofC(String name, String value, String type) {
		return of(name, value, type, true);
	}

	public static SymbolType ofType(ValueType t) {
		return new SymbolType(t.getId());
	}
	public static SymbolType ofType(String s) {
		SymbolType stack = new SymbolType();
		for(int i = 0; i < s.length(); i++) {
			ValueType v = null;
			for(ValueType t : ValueType.values()) {
				if(s.substring(i).startsWith(t.getId())) {
					v = t;
					break;
				}
			}
			if(v != null) {
				if(i > 0)
					stack = stack.up(s.substring(0, i));
				stack = stack.up(v.getId());
				s = s.substring(i + v.getId().length());
				i = -1;
			}
		}
		if(s.length() > 0)
			stack = stack.up(s);
		return stack;
	}

	public static ValueType valueTypeOf(String value) {
		ValueType val = ValueType.OBJECT;
		try {
			Integer.parseInt(value);
			val = ValueType.INT;
		} catch(NumberFormatException e1) {
			try {
				Float.parseFloat(value);
				val = ValueType.FLOAT;
			} catch(NumberFormatException e2) {
				if(value.length() == 3 && value.startsWith("\'") && value.endsWith("\'"))
					val = ValueType.CHAR;
				else if(value.startsWith("\"") && value.endsWith("\""))
					val = ValueType.STRING;
				else if("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value))
					val = ValueType.BOOL;
			}
		}
		return val;
	}
}
