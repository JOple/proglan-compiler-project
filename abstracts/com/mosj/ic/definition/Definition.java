package com.mosj.ic.definition;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Definition extends Serializable {

	Gson JSON = new GsonBuilder().setPrettyPrinting().create();
	
	default String toJson() {
		return JSON.toJson(this);
	}
	
	default Object copy() {
		return JSON.fromJson(toJson(), this.getClass());
	}
}
