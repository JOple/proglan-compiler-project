package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class SpaceIns extends Op2Instruction {

	public SpaceIns() {
		super(InsType.SPACE);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		long size = c.getVal(i2).asLong();
		c.getVal(i1).set(c.getAddr());
		for(int i = 1; i < size; i++)
			c.getAddr();
	}
}
