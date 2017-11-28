package com.mosj.ic.interpreter.ins;

import java.util.List;

import com.mosj.ic.interpreter.StackFrameExt;

public abstract class Op1Instruction extends StandardInstruction {

	public Op1Instruction(InsType op) {
		super(op);
	}

	public abstract void logic(StackFrameExt c, String i1);

	@Override
	public void logic(StackFrameExt c, List<String> i) {
		logic(c, i.get(0));
	}
}
