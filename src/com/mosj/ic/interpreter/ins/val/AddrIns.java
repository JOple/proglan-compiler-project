package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class AddrIns extends Op2Instruction {

	public AddrIns() {
		super(InsType.ADDR);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.getVal(i1).set(c.getAddr(i2));
	}
}
