package com.mecanicasci.mathim.utils;

public enum Debug {
	/** (Log level 1/4) DEFAULT VALUE : Delete all temporary files after rendering */
	NORMAL,
	/** (Log level 2/4) Keep only mp4 temporary files */
	KEEP_MP4_TMP,
	/** (Log level 3/4) Keep all temporary files */
	KEEP_ALL_TMP,
	/** (Log level 4/4) Show every log while rendering and keep each render temporary files */
	SHOW_ALL;

	
	/** @return default debug value */
	public static Debug getDefault() { return NORMAL; }

	/**
	 * @param debug The debug level
	 * @return the ffmpeg log level based on a certain log level
	 */
	public static String getFFMPEGLogLevel(Debug debug) {
		if(debug == SHOW_ALL)
			return "info";
		else
			return "error";
	}
}
