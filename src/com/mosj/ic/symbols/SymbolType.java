package com.mosj.ic.symbols;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mosj.ic.value.ValueType;

public class SymbolType {
	
	private List<String> typeStack;
	private Object meta;

	public SymbolType() {
		this.typeStack = Collections.emptyList();
	}
	public SymbolType(String type) {
		this.typeStack = Collections.singletonList(type);
	}
	public SymbolType(List<String> type) {
		this.typeStack = Collections.unmodifiableList(type);
	}
	public SymbolType(Object meta) {
		this.typeStack = Collections.emptyList();
		this.meta = meta;
	}
	public SymbolType(String type, Object meta) {
		this.typeStack = Collections.singletonList(type);
		this.meta = meta;
	}
	public SymbolType(List<String> type, Object meta) {
		this.typeStack = Collections.unmodifiableList(type);
		this.meta = meta;
	}

	public SymbolType down() {
		return new SymbolType(new LinkedList<>(typeStack.subList(0, typeStack.size() - 1)));
	}
	public SymbolType up(String type) {
		List<String> list = new LinkedList<>(typeStack);
		list.add(type);
		return new SymbolType(list);
	}
	public SymbolType up(ValueType t) {
		return up(t.getId());
	}
	
	public SymbolType meta(Object info) {
		return new SymbolType(stack(), info);
	}
	public Object meta() {
		return meta;
	}
	
	public String top() {
		return typeStack.get(typeStack.size() - 1);
	}
	public List<String> stack() {
		return typeStack;
	}
	
	public boolean isSimple() {
		return typeStack.size() == 1;
	}
	public boolean isComplex() {
		return typeStack.size() > 1;
	}
	public boolean isVoid() {
		return !isComplex() && ValueType.VOID.getId().equals(top());
	}
	public boolean isBool() {
		return !isComplex() && ValueType.BOOL.getId().equals(top());
	}
	public boolean isChar() {
		return !isComplex() && ValueType.CHAR.getId().equals(top());
	}
	public boolean isInt() {
		return !isComplex() && ValueType.INT.getId().equals(top());
	}
	public boolean isFloat() {
		return !isComplex() && ValueType.FLOAT.getId().equals(top());
	}
	public boolean isNumber() {
		return isInt() || isFloat();
	}
	public boolean isString() {
		return !isComplex() && ValueType.STRING.getId().equals(top());
	}
	public boolean isAny() {
		return !isComplex() && ValueType.OBJECT.getId().equals(top());
	}
	public boolean isPointer() {
		return isComplex() && ValueType.POINTER.getId().equals(top());
	}
	public boolean isArray() {
		return isComplex() && ValueType.ARRAY.getId().equals(top());
	}
	public boolean isStruct() {
		if(isComplex())
			return false;
		ValueType v = null;
		String s = typeStack.get(0);
		for(ValueType t : ValueType.values()) {
			if(t.getId().equals(s)) {
				v = t;
				break;
			}
		}
		return v == null;
	}
	
	public ValueType primitiveType() {
		if(isInt() || isArray() || isStruct() || isPointer())
			return ValueType.INT;
		if(isBool())
			return ValueType.BOOL;
		if(isChar())
			return ValueType.CHAR;
		if(isFloat())
			return ValueType.FLOAT;
		if(isString())
			return ValueType.STRING;
		if(isAny())
			return ValueType.OBJECT;
		return ValueType.VOID;
	}

	public SymbolType copy() {
		return new SymbolType(stack(), meta());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SymbolType other = (SymbolType) obj;
		if (typeStack == null) {
			if (other.typeStack != null)
				return false;
		} else if (!typeStack.equals(other.typeStack))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.join("", typeStack);
	}
}
