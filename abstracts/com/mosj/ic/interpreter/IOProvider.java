package com.mosj.ic.interpreter;

import java.io.InputStream;
import java.io.OutputStream;

public interface IOProvider {

	InputStream getInputStream();
	OutputStream getOutputStream();
	OutputStream getErrorStream();
}
