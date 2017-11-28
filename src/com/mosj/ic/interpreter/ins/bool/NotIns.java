package com.mosj.ic.interpreter.ins.bool;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class NotIns extends Op2Instruction {

	public NotIns() {
		super(InsType.NOT);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.setVal(i1, !c.getVal(i2).asBool());
	}
}
