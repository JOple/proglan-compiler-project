package com.mosj.ic.interpreter.ins.bool;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op3Instruction;

public class EqIns extends Op3Instruction {

	public EqIns() {
		super(InsType.EQ);
	}

	@Override
	public void logic(StackFrameExt c, String i1, String i2, String i3) {
		if(c.getVal(i2).isString() && c.getVal(i3).isString())
			c.setVal(i1, c.getVal(i2).asString().equals(c.getVal(i3).asString()));
		else
			c.setVal(i1, c.getVal(i2).asDouble().doubleValue() == c.getVal(i3).asDouble().doubleValue());
	}
}
