package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;

public class AssignIns extends Op2Instruction {

	public AssignIns() {
		super(InsType.ASSIGN);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.setAddr(i1, c.getAddr(i2));
	}
}
