package com.mosj.ic.compiler;

import org.antlr.v4.runtime.*;

public class Co2ErrorHandler extends DefaultErrorStrategy {
	
	@Override
	protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
//		String msg = "No Viable Alternative Input: " + getTokenErrorDisplay(e.getOffendingToken()) + " expecting " + Arrays.asList(recognizer.getRuleNames()).get(e.getCtx().getRuleIndex());
		String msg = "No Viable Alternative for " + getTokenErrorDisplay(e.getOffendingToken());
//		System.out.println("Missing Symbol: " + recognizer.get);
		
		System.out.println( "Current Token: " + recognizer.getCurrentToken());
		
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
	}
	
	@Override
	protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
		String msg = "Mismatched Input: " + getTokenErrorDisplay(e.getOffendingToken()) + " expecting: "/* + this.getMissingSymbol(recognizer).getText()*/;
		
//		System.out.println("Expected: ");
		for(int i = 0; i < e.getExpectedTokens().size(); i++) {
//			 System.out.println(recognizer.getVocabulary().getLiteralName(e.getExpectedTokens().get(i)));
			if(recognizer.getVocabulary().getLiteralName(e.getExpectedTokens().get(i)) != null)
				msg += recognizer.getVocabulary().getLiteralName(e.getExpectedTokens().get(i)) + " ";
			else
				msg += recognizer.getVocabulary().getSymbolicName(e.getExpectedTokens().get(i)) + " ";
		}
		
		System.out.println("Expecting: " + recognizer.getATN().getExpectedTokens(e.getOffendingState(), e.getCtx()));
		
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
	}
	
	@Override
	protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
		String msg = "Failed Predicate Error";
		
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
	}
	
	@Override
	protected void reportUnwantedToken(Parser recognizer) {
		String msg = "Extraneous Input Error: " + "'" + recognizer.getCurrentToken().getText() + "'" + "; Delete this token!";
		
		recognizer.notifyErrorListeners(msg);
	}
	
	@Override
	protected void reportMissingToken(Parser recognizer) {
		String msg = "Missing Input" + " expecting: ";
		
		for(int i = 0; i < recognizer.getExpectedTokens().size(); i++) {
//			 System.out.println(recognizer.getVocabulary().getLiteralName(e.getExpectedTokens().get(i)));
			msg += recognizer.getVocabulary().getLiteralName(recognizer.getExpectedTokens().get(i)) + " ";
		}
		
		recognizer.notifyErrorListeners(msg);
	}
}
