package com.mosj.ic.value;

import java.io.Serializable;

public class ValFloat extends JsonVal {

	private static final long serialVersionUID = 3324062141198523863L;

	@Override
	public void set(Serializable val) {
		super.set(val);
		super.set(asDouble());
	}
}
