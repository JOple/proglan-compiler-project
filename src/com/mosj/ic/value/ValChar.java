package com.mosj.ic.value;

import java.io.Serializable;

public class ValChar extends JsonVal {

	private static final long serialVersionUID = 4594882376440011717L;

	@Override
	public void set(Serializable val) {
		super.set(val);
		super.set(asChar());
	}
}
