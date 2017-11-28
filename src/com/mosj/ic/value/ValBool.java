package com.mosj.ic.value;

import java.io.Serializable;

public class ValBool extends JsonVal {

	private static final long serialVersionUID = 8719563307177242516L;

	@Override
	public void set(Serializable val) {
		super.set(val);
		super.set(asBool());
	}
}
