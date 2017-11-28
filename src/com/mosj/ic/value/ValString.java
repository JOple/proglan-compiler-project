package com.mosj.ic.value;

import java.io.Serializable;

public class ValString extends JsonVal {

	private static final long serialVersionUID = 3129488338012219059L;

	@Override
	public void set(Serializable val) {
		super.set(val);
		super.set("\"" + asString() + "\"");
	}
}
