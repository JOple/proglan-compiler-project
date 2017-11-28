package com.mosj.ic.interpreter;

import java.io.InputStream;
import java.io.OutputStream;

public class StandardIOProvider implements IOProvider {

	public static IOProvider systemIO() {
		return new StandardIOProvider(System.in, System.out, System.err);
	}
	
	private InputStream inputStream;
	private OutputStream outputStream;
	private OutputStream errorStream;

	public StandardIOProvider() { }
	public StandardIOProvider(InputStream inputStream, OutputStream outputStream, OutputStream errorStream) {
		this();
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.errorStream = errorStream;
	}
	public StandardIOProvider(InputStream inputStream, OutputStream outputStream) {
		this(inputStream, outputStream, null);
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}
	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public OutputStream getErrorStream() {
		return errorStream;
	}
	public void setErrorStream(OutputStream errorStream) {
		this.errorStream = errorStream;
	}
}
