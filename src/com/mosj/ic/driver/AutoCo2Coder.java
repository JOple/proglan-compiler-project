package com.mosj.ic.driver;

import com.mosj.ic.interpreter.ins.InsType;

public class AutoCo2Coder {

	public static final String template =
					"/**\n" +
					" * %s\n" +
					"%s" +
					" * @return An operation definition\n" +
					" */\n" +
					"public Co2CodeDef %s(%s) {\n" +
					"\treturn op(OpType.%s%s);\n" +
					"}";
	public static void main(String[] args) {
		
		for(InsType d : InsType.values()) {
			String docParams = "", params = "", opParams = "";
			int pc = d.getParamCount();
			if(pc > 0) {
				docParams = " * @param t Target (arg0)\n";
				params = "Co2CodeDef t";
				opParams = ", t(t)";
			}
			if(pc > 1) {
				docParams = " * @param a Operand (actual arg1)\n"
						+ " * @param t Target (actual arg0)\n";
				params = "Co2CodeDef a, Co2CodeDef t";
				opParams = ", t(t), t(a)";
			}
			if(pc > 2) {
				docParams = " * @param a1 Operand 1 (actual arg1)\n"
						+ " * @param a2 Operand 2 (actual arg2)\n"
						+ " * @param t Target (actual arg0)\n";
				params = "Co2CodeDef a1, Co2CodeDef a2, Co2CodeDef t";
				opParams = ", t(t), t(a1), t(a2)";
			}
			
			String out = String.format(template,
					d.getDescription(),
					docParams,
					d.getName(),
					params,
					d.toString(),
					opParams);
			System.out.println(out);
		}
	}
}
