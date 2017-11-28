package com.mosj.ic.editor;

public interface EditorModule extends Runnable {

	Editor getEditor();
	void setEditor(Editor editor);

	default <T> T getModule(Class<T> module) {
		return getEditor().getModule(module);
	}
}
