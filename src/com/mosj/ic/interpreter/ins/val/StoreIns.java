package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class StoreIns extends Op2Instruction {

	public StoreIns() {
		super(InsType.STORE);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.getVal(i1).set(i2
				.replace("\\b", "\b")
				.replace("\\t", "\t")
				.replace("\\n", "\n")
				.replace("\\f", "\f")
				.replace("\\r", "\r")
				.replace("\\\"", "\"")
				.replace("\\\'", "\'")
				.replace("\\\\", "\\"));
	}
}
