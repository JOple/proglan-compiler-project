package com.mosj.ic.interpreter.ins;

import java.util.List;

import com.mosj.ic.interpreter.StackFrameExt;

public abstract class Op0Instruction extends StandardInstruction {

	public Op0Instruction(InsType op) {
		super(op);
	}

	public abstract void logic(StackFrameExt c);
	
	@Override
	public void logic(StackFrameExt c, List<String> i) {
		logic(c);
	}
}
