package com.mosj.ic.driver;

import com.mosj.ic.grammar.Co2pNParser;

public class AutoFunction {

	public static void main(String[] args) {
		for(Class<?> a : Co2pNParser.class.getDeclaredClasses()) {
			String name = a.getSimpleName();
			String fname = name.toLowerCase().charAt(0) + name.substring(1);
			System.out.println("public void " + fname + "(" + name + " i" + ") {\n\t//TODO\n}");
		}
	}
}
