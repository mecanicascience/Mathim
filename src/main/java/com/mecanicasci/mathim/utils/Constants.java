package com.mecanicasci.mathim.utils;

import com.mecanicasci.mathim.render.Scene;

public final class Constants {
	/** ========== MATHEMATICAL VALUES ========== */
	/** PI float value (approximated) */
	public static final float PI = (float) Math.PI;
	
	
	/** ========== RENDERING CONSTANTS ========== */
	/** Simulation FPS (60 by default) */
	public static final float FPS = 60;
	/** Frame duration (1 / FPS) */
	public static final float FRAME_DURATION = 1 / FPS;
	/** Rendering quality value (lower is better, 15-25 is ok) */
	public static final int RENDERED_QUALITY = 15;
	
	/** FFMPEG rendering log level ("info" to show all informations) */
	public static final String FFMPEG_ERROR_LEVEL = DebugLevel.getFFMPEGLogLevel(Scene.debug); 
	
	
	
	/** ========== DISPLAYING FORMAT CONSTANTS ========== */
	/** Absolute render width */
	public final static int IMAGE_WIDTH     = 1920 / 2; // width must be bigger than height
	/** Absolute render height */
	public final static int HEIGHT          = 1080 / 2;
	/** Relative render width */
	public final static int RELATIVE_WIDTH  = 100;
	/** Relative render height */
	public final static int RELATIVE_HEIGHT = 100;
	/** Absolute render width */
	public final static int WIDTH           = HEIGHT;
	/** Absolute width offset */
	public final static int OFFSET_X        = (IMAGE_WIDTH - HEIGHT) / 2;
	
	
	
	
	/** ========== IMAGES AND VIDEO PATHS ========== */
	/** Path to the folder that contains all rendered MP4 files */
	public static final String PATH_TO_RENDER = "rendered/";
	/** Name of all temporary images */
	public static final String IMAGE_TMP_ID = "frame_%04d.png";
	/** Name of the file that describe all 'sub-mp4' files to render final mp4 */
	public static final String FFMPEG_MP4_DESCRIPTION_FILE = "partial_mp4_list.txt";
}
