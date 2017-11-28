package com.mosj.ic.interpreter.func;

import com.mosj.ic.definition.FunctionDefinition;
import com.mosj.ic.definition.OperationDefinition;
import com.mosj.ic.interpreter.Reg;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.value.ValueType;

public class ScanFunction extends FunctionDefinition {

	private static final long serialVersionUID = -9056814383564013920L;
	
	public static final ScanFunction INSTANCE = new ScanFunction();
	
	public ScanFunction() {
		setName(InsType.SCAN.getName());
		setReturnType(ValueType.OBJECT.getId());
		getOperations().add(new OperationDefinition(InsType.SCAN.getName(), Reg.R_V0));
		getOperations().add(new OperationDefinition(InsType.RET.getName(), Reg.R_V0));
	}
}
