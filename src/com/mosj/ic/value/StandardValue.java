package com.mosj.ic.value;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mosj.ic.value.Value;

@Deprecated
public class StandardValue implements Value {

	private static final long serialVersionUID = -5376326041979233180L;

	public static final Gson JSON = new Gson();

	private String val;
	private transient boolean hasChanges;
	private transient JsonElement json;

	@Override
	public Serializable get() {
		if(hasChanges || json == null) {
			try {
				json = JSON.fromJson(val + "", JsonElement.class);
			} catch(Exception e) {
				json = JSON.fromJson("\"" + val + "\"", JsonElement.class);
			}
			val = json.toString();
		}
		return val;
	}
	@Override
	public void set(Serializable val) {
		this.val = val + "";
		hasChanges = true;
		get();
		if(json.isJsonArray() || json.isJsonObject())
			throw new IllegalArgumentException("The value must be a primitive or string: " + json);
	}

	/*protected JsonElement getJson() {
		get();
		return json;
	}
	protected void setJson(JsonElement json) {
		if(json == null)
			json = JsonNull.INSTANCE;
		if(json.isJsonArray() || json.isJsonObject())
			throw new IllegalArgumentException("The value must be a primitive or string: " + json);

		this.json = json;
		this.val = json.toString();
	}*/

	@Override
	public boolean isNull() {
		get();
		return json.isJsonNull();
	}
	@Override
	public boolean isPrimitive() {
		get();
		return !isNull() && !isString() && json.isJsonPrimitive();
	}
	@Override
	public boolean isString() {
		get();
		String str = json.toString();
		return !isNull() && (str.startsWith("\"") || "true".equals(str) || "false".equals(str));
	}

	@Override
	public Boolean asBool() {
		get();
		if(isNull())
			return false;
		try {
			return Double.parseDouble(asString()) != 0;
		} catch (NumberFormatException e) {
			return json.getAsBoolean();
		}
	}
	@Override
	public Byte asByte() {
		get();
		return asDouble().byteValue();
	}
	@Override
	public Character asChar() {
		get();
		return (char) asDouble().intValue();
	}
	@Override
	public Short asShort() {
		get();
		return asDouble().shortValue();
	}
	@Override
	public Integer asInt() {
		get();
		return asDouble().intValue();
	}
	@Override
	public Long asLong() {
		get();
		return asDouble().longValue();
	}
	@Override
	public Float asFloat() {
		get();
		return asDouble().floatValue();
	}
	@Override
	public Double asDouble() {
		get();
		if(isNull())
			return 0D;
		try {
			return json.getAsDouble();
		} catch (Exception e) {
			if("true".equals(json.toString()))
				return 1D;
			return 0D;
		}
	}
	@Override
	public String asString() {
		get();
		try {
			return json.getAsString();
		} catch(Exception e) {
			return json.toString();
		}
	}

	@Override
	public Value copy() {
		get();
		try {
			StandardValue v = getClass().newInstance();
			v.set(get());
			return v;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("There is no exposed default constructor for value", e);
		}
	}

	@Override
	public String toString() {
		get();
		return getClass().getSimpleName() + "[" + asString() + "]";
	}
	@Override
	public boolean isBool() {
		return false;
	}
	@Override
	public boolean isChar() {
		return false;
	}
	@Override
	public boolean isByte() {
		return false;
	}
	@Override
	public boolean isShort() {
		return false;
	}
	@Override
	public boolean isInt() {
		return false;
	}
	@Override
	public boolean isLong() {
		return false;
	}
	@Override
	public boolean isFloat() {
		return false;
	}
	@Override
	public boolean isDouble() {
		return false;
	}
	@Override
	public Number asNumber() {
		return null;
	}
	@Override
	public boolean isIntegral() {
		return false;
	}
	@Override
	public boolean isDecimal() {
		return false;
	}
	@Override
	public boolean isNumber() {
		return false;
	}
}
