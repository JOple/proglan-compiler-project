package com.mosj.ic.value;

import java.io.Serializable;

public interface Value extends Holder<Serializable>, Serializable {

	boolean isNull();
	boolean isBool();
	boolean isChar();
	boolean isByte();
	boolean isShort();
	boolean isInt();
	boolean isLong();
	boolean isFloat();
	boolean isDouble();
	boolean isIntegral();
	boolean isDecimal();
	boolean isNumber();
	boolean isPrimitive();
	boolean isString();

	Boolean asBool();
	Character asChar();
	Byte asByte();
	Short asShort();
	Integer asInt();
	Long asLong();
	Float asFloat();
	Double asDouble();
	Number asNumber();
	String asString();
	
	Value copy();
}
