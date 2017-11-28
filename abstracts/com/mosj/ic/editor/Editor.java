package com.mosj.ic.editor;

import java.util.Collection;

public interface Editor extends Runnable {

	Collection<EditorModule> getModules();
	<T> T getModule(Class<T> module);
	<T extends EditorModule> void addModule(T module);
	void removeModule(Class<?> module);
}
