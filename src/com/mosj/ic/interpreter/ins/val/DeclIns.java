package com.mosj.ic.interpreter.ins.val;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op2Instruction;
import com.mosj.ic.value.ValueType;

public class DeclIns extends Op2Instruction {

	public DeclIns() {
		super(InsType.DECL);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2) {
		c.setType(i1, ValueType.of(i2));
	}
}
