package com.mosj.ic.value;

import com.mosj.ic.grammar.Co2pNLexer;
import com.mosj.ic.value.Value;

public enum ValueType {

	VOID(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.VOID).replaceAll("\'", ""), Void.class, Values.ofNull()),
	BOOL(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.BOOLEAN).replaceAll("\'", ""), Boolean.class, Values.ofBool(false)),
	CHAR(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.CHAR).replaceAll("\'", ""), Character.class, Values.ofChar('\0')),
	INT(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.INT).replaceAll("\'", ""), Integer.class, Values.ofInt(0)),
	FLOAT(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.FLOAT).replaceAll("\'", ""), Float.class, Values.ofFloat(0)),
	STRING(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.STRING).replaceAll("\'", ""), String.class, Values.ofString(null)),
	OBJECT(Co2pNLexer.VOCABULARY.getDisplayName(Co2pNLexer.OBJECT).replaceAll("\'", ""), Void.class, Values.ofAny(null)),
	ARRAY("[]", Integer.class, Values.ofInt(0)),
	POINTER("^", Integer.class, Values.ofInt(0));
	
	public static ValueType of(String s) {
		for(ValueType d : ValueType.values())
			if(d.getId().toLowerCase().equals((s + "").toLowerCase()))
				return d;
		return VOID;
	}
	
	private String id;
	private Class<?> basis;
	private Value basisValue;
	
	private ValueType(String id, Class<?> basis, Value value) {
		this.id = id;
		this.basis = basis;
		this.basisValue = value;
	}

	public String getId() {
		return id;
	}
	public Class<?> getBasis() {
		return basis;
	}
	public Value getBasisValue() {
		return basisValue;
	}
	
	public Value gen() {
		return basisValue.copy();
	}
}
