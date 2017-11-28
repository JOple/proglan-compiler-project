package com.mosj.ic.interpreter;

public interface Processor<Ctx, In> {

	String getName();
	void process(Ctx c, In i);
}
