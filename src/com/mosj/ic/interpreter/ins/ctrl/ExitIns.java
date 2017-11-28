package com.mosj.ic.interpreter.ins.ctrl;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class ExitIns extends Op1Instruction {

	public ExitIns() {
		super(InsType.EXIT);
	}

	@Override
	public void logic(StackFrameExt c, String i1) {
		c.setVal(R_DONE, true);
	}
}
