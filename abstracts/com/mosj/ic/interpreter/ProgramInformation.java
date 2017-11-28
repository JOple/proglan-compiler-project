package com.mosj.ic.interpreter;

import com.mosj.ic.definition.ProgramDefinition;

public interface ProgramInformation {

	ProgramDefinition getDefinition();
	ProgramRunner getRunner();
}
