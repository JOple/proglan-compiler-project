package com.mosj.ic.interpreter.ins.ctrl;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class LabelIns extends Op1Instruction {

	public LabelIns() {
		super(InsType.LABEL);
	}

	@Override
	public void logic(StackFrameExt c, String i1) { }
}
