package com.mosj.ic.editor.modules;

import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;

import com.mosj.ic.compiler.CodeCompilation;
import com.mosj.ic.compiler.CodeCompiler;
import com.mosj.ic.compiler.Co2ErrorHandler;
import com.mosj.ic.compiler.Co2ErrorListener;
import com.mosj.ic.editor.StandardEditorModule;
import com.mosj.ic.grammar.Co2pNLexer;
import com.mosj.ic.grammar.Co2pNParser;
import com.mosj.ic.grammar.Co2pNParser.CompilationUnitContext;
import com.mosj.ic.interpreter.ProgramRunner;
import com.mosj.ic.interpreter.StandardProgramRunner;
import com.mosj.ic.log.Log;

public class CompilerModule extends StandardEditorModule {

	private UIModule ui;
	private IOModule io;

	private ProgramRunner runner;
	
	private CodeCompilation code;
	private Thread currentRunning;

	@Override
	public void run() {
		ui = getModule(UIModule.class);
		io = getModule(IOModule.class);

		//		runner = new StandardProgramRunner(io.getInReader(), io.getOutWriter(), System.err);
//		runner = new StandardProgramRunner(io.getInputStream(), io.getOutputStream(), System.err);
		runner = new StandardProgramRunner(System.in, System.out, System.err);
		io.getEditor();
	}

	public void compileCode() {
		String text = ui.getTextEditor().getText();

		Co2ErrorListener errorListener = new Co2ErrorListener();
		Co2ErrorHandler errorHandler = new Co2ErrorHandler();

		Co2pNLexer lexer = new Co2pNLexer(CharStreams.fromString(text));
		lexer.addErrorListener(errorListener);

		Co2pNParser parser = new Co2pNParser(new BufferedTokenStream(lexer));
		//		parser.removeErrorListeners();
		parser.setErrorHandler(errorHandler);
		parser.addErrorListener(errorListener);

		CompilationUnitContext ctx = parser.compilationUnit();
		System.out.println(ctx.toStringTree(parser));

		LinkedList<Log> errorLogs = new LinkedList<>();
		errorLogs.addAll(errorListener.logs());

		if(errorLogs.isEmpty()) {
			CodeCompilation cpl = CodeCompiler.compile("main", ctx);

			for(String n : cpl.asProgram().getFunctions().keySet()) {
				System.out.println("function " + n);
				System.out.println(String.join("\n", cpl
						.asProgram()
						.getFunctions()
						.get(n)
						.getOperations()
						.stream()
						.map(o -> "\t" + o.toString())
						.collect(Collectors.toList())));
			}

			errorLogs.addAll(cpl.logs());

			code = errorLogs.isEmpty() ? cpl : null;
		}

		String errors = errorLogs.isEmpty() ? "Result: success" : String.join("\n", errorLogs
				.stream()
				.map(l -> l.toString())
				.collect(Collectors.toList()));
		ui.getConsole().append(
				"\n---- Compilation ---\n" +
				errors + "\n");
		
		System.out.println(errors);

		code = errorLogs.isEmpty() ? code : null;
	}
	public void runCode() {
		if(currentRunning != null && currentRunning.isAlive())
			currentRunning.interrupt();

		if(code != null) {
			ui.getConsole().append("\n---- Program Start ---\n");
			currentRunning = new Thread(() -> runner.start(code.asProgram()));
			currentRunning.start();
		} else
			JOptionPane.showMessageDialog(ui, "Not yet compiled", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public void compileRunCode() {
		compileCode();
		runCode();
	}
}
