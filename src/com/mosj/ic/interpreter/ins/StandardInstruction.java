package com.mosj.ic.interpreter.ins;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mosj.ic.interpreter.Instruction;
import com.mosj.ic.interpreter.Reg;
import com.mosj.ic.interpreter.StackFrame;
import com.mosj.ic.interpreter.StackFrameExt;

public abstract class StandardInstruction implements Instruction, Reg {

	private InsType type;

	public StandardInstruction(InsType op) {
		Objects.requireNonNull(op, "InsType cannot be null");
		this.type = op;
	}

	@Override
	public String getName() {
		return type.getName();
	}
	public int getParamCount() {
		return type.getParamCount();
	}

	public abstract void logic(StackFrameExt c, List<String> i);

	public void checkArgs(StackFrame c, List<String> i) {
		Objects.requireNonNull(c, "Context cannot be null");
		Objects.requireNonNull(i, "Input cannot be null");
		if(i.size() < getParamCount())
			throw new IllegalArgumentException("Instruction '" + getName() + "' needs at least " + getParamCount() + " parameters, provided are (" + String.join(",", i) + ")");
	}

	@Override
	public void process(StackFrame c, List<String> i) {
		checkArgs(c, i);
		logic(StackFrameExt.of(c), i);
		/*System.out.println(getClass().getSimpleName().replace("Ins", "") + "\t(" + String.join(",", i) + ")\t\t");
		StackFrameExt ext = StackFrameExt.of(c);
		System.out.println("\t" + String.join(",", i
				.stream()
				.map(s -> s + "=" + ext.getVal(s).asString().replaceAll("[\n\r]", " "))
				.collect(Collectors.toList())));*/
	}

	@Override
	public String toString() {
		return getName() + "(" + getParamCount() + ")";
	}
}
