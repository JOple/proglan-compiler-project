package com.mosj.ic.interpreter;

import java.util.Map;

import com.mosj.ic.definition.ProgramDefinition;

public interface ProgramRunner extends Runner<ProgramDefinition> {

	Map<String, Instruction> getInstructionSet();
	default Instruction ins(String name) {
		return getInstructionSet().get(name);
	}
	
	default void handleError(ProgramDefinition in, Exception e) {
		e.printStackTrace();
	}
	default void start(ProgramDefinition in) {
		try {
			run(in);
		} catch (Exception e) {
			handleError(in, e);
		}
	}
}
