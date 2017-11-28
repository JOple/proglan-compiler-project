package com.mosj.ic.interpreter.ins.jump;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class JumpfIns extends Op2Instruction {

	public JumpfIns() {
		super(InsType.JUMPF);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		if(!c.getVal(i2).asBool())
			c.setVal(INS, c.getVal(i1));
	}
}
