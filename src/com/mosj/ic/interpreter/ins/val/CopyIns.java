package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class CopyIns extends Op2Instruction {

	public CopyIns() {
		super(InsType.COPY);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.getVal(i1).set(c.getVal(i2).get());
	}
}
