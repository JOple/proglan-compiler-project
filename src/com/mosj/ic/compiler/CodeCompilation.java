package com.mosj.ic.compiler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.mosj.ic.definition.ProgramDefinition;
import com.mosj.ic.log.Log;

public class CodeCompilation {

	private static final Gson JSON = new Gson();
	
	public static CodeCompilation of(ProgramDefinition program) {
		return new CodeCompilation(program);
	}
	public static CodeCompilation of(String json) {
		return of(JSON.fromJson(json, ProgramDefinition.class));
	}
	public static CodeCompilation of(InputStream stream) throws IOException {
		try(ObjectInputStream in = new ObjectInputStream(stream)) {
			try {
				return of((ProgramDefinition) in.readObject());
			} catch(ClassNotFoundException e) {
				throw new RuntimeException("Co2Compilation class is not found", e);
			} catch(ClassCastException e) {
				throw new RuntimeException("The read object is not a program definition", e);
			}
		}
	}
	public static CodeCompilation of(File f) throws IOException {
		return of(new FileInputStream(f));
	}
	
	private ProgramDefinition program;
	private List<Log> logs;

	public CodeCompilation(ProgramDefinition program) {
		this.program = program;
		this.logs = Collections.emptyList();
	}
	public CodeCompilation(ProgramDefinition program, List<Log> logs) {
		this.program = program;
		this.logs = Collections.unmodifiableList(logs);
	}
	
	public ProgramDefinition asProgram() {
		return program;
	}
	public String asJson() {
		return program.toJson();
	}
	public byte[] asBinary() {
		try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			toBinary(out);
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void toJson(OutputStream stream) throws IOException {
		DataOutputStream data = new DataOutputStream(stream);
		data.writeBytes(asJson());
	}
	public void toBinary(OutputStream stream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(stream);
		out.writeObject(program);
	}
	
	public List<Log> logs() {
		return logs;
	}
}
