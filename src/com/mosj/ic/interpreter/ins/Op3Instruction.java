package com.mosj.ic.interpreter.ins;

import java.util.List;

import com.mosj.ic.interpreter.StackFrameExt;

public abstract class Op3Instruction extends StandardInstruction {

	public Op3Instruction(InsType op) {
		super(op);
	}

	public abstract void logic(StackFrameExt c, String i1, String i2, String i3);

	@Override
	public void logic(StackFrameExt c, List<String> i) {
		logic(c, i.get(0), i.get(1), i.get(2));
	}
}
