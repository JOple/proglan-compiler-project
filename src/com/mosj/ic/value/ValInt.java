package com.mosj.ic.value;

import java.io.Serializable;

public class ValInt extends JsonVal {

	private static final long serialVersionUID = 4376646482167261158L;

	@Override
	public void set(Serializable val) {
		super.set(val);
		super.set(asLong());
	}
}
