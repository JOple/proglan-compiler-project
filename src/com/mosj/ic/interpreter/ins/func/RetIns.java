package com.mosj.ic.interpreter.ins.func;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class RetIns extends Op1Instruction {

	public RetIns() {
		super(InsType.RET);
	}

	@Override
	public void logic(StackFrameExt c, String a1) {
		c.getStackTrace().pop();
		if(!c.getStackTrace().isEmpty())
			StackFrameExt.of(c.getStackTrace().peek()).setVal(R_V0, c.getVal(a1));
	}
}
