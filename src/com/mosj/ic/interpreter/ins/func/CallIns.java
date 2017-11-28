package com.mosj.ic.interpreter.ins.func;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.StandardStackFrame;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class CallIns extends Op1Instruction {

	public CallIns() {
		super(InsType.CALL);
	}

	@Override
	public void logic(StackFrameExt c, String i1) {
		c.getStackTrace().push(new StandardStackFrame(i1, c.getStackTrace().peek()));
	}
}
