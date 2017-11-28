package com.mosj.ic.interpreter;

import java.util.Arrays;
import java.util.List;

public interface Instruction extends Processor<StackFrame, List<String>> {

	default void op(StackFrame c, String... i) {
		op(c, Arrays.asList(i));
	}
	default void op(StackFrame c, List<String> i) {
		process(c, i);
	}
}
