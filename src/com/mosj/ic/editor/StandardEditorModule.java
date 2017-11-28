package com.mosj.ic.editor;

public class StandardEditorModule implements EditorModule {

	private Editor editor;
	
	@Override
	public void run() { }

	@Override
	public Editor getEditor() {
		return editor;
	}
	@Override
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
}
