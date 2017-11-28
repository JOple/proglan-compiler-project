package com.mosj.ic.value;

import java.io.Serializable;

public class JsonVal implements Value {

	private static final long serialVersionUID = -3909952625995277479L;

	private Serializable val;

	@Override
	public Serializable get() {
		return val;
	}
	@Override
	public void set(Serializable val) {
		if(!check(val))
			throw new IllegalArgumentException("Accepted values are limited to null, boolean, character, number, and string: " + val);

		if(!(val instanceof CharSequence)) {
			this.val = val;
			return;
		}

		String s = val.toString();
		String ls = s.toLowerCase();
		
		if("null".equals(ls))
			this.val = null;
		else if("true".equals(ls))
			this.val = true;
		else if("false".equals(ls))
			this.val = false;
		else if(s.length() == 3 && s.startsWith("\'") && s.endsWith("\'"))
			this.val = s.charAt(1);
		else if(s.length() > 2  && s.startsWith("\"") && s.startsWith("\""))
			this.val = s.substring(1, s.length() - 1);
		else if(s.matches(".*[ \b\t\n\f\r].*"))
			this.val = val;
		else {
			try {
				this.val = Byte.parseByte(s);
				return;
			} catch(NumberFormatException e) { }
			try {
				this.val = Short.parseShort(s);
				return;
			} catch(NumberFormatException e){ }
			try {
				this.val = Integer.parseInt(s);
				return;
			} catch(NumberFormatException e) { }
			try {
				this.val = Long.parseLong(s);
				return;
			} catch(NumberFormatException e) { }
			try {
				float f = Float.parseFloat(s);
				if(!Float.isInfinite(f)) {
					this.val = f;
					return;
				}
			} catch(NumberFormatException e) { }
			try {
				this.val = Double.parseDouble(s);
				return;
			} catch(NumberFormatException e) { }
			this.val = s;
		}
	}
	@Override
	public boolean isNull() {
		return val == null;
	}
	@Override
	public boolean isBool() {
		return val instanceof Boolean;
	}
	@Override
	public boolean isChar() {
		return val instanceof Character;
	}
	@Override
	public boolean isByte() {
		return val instanceof Byte;
	}
	@Override
	public boolean isShort() {
		return val instanceof Short;
	}
	@Override
	public boolean isInt() {
		return val instanceof Integer;
	}
	@Override
	public boolean isLong() {
		return val instanceof Long;
	}
	@Override
	public boolean isFloat() {
		return val instanceof Float;
	}
	@Override
	public boolean isDouble() {
		return val instanceof Double;
	}
	@Override
	public boolean isIntegral() {
		return isLong() || isInt() || isShort() || isByte();
	}
	@Override
	public boolean isDecimal() {
		return isDouble() || isFloat();
	}
	@Override
	public boolean isNumber() {
		return isDecimal() || isIntegral();
	}
	@Override
	public boolean isPrimitive() {
		return isBool() || isChar() || isNumber();
	}
	@Override
	public boolean isString() {
		return val instanceof CharSequence;
	}

	@Override
	public Boolean asBool() {
		if(isNull())
			return false;
		if(isBool())
			return (Boolean) val;
		if(isNumber())
			return asDouble() != 0;
		if(isString() && "false".equals(val))
			return false;
		return true;
	}
	@Override
	public Character asChar() {
		if(isNull())
			return '\0';
		if(isBool())
			return asBool() ? 't' : 'f';
		if(isNumber())
			return (char) asInt().intValue();
		if(isString() && asString().length() > 0)
			return asString().charAt(0);
		return '\0';
	}
	@Override
	public Byte asByte() {
		return asNumber().byteValue();
	}
	@Override
	public Short asShort() {
		return asNumber().shortValue();
	}
	@Override
	public Integer asInt() {
		return asNumber().intValue();
	}
	@Override
	public Long asLong() {
		return asNumber().longValue();
	}
	@Override
	public Float asFloat() {
		return asNumber().floatValue();
	}
	@Override
	public Double asDouble() {
		return asNumber().doubleValue();
	}
	@Override
	public Number asNumber() {
		if(isNull())
			return 0;
		if(isBool())
			return asBool() ? 1 : 0;
		if(isChar())
			return (int) asChar().charValue();
		if(isNumber())
			return (Number) val;
		try {
			return Double.parseDouble(val.toString());
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	@Override
	public String asString() {
		return val + "";
	}

	@Override
	public Value copy() {
		try {
			Value v = getClass().newInstance();
			v.set(get());
			return v;
		} catch (Exception e) {
			throw new UnsupportedOperationException("A default constructor must be exposed for " + getClass().getName());
		}
	}

	private boolean check(Serializable val) {
		if(val == null)
			return true;
		if(val instanceof Boolean)
			return true;
		if(val instanceof Character)
			return true;
		if(val instanceof Number)
			return true;
		if(val instanceof CharSequence)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		if(isString())
			return "\"" + val + "\"";
		if(isChar())
			return "\'" + val + "\'";
		if(isLong())
			return val + "L";
		if(isFloat())
			return val + "F";
		if(isDouble())
			return val + "D";
		return val + "";
	}
}
