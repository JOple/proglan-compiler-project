package com.mosj.ic.interpreter.ins.bool;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op3Instruction;

public class AndIns extends Op3Instruction {

	public AndIns() {
		super(InsType.AND);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2, String i3) {
		c.setVal(i1, c.getVal(i2).asBool() && c.getVal(i3).asBool());
	}
}
