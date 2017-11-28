package com.mosj.ic.driver;

import com.mosj.ic.interpreter.ins.InsType;

public class AutoIns {

	public static final String dest = "src";
	public static final String pkg = "com.mosj.ic.interpreter";
	
	public static final String template =
					"package " + pkg + ".ins.%s;\n\n" +
					"import " + pkg + ".StackFrameExt;\n" +
					"import " + pkg + ".ins.Op%sInstruction;\n" +
					"import com.mosj.ic.utils.OpType;\n\n" +
					"public class %sIns extends Op%sInstruction {\n\n" +
					"\tpublic %sIns() {\n" +
					"\t\tsuper(OpType.%s);\n" +
					"\t}\n\n" +
					"\t@Override\n" +
					"\tpublic void logic(StackFrameExt c%s) {\n" +
					"\t\t%s\n\t}\n}";
	
	public static void main(String[] args) {
		String fdest = dest + "/" + pkg.replace('.', '/') + "/ins";
		for(InsType o : InsType.values()) {
			String params = "";
			for(int i = 1; i <= o.getParamCount(); i++)
				params += ", String i" + i;
			
			String body = "";
			if(o.getParamCount() == 3) {
				if(o.getType().equals("math") || o.getName().toLowerCase().startsWith("gt") || o.getName().toLowerCase().startsWith("lt"))
				body = "c.setVal(i1, c.getVal(i2).asDouble() " + o.getSymbol() + " c.getVal(i3).asDouble());";
				else if(o.getType().equals("bool"))
				body = "c.setVal(i1, c.getVal(i2).asBool() " + o.getSymbol() + " c.getVal(i3).asBool());";
			}
			
			String s = String.format(template,
					o.getType(),
					o.getParamCount(),
					capF(o + ""),
					o.getParamCount(),
					capF(o + ""),
					o,
					params,
					body);

			String d = fdest + "/" + o.getType() + "/" + capF(o + "") + "Ins.java";
			
			System.out.println(d);
			System.out.println(s);
			/*try {
				Files.write(Paths.get(d), Arrays.asList(s.split("\n")), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	public static String capF(String s) {
		return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
	}
}
