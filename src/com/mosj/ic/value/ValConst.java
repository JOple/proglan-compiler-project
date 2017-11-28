package com.mosj.ic.value;

import java.io.Serializable;

public class ValConst extends JsonVal {
	
	private static final long serialVersionUID = 8842711222691893826L;

	private boolean isSet = false;
	
	@Override
	public void set(Serializable val) {
		if(isSet)
			return;
		super.set(val);
	}
	
	public void unset() {
		isSet = false;
	}

	public boolean isSet() {
		return isSet;
	}
}
