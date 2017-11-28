package com.mosj.ic.value;

import java.io.Serializable;

public class ValNull extends JsonVal {

	private static final long serialVersionUID = -7019690862733928220L;

	@Override
	public void set(Serializable val) {
		super.set(null);
	}
}
