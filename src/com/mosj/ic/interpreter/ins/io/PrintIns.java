package com.mosj.ic.interpreter.ins.io;

import java.io.IOException;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class PrintIns extends Op1Instruction {

	public PrintIns() {
		super(InsType.PRINT);
	}

	@Override
	public void logic(StackFrameExt c, String i1) {
		/*try {
			c.getIO().getOutputStream().write(c.getVal(i1).asString().getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Cannot write to output stream", e);
		}*/
		System.out.print(c.getVal(i1).asString());
	}
}
