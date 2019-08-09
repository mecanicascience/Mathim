package com.mecanicasci.mathim.render.tex;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.mecanicasci.mathim.render.Scene;
import com.mecanicasci.mathim.utils.Constants;
import com.mecanicasci.mathim.utils.DebugLevel;
import com.mecanicasci.mathim.utils.FileUtils;
import com.mecanicasci.mathim.utils.Logger;

public final class TexRenderer {
	private static int texId = 0;
	
	/**
	 * Get a SVG content file from a tex String
	 * @param latex
	 * @return the SVG content
	 */
	public static String getSVGFromTex(String latex) {
		Logger.info(" # Generating latex SVG " + texId);
		
		String outputSVG = "";
		
		try {
			// generate files
			generateTexFromString(latex, texId);
			generateDviAuxFromTex(texId);
			generateSvgFromDvi(texId);
			
			// get svg content
			outputSVG = FileUtils.readFile(Paths.get(String.format(Constants.PATH_TO_RENDER + "partial/LaTeX/tex_%03d/%03d.svg", texId, texId)).toFile());
		}
		catch (IOException e) { Logger.err("Error while generating svg file from tex String", e, "TexRenderer::getSVGFromTex()", false); }
		
		texId++;
		
		return outputSVG;
	}
	
	
	
	
	
	
	/**
	 * Generate a temp pdf file from latex String
	 * @param latex
	 * @param animationID ID of the animation
	 * @throws IOException
	 */
	private static void generateTexFromString(String latex, int animationID) throws IOException {
		String content =
			"\\documentclass[preview]{standalone}\r\n" + 
			"\r\n" + 
			"\\usepackage[french]{babel}\r\n" + 
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
		
		
		String dir = Constants.PATH_TO_RENDER + "partial/LaTeX";
		new File(dir).mkdirs();
		
		dir += String.format("/tex_%03d", animationID);
		new File(dir).mkdirs();
		
		
		List<String> lines = Arrays.asList(content);
		Path file = Paths.get(String.format(dir + "/%03d.tex", animationID));
		Files.write(file, lines, StandardCharsets.UTF_8);
	}
	
	
	
	
	/**
	 * Generate a temp DVI and AUX file from tex file
	 * @param animationID ID of the animation
	 * @throws IOException
	 */
	private static void generateDviAuxFromTex(int animationID) throws IOException {
		String str =
		  "latex"
		+ " -interaction=" + "batchmode"
		+ " -halt-on-error"
		+ " -output-directory=\"" + "\""
		+ String.format(" \"%03d.tex\"", animationID);
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(str, null, new File(String.format(Constants.PATH_TO_RENDER + "partial/LaTeX/tex_%03d", animationID)));
		
		Logger.handleErrorFromProcess(
			"Error while generating Dvi and Aux",
			"Dvi and Aux generation log output:",
			pr,
			"TexRenderer::generateDviAuxFromTex()"
		);
	}
	
	
	
	
	/**
	 * Generate a temp SVG file from DVI (and eventually aux) file
	 * @param animationID ID of the animation
	 * @throws IOException
	 */
	private static void generateSvgFromDvi(int animationID) throws IOException {
		String str =
		  "dvisvgm"
		+ String.format(" \"%03d.dvi\"", animationID)
		+ " -n"
		+ " -v 0"
		+ " -o"
		+ String.format(" \"%03d.svg\"", animationID);
		
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(str, null, new File(String.format(Constants.PATH_TO_RENDER + "partial/LaTeX/tex_%03d", animationID)));
		
		Logger.handleErrorFromProcess(
			"Error while generating Svg",
			"Svg generation log output:",
			pr,
			"TexRenderer::generateSvgFromDvi()"
		);
	}





	/**
	 * Clean last LaTeX temporary files (warning, this will delete all LaTeX folder)
	 */
	public static void cleanLastConfig() {
		Logger.info(" # Cleaning last latex temporary files");
		FileUtils.deleteDir(Paths.get(String.format(Constants.PATH_TO_RENDER + "partial/LaTeX")).toFile());
	}


	/**
	 * Clean all useless LaTeX temporary files in function of the Debug configuration
	 */
	public static void clean() {
		if(Scene.debug == DebugLevel.KEEP_ALL_TMP || Scene.debug == DebugLevel.SHOW_ALL)
			return;
		else
			FileUtils.deleteDir(Paths.get(Constants.PATH_TO_RENDER + "partial/LaTeX").toFile());
	}
}
