package com.mosj.ic.driver;

import javax.swing.SwingUtilities;

import com.mosj.ic.editor.CodEditor;

public class RunEditor {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new CodEditor().run();
		});
	}
}
