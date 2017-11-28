package com.mosj.ic.interpreter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import com.mosj.ic.definition.FunctionDefinition;
import com.mosj.ic.definition.OperationDefinition;
import com.mosj.ic.definition.ProgramDefinition;
import com.mosj.ic.definition.VariableDefinition;
import com.mosj.ic.interpreter.ins.InsType;
import com.mosj.ic.value.Value;
import com.mosj.ic.value.ValueType;
import com.mosj.ic.value.Values;

public class StandardProgramRunner implements ProgramRunner, Reg {

	public static final String MAIN_FUNCTION_NAME = "main";

	private  Map<String, Instruction> instructionSet = new Hashtable<>();;

	private InputStream inputStream;
	private OutputStream outputStream;
	private OutputStream errorStream;
	
	public StandardProgramRunner(InputStream in, OutputStream out, OutputStream err) {
		for(InsType ins : InsType.values())
			instructionSet.put(ins.getName(), ins.getInstruction());
		this.inputStream = in == null ? System.in : in;
		this.outputStream = in == null ? System.out : out;
		this.errorStream = err == null ? System.err : err;
	}

	@Override
	public void run(ProgramDefinition in) throws Exception {
		ProgramInformation info = new StandardProgramInformation(in, this);
		IOProvider io = new StandardIOProvider(inputStream, outputStream, errorStream);
		Stack<StackFrame> stackTrace = new Stack<>();

		Map<Long, Value> heap = new LinkedHashMap<>();
		Map<String, Long> regAliases = new LinkedHashMap<>();

		long regAddr = 0;
		for(Entry<String, Value> r : Reg.registers().entrySet()) {
			heap.put(regAddr, r.getValue());
			regAliases.put(r.getKey(), regAddr);
			regAddr++;
		}

		StackFrame main = new StandardStackFrame(MAIN_FUNCTION_NAME, info, io, stackTrace, heap);
		stackTrace.push(main);
		
		System.out.println("Program Starting");

		while(!stackTrace.isEmpty()) {
			
			if(Thread.interrupted())
				break;
			
			StackFrameExt top = StackFrameExt.of(stackTrace.peek());

			FunctionDefinition func = in.getFunctions().get(top.getFunctionName());

			if(!top.getAliases().containsKey(INS)) {
//				System.out.println("Function " + func.getName() + " initialization");

				top.getAliases().putAll(regAliases);
				top.setType(INS, ValueType.INT).setVal(INS, 0);

				int size = func.getParameters().size();
				for(int i = 0; i < size; i++) {
					VariableDefinition v = func.getParameters().get(i);
					String name = v.getName();
					top
					.setType(name, ValueType.of(v.getType()))
					.setVal(name, top.getVal(ARRAY_R_AXX[i]).copy());
				}
				
				size = func.getOperations().size();
				for(int i = 0; i < size; i++) {
					OperationDefinition o = func.getOperations().get(i);
					if(InsType.LABEL.getName().equals(o.getOp()))
						top.setType(o.getArgs().get(0), Values.ofConst(i));
				}
			}

			int ins = top.getVal(INS).asInt();
			top.getVal(INS).set(ins + 1);

			OperationDefinition op = func.getOperations().get(ins);
			getInstructionSet().get(op.getOp()).process(top.getBasis(), op.getArgs());
			
			/*System.out.print(top.getFunctionName() + ":" + ins + "\t" + op + " = ");
			System.out.println(String.join(",", op.getArgs().stream().map(a -> top.getVal(a).toString()).collect(Collectors.toList())));
			
			System.out.println("Aliases: " + top.getAliases().size());
			System.out.println("Values: " + top.getValues().size());
			
			System.out.println("Addr of A00: " + top.getAddr(R_A00));*/
			
			while(top.getVal(R_TRAP).asBool() && !top.getVal(R_DONE).asBool()) {
				try {
					Thread.sleep(200);
				} catch(InterruptedException e) {
					break;
				}
			}

			if(top.getVal(R_DONE).asBool())
				break;
		}
	}

	@Override
	public Map<String, Instruction> getInstructionSet() {
		return instructionSet;
	}
	public void setInstructionSet(Map<String, Instruction> instructionSet) {
		this.instructionSet = instructionSet;
	}
}
