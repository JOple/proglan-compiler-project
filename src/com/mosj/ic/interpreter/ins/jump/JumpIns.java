package com.mosj.ic.interpreter.ins.jump;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class JumpIns extends Op1Instruction {

	public JumpIns() {
		super(InsType.JUMP);
	}

	@Override
	public void logic(StackFrameExt c, String i1) {
		c.setVal(INS, c.getVal(i1));
	}
}
