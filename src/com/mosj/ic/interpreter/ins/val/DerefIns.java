package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class DerefIns extends Op2Instruction {

	public DerefIns() {
		super(InsType.DEREF);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.setAddr(i1, c.getVal(i2).asLong());
	}
}
