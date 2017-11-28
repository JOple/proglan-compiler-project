package com.mosj.ic.editor.modules;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingWorker;

import com.mosj.ic.editor.StandardEditorModule;

public class IOModule extends StandardEditorModule {

	public class ConsoleWorker extends SwingWorker<Void, String> {
		
		private ConsoleWorker() { }
		
		@Override
		protected Void doInBackground() throws Exception {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(outReader);
			while (true)
				publish(s.nextLine() + "\n");
		}
		
		@Override
		protected void process(List<String> chunks) {
			for (String line : chunks)
				ui.getConsole().append(line);
		}
	}
	public class ConsoleListener extends KeyAdapter {
		
		private StringBuffer line = new StringBuffer();
		
		private PipedInputStream inStream;
		private PipedOutputStream outStream;
		
		private ConsoleListener() {
			outStream = new PipedOutputStream();
			try {
				inStream = new PipedInputStream(outStream);
			} catch (IOException e) {
				System.err.println("Error initializing inStream: will use default");
			}
		}
		
		@Override
		public void keyTyped(KeyEvent ev) {
			char c = ev.getKeyChar();
			if (c == KeyEvent.VK_ENTER) {
				try {
					line.append("\r\n");
					outStream.write(line.toString().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					line = new StringBuffer();
				}
			} else if (c == KeyEvent.VK_BACK_SPACE) {
				if(line.length() > 0)
					line.setLength(line.length() - 1);
			} else if (!Character.isISOControl(c)) {
				line.append(c);
			}
		}
	}

	private UIModule ui;

	private PipedInputStream inReader;
	private PipedInputStream outReader;
	
	private PrintStream inWriter;	
	private PrintStream outWriter;
	
	private ConsoleWorker worker;
	private ConsoleListener listener;
	
	@Override
	public void run() {
		ui = getModule(UIModule.class);

		inReader = new PipedInputStream();
		outReader = new PipedInputStream();
		
		try {
			inWriter = new PrintStream(new PipedOutputStream(inReader), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			outWriter = new PrintStream(new PipedOutputStream(outReader), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		worker = new ConsoleWorker();
		worker.execute();
		
		listener = new ConsoleListener();
		ui.getConsole().addKeyListener(listener);
	}

	public PipedInputStream getInReader() {
		return inReader;
	}
	public PipedInputStream getOutReader() {
		return outReader;
	}
	public PrintStream getInWriter() {
		return inWriter;
	}
	public PrintStream getOutWriter() {
		return outWriter;
	}
	public ConsoleWorker getWorker() {
		return worker;
	}
	public ConsoleListener getListener() {
		return listener;
	}

	public OutputStream getOutputStream() {
		return new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				String s = new String(new int[] { b }, 0, 1);
				ui.getConsole().append(s);
			}
		};
	}
	public OutputStream getErrorStream() {
		return new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				String s = new String(new int[] { b }, 0, 1);
				ui.getConsole().append(s);
			}
		};
	}
	public InputStream getInputStream() {
		return listener.inStream == null ? System.in : new InputStream() {
			
			@Override
			public int read() throws IOException {
				return listener.inStream.read();
			}
		};
	}
}
