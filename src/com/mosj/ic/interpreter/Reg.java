package com.mosj.ic.interpreter;

import java.util.LinkedHashMap;
import java.util.Map;

import com.mosj.ic.value.Value;
import com.mosj.ic.value.Values;

public interface Reg {

	String R_NULL = "$null";

	String R_DONE = "$done";
	String R_TRAP = "$trap";

	String R_A00 = "$a00";
	String R_A01 = "$a01";
	String R_A02 = "$a02";
	String R_A03 = "$a03";
	String R_A04 = "$a04";
	String R_A05 = "$a05";
	String R_A06 = "$a06";
	String R_A07 = "$a07";
	String R_A08 = "$a08";
	String R_A09 = "$a09";
	String R_A10 = "$a10";
	String R_A11 = "$a11";
	String R_A12 = "$a12";
	String R_A13 = "$a13";
	String R_A14 = "$a14";
	String R_A15 = "$a15";
	String R_A16 = "$a16";
	String R_A17 = "$a17";
	String R_A18 = "$a18";
	String R_A19 = "$a19";

	String R_V0 = "$v0";
	String R_V1 = "$v1";

	String INS = "$ii";
	
	String[] ARRAY_R_AXX = new String[] {
			R_A00, 
			R_A01, 
			R_A02, 
			R_A03, 
			R_A04, 
			R_A05, 
			R_A06, 
			R_A07, 
			R_A08, 
			R_A09, 
			R_A10, 
			R_A11, 
			R_A12, 
			R_A13, 
			R_A14, 
			R_A15, 
			R_A16, 
			R_A17, 
			R_A18, 
			R_A19, 
	};
	String[] ARRAY_R_VX = new String[] {
			R_V0,
			R_V1
	};
	
	public static Map<String, Value> registers() {
		LinkedHashMap<String, Value> map = new LinkedHashMap<>();
		map.put(R_NULL, Values.ofNull());
		map.put(R_DONE, Values.ofBool(false));
		map.put(R_TRAP, Values.ofBool(false));
		for(String ra : ARRAY_R_AXX)
			map.put(ra, Values.ofAny(null));
		for(String rv : ARRAY_R_VX)
			map.put(rv, Values.ofAny(null));
		return map;
	}
}
