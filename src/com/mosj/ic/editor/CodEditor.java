package com.mosj.ic.editor;

import com.mosj.ic.editor.modules.CompilerModule;
import com.mosj.ic.editor.modules.IOModule;
import com.mosj.ic.editor.modules.StyleModule;
import com.mosj.ic.editor.modules.UIModule;

public class CodEditor extends StandardEditor {

	public CodEditor() {
		addModule(new StyleModule());
		addModule(new UIModule());
		addModule(new IOModule());
		addModule(new CompilerModule());
	}
}
