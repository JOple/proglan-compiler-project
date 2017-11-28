package com.mosj.ic.interpreter.func;

import com.mosj.ic.definition.FunctionDefinition;
import com.mosj.ic.definition.OperationDefinition;
import com.mosj.ic.definition.VariableDefinition;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.value.ValueType;

public class PrintFunction extends FunctionDefinition {

	private static final long serialVersionUID = 2158759893944672839L;

	public static final PrintFunction INSTANCE = new PrintFunction();
	
	public PrintFunction() {
		setName(InsType.PRINT.getName());
		setReturnType(ValueType.VOID.getId());
		getParameters().add(new VariableDefinition("msg", ValueType.OBJECT));
		getOperations().add(new OperationDefinition(InsType.PRINT.getName(), "msg"));
		getOperations().add(new OperationDefinition(InsType.RET.getName(), "0"));
	}
}
