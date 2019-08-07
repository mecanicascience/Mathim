package com.mecanicasci.mathim.utils;

import java.io.File;
import java.nio.file.Files;

public class FileUtils {
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
}
