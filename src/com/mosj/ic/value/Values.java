package com.mosj.ic.value;

import java.io.Serializable;

public class Values {

	private Values() { }

	private static final Value NULL = new ValNull();

	public static Value ofNull() {
		return NULL;
	}
	public static Value ofConst(Serializable val) {
		Value v = new ValConst();
		v.set(val);
		return v;
	}
	public static Value ofAny(Serializable val) {
		Value v = new ValAny();
		v.set(val);
		return v;
	}
	public static Value ofBool(boolean bool) {
		Value v = new ValBool();
		v.set(bool);
		return v;
	}
	public static Value ofChar(char ch) {
		Value v = new ValChar();
		v.set(ch);
		return v;
	}
	public static Value ofInt(long i) {
		Value v = new ValInt();
		v.set(i);
		return v;
	}
	public static Value ofFloat(double f) {
		Value v = new ValFloat();
		v.set(f);
		return v;
	}
	public static Value ofString(String s) {
		Value v = new ValString();
		v.set(s);
		return v;
	}
}
