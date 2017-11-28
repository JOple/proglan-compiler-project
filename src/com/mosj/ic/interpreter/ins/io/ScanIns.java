package com.mosj.ic.interpreter.ins.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.mosj.ic.interpreter.StackFrameExt;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.interpreter.ins.Op1Instruction;

public class ScanIns extends Op1Instruction {

	public ScanIns() {
		super(InsType.SCAN);
	}

	@Override
	public void logic(StackFrameExt c, String i1) {
		/*StringBuffer buf = new StringBuffer();
		InputStream in = c.getIO().getInputStream();
		
		try {
			int b = in.read();
			while(true) {
				char a = (char) b;
				buf.append(a);
				if(a == '\n')
					break;
				if(b != -1)
					b = in.read();
			}
		} catch (IOException e) {
			throw new RuntimeException("Cannot read from input stream", e);
		}
		c.setVal(i1, buf.toString());*/
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		c.setVal(i1, sc.nextLine());
	}
}
