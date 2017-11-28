package com.mosj.ic.interpreter.ins.ctrl;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op0Instruction;

public class TrapIns extends Op0Instruction {

	public TrapIns() {
		super(InsType.TRAP);
	}

	@Override
	public void logic(StackFrameExt c) {
		c.setVal(R_TRAP, true);
	}
}
