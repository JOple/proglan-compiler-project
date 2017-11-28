package com.mosj.ic.editor.modules;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.mosj.ic.editor.Editor;
import com.mosj.ic.editor.EditorModule;

/**
 * Code snippet came from: 
 * {@link https://stackoverflow.com/users/1941489/xiaoerge}
 * {@link https://github.com/bobbylight/RSyntaxTextArea}
 * 
 * @author Josm Ople
 *
 */
public class UIModule extends JFrame implements EditorModule, ClipboardOwner {

	private static final long serialVersionUID = -9167241249525874571L;

	private Editor editor;

	private RSyntaxTextArea rEditor;
	private RTextScrollPane rEditorScrollPane;

	private RSyntaxTextArea rConsole;
	private RTextScrollPane rConsoleScrollPane;

	private JSplitPane splitPane;

	private JToolBar toolBar;
	private JMenuBar menu;
	private JMenu fileMenu, editMenu, viewMenu, codeMenu;

	private JMenuItem saveItem, saveAsItem, openItem, newItem, exitItem;
	private JMenuItem cutItem, copyItem, pasteItem, selectItem, undoItem, redoItem;
	private JMenuItem zoomInItem, zoomOutItem;
	private JMenuItem compileItem, runItem, compileRunItem, consoleItem;

	private JFileChooser fileChooser;
	private Clipboard clip;

	private boolean isChanged;
	private File target;

	private String newline = "\r\n";
	private CompilerModule compiler;

	private double consoleDivide;

	public UIModule() {
		super("CodEditor");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Co2 Files", "co2"));

		clip = Toolkit.getDefaultToolkit().getSystemClipboard();

		rEditor = new RSyntaxTextArea(20, 60);
		rEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		rEditor.setCodeFoldingEnabled(true);
		rEditor.setBracketMatchingEnabled(true);
		rEditor.setAnimateBracketMatching(true);

		rConsole = new RSyntaxTextArea(20, 60);
		rConsole.setText("Console:\n");

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				rEditorScrollPane = new RTextScrollPane(rEditor),
				rConsoleScrollPane = new RTextScrollPane(rConsole, false));

		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(splitPane, BorderLayout.CENTER);
		pane.add(toolBar = new JToolBar(), BorderLayout.SOUTH);

		setJMenuBar(menu = new JMenuBar());
		menu.add(fileMenu = new JMenu("File"));
		menu.add(editMenu = new JMenu("Edit"));
		menu.add(viewMenu = new JMenu("View"));
		menu.add(codeMenu = new JMenu("Code"));

		fileMenu.add(saveItem = new JMenuItem("Save"));
		fileMenu.add(saveAsItem = new JMenuItem("Save As"));
		fileMenu.add(openItem  = new JMenuItem("Open"));
		fileMenu.add(newItem = new JMenuItem("Create New"));
		fileMenu.addSeparator();
		fileMenu.add(exitItem = new JMenuItem("Exit"));

		editMenu.add(cutItem = new JMenuItem("Cut"));
		editMenu.add(copyItem = new JMenuItem("Copy"));
		editMenu.add(pasteItem = new JMenuItem("Paste"));
		editMenu.add(selectItem = new JMenuItem("Select All"));
		editMenu.addSeparator();
		editMenu.add(undoItem = new JMenuItem("Undo"));  
		editMenu.add(redoItem = new JMenuItem("Redo"));

		viewMenu.add(zoomInItem = new JMenuItem("Zoom In"));
		viewMenu.add(zoomOutItem = new JMenuItem("Zoom Out"));

		codeMenu.add(compileItem = new JMenuItem("Compile"));
		codeMenu.add(runItem = new JMenuItem("Run"));
		codeMenu.add(compileRunItem = new JMenuItem("Compile & Run"));
		codeMenu.addSeparator();
		codeMenu.add(consoleItem = new JMenuItem("Show/Hide Console"));

		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

		cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		selectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

		zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

		compileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.CTRL_MASK));
		runItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, ActionEvent.CTRL_MASK));
		compileRunItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.CTRL_MASK));
		consoleItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.CTRL_MASK));

		rEditor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				isChanged = true;
			}	
		});

		saveItem.addActionListener(ev -> {
			if(!isChanged)
				return;

			String addr = null;
			if(target == null) {
				if(fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
					return;
				if(fileChooser.getSelectedFile() != null) {
					setTarget(fileChooser.getSelectedFile());
					addr = target.getAbsolutePath();
					if(!addr.matches(".*\\\\.*\\.[A-Za-z0-9_]+"))
						addr += ".co2";
				}
			} else
				addr = target.getAbsolutePath();

			if(addr != null) {
				try {
					Files.write(Paths.get(addr), rEditor.getText().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
					isChanged = false;
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error saving the file: " + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		saveAsItem.addActionListener(ev -> {
			String addr = null;
			if(fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
				return;
			if(fileChooser.getSelectedFile() != null) {
				setTarget(fileChooser.getSelectedFile());
				addr = target.getAbsolutePath();
				if(!addr.matches(".*\\\\.*\\.[A-Za-z0-9_]+"))
					addr += ".co2";
				System.out.println(addr);
			}

			if(addr != null) {
				try {
					Files.write(Paths.get(addr), rEditor.getText().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
					isChanged = false;
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error saving the file: " + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		openItem.addActionListener(ev -> {
			if(isChanged && JOptionPane.showConfirmDialog(this, "There still unsaved changes. Do you want to continue?") != JOptionPane.YES_OPTION)
				return;

			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if(file == null)
					return;
				setTarget(file);

				try {
					List<String> lines = Files.readAllLines(Paths.get(target.getAbsolutePath()));
					rEditor.setText(String.join(newline, lines));
					isChanged = false;
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "An error occurred while reading the file", "IOException", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		newItem.addActionListener(ev -> {
			if(isChanged && JOptionPane.showConfirmDialog(this, "There still unsaved changes. Do you want to continue?") == JOptionPane.CANCEL_OPTION)
				return;

			setTarget(null);
			rEditor.setText("");
		});
		exitItem.addActionListener(ev -> System.exit(0));

		cutItem.addActionListener(ev -> {
			clip.setContents(new StringSelection(rEditor.getSelectedText()), this);
			rEditor.replaceRange("", rEditor.getSelectionStart(), rEditor.getSelectionEnd());
		});
		copyItem.addActionListener(ev -> clip.setContents(new StringSelection(rEditor.getSelectedText()), this));
		pasteItem.addActionListener(ev -> {
			try {
				rEditor.replaceRange("", rEditor.getSelectionStart(), rEditor.getSelectionEnd());
				rEditor.insert((String) clip.getData(DataFlavor.stringFlavor), rEditor.getCaretPosition());
			} catch (UnsupportedFlavorException | IOException e) {
				JOptionPane.showMessageDialog(this, "An error occurred while pasting", "IOException", JOptionPane.ERROR_MESSAGE);
			}
		});

		selectItem.addActionListener(ev -> rEditor.selectAll());

		undoItem.addActionListener(ev -> {
			rEditor.undoLastAction();
		});
		redoItem.addActionListener(ev -> {
			rEditor.redoLastAction();
		});

		zoomInItem.addActionListener(ev -> {
			Font oldFont = rEditor.getFont();
			if(oldFont.getSize() < 100)
				rEditor.setFont(new Font(oldFont.getName(), oldFont.getStyle(), oldFont.getSize() + 5));
		});
		zoomOutItem.addActionListener(ev -> {
			Font oldFont = rEditor.getFont();
			if(oldFont.getSize() > 10)
				rEditor.setFont(new Font(oldFont.getName(), oldFont.getStyle(), oldFont.getSize() - 5));
		});

		compileItem.addActionListener(ev -> {
			compiler.compileCode();
		});
		runItem.addActionListener(ev -> {
			compiler.runCode();
		});
		compileRunItem.addActionListener(ev -> {
			compiler.compileRunCode();
		});

		consoleItem.addActionListener(ev -> {
			if(rConsole.isVisible()) {
				consoleDivide = 1.0 * splitPane.getDividerLocation() / splitPane.getHeight();
				splitPane.setDividerLocation(splitPane.getHeight());
				splitPane.setDividerSize(0);
				rConsole.setVisible(false);
			} else {
				splitPane.setDividerLocation(consoleDivide);
				splitPane.setDividerSize(5);
				rConsole.setVisible(true);
			}
		});

	}

	@Override
	public void run() {
		compiler = getModule(CompilerModule.class);

		setVisible(true);
		splitPane.setDividerLocation(0.9);
		setTarget(null);
	}

	@Override
	public Editor getEditor() {
		return editor;
	}
	@Override
	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) { }

	/*
	 * Getters and Setters
	 */

	public boolean isChanged() {
		return isChanged;
	}
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	public File getTarget() {
		return target;
	}
	public void setTarget(File target) {
		this.target = target;
		setTitle("CodEditor - " + (target == null ? "Unnamed" : target.getAbsolutePath()));
	}

	public double getConsoleDivide() {
		return consoleDivide;
	}
	public void setConsoleDivide(double consoleDivide) {
		this.consoleDivide = consoleDivide;
	}

	public String getNewline() {
		return newline;
	}
	public void setNewline(String newline) {
		this.newline = newline;
	}

	public RSyntaxTextArea getTextEditor() {
		return rEditor;
	}
	public RTextScrollPane getTextEditorScrollPane() {
		return rEditorScrollPane;
	}
	public RSyntaxTextArea getConsole() {
		return rConsole;
	}
	public RTextScrollPane getConsoleScrollPane() {
		return rConsoleScrollPane;
	}
	public JSplitPane getSplitPane() {
		return splitPane;
	}
	public JToolBar getToolBar() {
		return toolBar;
	}
	public JMenuBar getMenu() {
		return menu;
	}
	public JMenu getFileMenu() {
		return fileMenu;
	}
	public JMenu getEditMenu() {
		return editMenu;
	}
	public JMenu getViewMenu() {
		return viewMenu;
	}
	public JMenu getCodeMenu() {
		return codeMenu;
	}
	public JMenuItem getSaveItem() {
		return saveItem;
	}
	public JMenuItem getSaveAsItem() {
		return saveAsItem;
	}
	public JMenuItem getOpenItem() {
		return openItem;
	}
	public JMenuItem getNewItem() {
		return newItem;
	}
	public JMenuItem getExitItem() {
		return exitItem;
	}
	public JMenuItem getCutItem() {
		return cutItem;
	}
	public JMenuItem getCopyItem() {
		return copyItem;
	}
	public JMenuItem getPasteItem() {
		return pasteItem;
	}
	public JMenuItem getSelectItem() {
		return selectItem;
	}
	public JMenuItem getUndoItem() {
		return undoItem;
	}
	public JMenuItem getRedoItem() {
		return redoItem;
	}
	public JMenuItem getZoomInItem() {
		return zoomInItem;
	}
	public JMenuItem getZoomOutItem() {
		return zoomOutItem;
	}
	public JMenuItem getCompileItem() {
		return compileItem;
	}
	public JMenuItem getRunItem() {
		return runItem;
	}
	public JMenuItem getCompileRunItem() {
		return compileRunItem;
	}
	public JMenuItem getConsoleItem() {
		return consoleItem;
	}
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
}