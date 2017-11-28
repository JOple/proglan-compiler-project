package com.mosj.ic.interpreter;

import com.mosj.ic.definition.ProgramDefinition;
import com.mosj.ic.interpreter.ProgramInformation;
import com.mosj.ic.interpreter.ProgramRunner;

public class StandardProgramInformation implements ProgramInformation {

	private ProgramDefinition definition;
	private ProgramRunner runner;

	public StandardProgramInformation() { }
	public StandardProgramInformation(ProgramDefinition definition, ProgramRunner runner) {
		this();
		this.definition = definition;
		this.runner = runner;
	}

	@Override
	public ProgramDefinition getDefinition() {
		return definition;
	}
	public void setDefinition(ProgramDefinition definition) {
		this.definition = definition;
	}

	@Override
	public ProgramRunner getRunner() {
		return runner;
	}
	public void setRunner(ProgramRunner runner) {
		this.runner = runner;
	}
}
