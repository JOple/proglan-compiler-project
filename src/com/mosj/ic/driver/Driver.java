package com.mosj.ic.driver;

import com.google.gson.Gson;
import com.mosj.ic.log.Loggers;
import com.mosj.ic.log.BasicLogger;

public class Driver {

	public static final Gson JSON = new Gson();
	
	
	public static void main(String[] args) throws Exception {
		BasicLogger m = Loggers.create(Driver.class);
		m.debug("asdasd");
		System.out.println(m.logs());
	}
}
