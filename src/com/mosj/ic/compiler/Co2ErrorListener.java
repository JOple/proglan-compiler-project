package com.mosj.ic.compiler;

import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import com.mosj.ic.log.Log;

public class Co2ErrorListener implements ANTLRErrorListener {

	CodeLogger log = CodeLogger.create(Co2ErrorListener.class);
	
	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
//		log.ambiguity(startIndex, stopIndex, recognizer.getCurrentToken().getText());
	}
	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
//		log.sslConflic(startIndex, stopIndex, recognizer.getCurrentToken().getText());
	}
	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
//		log.ctxSensitivity(startIndex, stopIndex, recognizer.getCurrentToken().getText());
	}
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		log.syntaxErr(line, charPositionInLine, msg);
	}

	public List<Log> logs() {
		return log.logs();
	}
}
