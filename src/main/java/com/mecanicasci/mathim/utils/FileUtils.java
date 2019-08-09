package com.mecanicasci.mathim.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;

public final class FileUtils {
	/**
	 * Delete a specific directory
	 * @param dir Directory to be deleted
	 */
	public static void deleteDir(File dir) {
		File[] contents = dir.listFiles();
		
		if (contents != null) {
			for (File f : contents) {
				if (!Files.isSymbolicLink(f.toPath())) {
					deleteDir(f);
				}
			}
		}
		
		dir.delete();
	}
	
	
	/**
	 * Read content from file
	 * @param file File to read
	 * @return the String content
	 * @throws FileNotFoundException
	 */
	public static String readFile(File file) throws FileNotFoundException {
		String content = "";
		
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line != "") {
				content += "\n" + line;
			}
		}
		
		sc.close();
		
		return content;
	}
}
