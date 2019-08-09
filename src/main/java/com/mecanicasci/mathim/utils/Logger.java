package com.mecanicasci.mathim.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mecanicasci.mathim.render.Scene;

public final class Logger {
	/**
	 * Log a specific String array to the console
	 * @param text List of Strings (each new String is a new line)
	 */
	public static void info(String... text) {
		for (String string : text)
			System.out.println(string);
	}

	
	
	
	/**
	 * Display an error
	 * @param preMessage Error custom message
	 * @param e Exception thrown
	 * @param functionId Identifier of the function typeof CLASS_NAME::FUNCTION_NAME()
	 * @param fatal true : fatal error that will exit the System
	 */
	public static void err(String preMessage, Exception e, String functionId, boolean fatal) {
		// Construct String
		String str = "=> ";
		
		if(fatal) str += "Fatal error ";
		else	  str += "Error ";
		
		str += "thrown from " + functionId;
		
		if(e == null) str += ":\n    " + preMessage;
		else		  str += ": " + preMessage;
		
		
		// Logs
		if(e != null) {
			System.err.print(str);
			e.printStackTrace();
		}
		else
			System.err.println(str);
		
		
		// Exit
		if(fatal) exit();
	}

	/**
	 * Display an error
	 * @param message Error message
	 * @param functionId Identifier of the function typeof CLASS_NAME::FUNCTION_NAME()
	 * @param fatal true : fatal error that will exit the System
	 */
	public static void err(String message, String functionId, boolean fatal) {
		err(message, null, functionId, fatal);
	}
	
	
	/**
	 * Ends the program by force. Then exits the System with error code -1
	 */
	private static void exit() {
		System.exit(-1);
	}
	
	
	
	
	
	/**
	 * Handle error from Process output Stream
	 * @param errorString String to be displayed if there is any error
	 * @param debugString String to be displayed if the program should show logs
	 * @param process The process to listen to
	 * @param functionId Identifier of the function typeof CLASS_NAME::FUNCTION_NAME()
	 */
	public static void handleErrorFromProcess(String errorString, String debugString, Process process, String functionId) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			StringBuilder builder = new StringBuilder();
			String aux = "";
	
			while ((aux = in.readLine()) != null) {
			    builder.append(aux);
			}
	
			String text = builder.toString();
			if (!text.isEmpty()) {
				if(Scene.debug == DebugLevel.SHOW_ALL)
					info("Below are logs while converting to video:", text);
				else
					err(errorString, functionId, false);
			}
		}
		catch(IOException e) { err("Error while handeling log command output from process.", functionId, false); }
	}
}
