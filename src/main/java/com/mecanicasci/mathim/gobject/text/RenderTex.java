package com.mecanicasci.mathim.gobject.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** @TODO Delete and replace this file */
public class RenderTex {
	private String name;
	public RenderTex(String name) {
		this.name = name;
	}
	
	public void getSVG(String latex) throws IOException {
		generateTex(latex, name);
		generateDviAux(name);
		generateSvg(name);
		// latex -interaction=batchmode -halt-on-error -output-directory="./media\Tex" "./media\Tex\name.tex"
		// dvisvgm "./media\Tex\name.dvi" -n -v 0 -o "./media\Tex\name.svg"
	}
	
	
	
	private void generateSvg(String name) throws IOException {
		String str =
		  "dvisvgm"
		+ " \"" + name + ".dvi\""
		+ " -n"
		+ " -v 0"
		+ " -o"
		+ " \"" + name + ".svg\"";
		
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(str, null, new File("src/main/resources/tmp/latex"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = in.readLine()) != null) {
		    builder.append(aux);
		}

		String text = builder.toString();
		System.out.println("GENERATING SVG LOG :\n" + text);
	}
	
	
	private void generateDviAux(String name) throws IOException {
		String str =
		  "latex"
		+ " -interaction=" + "batchmode"
		+ " -halt-on-error"
		+ " -output-directory=\"" + "\""
		+ " \"" + name + ".tex\"";
		
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(str, null, new File("src/main/resources/tmp/latex"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = in.readLine()) != null) {
		    builder.append(aux);
		}

		String text = builder.toString();
		System.out.println("GENERATING DVI LOG :\n" + text);
	}
	
	
	
	public void generateTex(String latex, String name) throws IOException {
		String content =
			"\\documentclass[preview]{standalone}\r\n" + 
			"\r\n" + 
			"\\usepackage[english]{babel}\r\n" + 
			"\\usepackage{amsmath}\r\n" + 
			"\\usepackage{amssymb}\r\n" + 
			"\\usepackage{dsfont}\r\n" + 
			"\\usepackage{setspace}\r\n" + 
			"\\usepackage{tipa}\r\n" + 
			"\\usepackage{relsize}\r\n" + 
			"\\usepackage{textcomp}\r\n" + 
			"\\usepackage{mathrsfs}\r\n" + 
			"\\usepackage{calligra}\r\n" + 
			"\\usepackage{wasysym}\r\n" + 
			"\\usepackage{ragged2e}\r\n" + 
			"\\usepackage{physics}\r\n" + 
			"\\usepackage{xcolor}\r\n" + 
			"\\usepackage{microtype}\r\n" + 
			"\\DisableLigatures{encoding = *, family = * }\r\n" + 
			"%\\usepackage[UTF8]{ctex}\r\n" + 
			"\\linespread{1}\r\n" + 
			"\r\n" + 
			"\\begin{document}\r\n" + 
			"\r\n" + 
			"\\begin{align*}\r\n";
		
		content += latex;
		
		content +=
			"\r\n\\end{align*}\r\n" + 
			"\r\n" + 
			"\\end{document}";
		
		
		List<String> lines = Arrays.asList(content);
		Path file = Paths.get("src/main/resources/tmp/latex/" + name + ".tex");
		Files.write(file, lines, StandardCharsets.UTF_8);
	}



	public String getSVGFileContent() throws IOException {
		String content = "";
		
		File file = new File("src/main/resources/tmp/latex/" + name + ".svg"); 
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
