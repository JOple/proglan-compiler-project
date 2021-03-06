package com.mosj.ic.interpreter.ins.math;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op3Instruction;

public class AddIns extends Op3Instruction {

	public AddIns() {
		super(InsType.ADD);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2, String i3) {
		if(c.getVal(i2).isString() || c.getVal(i3).isString())
			c.getVal(i1).set(c.getVal(i2).asString() + c.getVal(i3).asString());
		else if((c.getVal(i2).isInt() || c.getVal(i2).isLong()) && (c.getVal(i3).isInt() || c.getVal(i3).isLong()))
			c.getVal(i1).set(c.getVal(i2).asLong() + c.getVal(i3).asLong());
		else
			c.getVal(i1).set(c.getVal(i2).asDouble() + c.getVal(i3).asDouble());
	}
}
