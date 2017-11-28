package com.mosj.ic.driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class CopyGrammar {

	public static String srcpath = "target/generated-sources/antlr4/";
	public static String destpath = "src/";
	public static String packageName = "com.mosj.ic.grammar";

	public static void main(String[] args) {
		String destfinal = destpath + packageName.replace('.', '/') + "/";
		File src = new File(srcpath);
		for(File f : src.listFiles()) {
			if(f.isDirectory() || !f.getAbsolutePath().endsWith(".java"))
				continue;
			if(!f.getName().startsWith("Co2pN"))
				continue;
			System.out.println(f.getAbsolutePath());
			try {
				LinkedList<String> lines = new LinkedList<>(Files.readAllLines(Paths.get(f.getAbsolutePath())));
				/*if(f.getName().contains("Listener")) {
					for(int i = 0; i < lines.size(); i++) {
						if(lines.get(i).startsWith("public")) {
							lines.add(i, "@SuppressWarnings(\"deprecation\")");
							break;
						}
					}
				}*/
				lines.addFirst("package " + packageName + ";");
				Files.write(Paths.get(destfinal, f.getName()), lines,
						StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING,
						StandardOpenOption.WRITE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
