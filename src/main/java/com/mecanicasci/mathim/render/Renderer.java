package com.mecanicasci.mathim.render;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mecanicasci.mathim.animations.Animation;
import com.mecanicasci.mathim.render.drawer.PixelDrawer;
import com.mecanicasci.mathim.utils.Constants;
import com.mecanicasci.mathim.utils.DebugLevel;
import com.mecanicasci.mathim.utils.FileUtils;
import com.mecanicasci.mathim.utils.Logger;

public class Renderer {
	/** Pixels list [r, g, b, a] */
	private ArrayList<int[]> frames;
	
	
	
	/**
	 * Scene renderer
	 */
	public Renderer() {
		this.frames = new ArrayList<int[]>();
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Start rendering
	 * @param animation Animation to be render
	 * @param sceneName Name of the scene
	 * @param animationID Number of the animation position in the list
	 */
	public void render(Animation animation, String sceneName, int animationID) {
		this.frames.clear();
		
		// get each frames
		this.renderAnimation(animation);
		
		
		
		// export each frame to png
		String dir = String.format(Constants.PATH_TO_RENDER + "partial/%s", sceneName);
		new File(dir).mkdirs();
		
		dir += String.format("/%03d_%s", animationID, animation.getName());
		new File(dir).mkdirs();
		
		String dir2 = dir +  "/png";
		new File(dir2).mkdirs();
		
		this.exportPNGFrames(dir2 + "/");
		
		
		
		// generate mp4 for animation
		try {
			this.generateMP4(animation.getName(), dir);
		}
		catch(IOException e) { Logger.err("Error while generating MP4 for animation with id " + animationID, e, "Renderer::render()", false); }
	}
	
	
	
	
	
	
	/**
	 * Render an animation and show the progressbar
	 * @param animation Animation to be render
	 */
	private void renderAnimation(Animation animation) {
		for (float t = 0; t < animation.getAnimLength().getDuration(); t += Constants.FRAME_DURATION) {
			int[] pixels = PixelDrawer.getBlankPixelsArray();
			animation.renderFrameAt(pixels, t);
			
			this.frames.add(pixels);
		}
	}
	
	
	/**
	 * Export each frame to a specific PNG image
	 * @param path Path to the image folder
	 */
	private void exportPNGFrames(String path) {
		int image_index = 0;
		for (int[] pixels : frames) {
			BufferedImage img = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_ARGB);
			
			WritableRaster raster = (WritableRaster) img.getData();
            raster.setPixels(0, 0, Constants.WIDTH, Constants.HEIGHT, pixels);
            
            img.setData(raster);
			
			try {
				ImageIO.write(img, "png",  new File(String.format(path + Constants.IMAGE_TMP_ID, image_index)));
				image_index++;
			}
			catch(IOException e) { Logger.err("Error exporting PNG Frames", e, "Renderer::exportPNGFrames()", false); }
		}
	}
	
	
	
	
	/**
	 * Compute all generated images to create a MP4 file
	 * @param animationName Animation rendered name
	 * @param dir Directory of the animation
	 * @throws IOException 
	 */
	private void generateMP4(String animationName, String dir) throws IOException {
		String str =
			  "ffmpeg"
			+ " -y"
			+ " -loglevel " + Constants.FFMPEG_ERROR_LEVEL
			+ " -r "        + Constants.FPS
			+ " -s "        + Constants.WIDTH + "x" + Constants.HEIGHT
			+ " -i "      	+ "png/" + Constants.IMAGE_TMP_ID
			+ " -vcodec "   + "libx264"	// render engine
			+ " -crf "      + Constants.RENDERED_QUALITY
			+ " -pix_fmt "  + "yuv420p"	// pixel format
			+ " " + String.format("%s.mp4", animationName);
		
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(str, null, new File(dir));
		
		Logger.handleErrorFromProcess(
			"Error while generating a specific MP4 file for animation " + animationName + ":",
			"Logs while generating a specific MP4 file for animation " + animationName + ":",
			pr,
			"Renderer::generateMP4()"
		);
	}


	

	/**
	 * Compile all generated mp4 animations into one mp4
	 * @param animations All animations from the Scene
	 * @param dir Directory of the Scene
	 * @param outputName Name of the output final mp4
	 */
	public void compileAllAnimations(ArrayList<Animation> animations, String dir, String outputName) {
		// generate animation text file
		ArrayList<String> lines = new ArrayList<String>();
		for (int i = 0; i < animations.size(); i++)
			lines.add(String.format("file '%03d_%s/%s.mp4'", i, animations.get(0).getName(), animations.get(0).getName()));
		
		try {
			Path file = Paths.get(dir + "/" + Constants.FFMPEG_MP4_DESCRIPTION_FILE);
			Files.write(file, lines, StandardCharsets.UTF_8);
		}
		catch (NoSuchFileException e) {
			Logger.err("It appears that you forgot to add animations to the scene (nothing to render)", e, "Renderer::compileAllAnimations()", false);
			return;
		}
		catch (IOException e) {
			Logger.err("Error while generating animations list file", e, "Renderer::compileAllAnimations()", false);
			return;
		}
		
		
		// combine command
		String outputFileName = String.format("%s.mp4", outputName);
		String str =
				  "ffmpeg"
				+ " -y"
				+ " -f " 		+ "concat"
				+ " -i "		+ Constants.FFMPEG_MP4_DESCRIPTION_FILE
				+ " -loglevel " + Constants.FFMPEG_ERROR_LEVEL
				+ " -c "        + "copy"
				+ " " + outputFileName;
			
		try {
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(str, null, new File(dir));
			
			Logger.handleErrorFromProcess(
				"Logs while concating animations videos:",
				"Error while concating MP4 animations videos (1)",
				pr,
				"Renderer::compileAllAnimations()"
			);
		}
		catch (IOException e) { Logger.err("Error while concating MP4 animations videos (2)", "Renderer::compileAllAnimations()", false); }
		
		
		// copy generated file
		try {
			Files.copy(
				Paths.get(dir + "/" + outputFileName),
				Paths.get(Constants.PATH_TO_RENDER + outputFileName),
				StandardCopyOption.REPLACE_EXISTING
			);
		}
		catch (IOException e) { Logger.err("Error while copying final video", "Renderer::compileAllAnimations()", false); }
	}




	
	/**
	 * Delete potential last files and folders
	 * @param sceneName Name of the scene rendered
	 */
	public void cleanLastConfig(String sceneName) {
		FileUtils.deleteDir(Paths.get(Constants.PATH_TO_RENDER + "partial/" + sceneName).toFile());
	}

	
	/**
	 * Clean all temporary files after rendering
	 * @param sceneName Name of the scene rendered
	 * @param animations List of all animations
	 */
	public void clean(String sceneName, ArrayList<Animation> animations) {
		if(Scene.debug == DebugLevel.KEEP_ALL_TMP || Scene.debug == DebugLevel.SHOW_ALL) return;
		
		if(Scene.debug == DebugLevel.NORMAL) {
			FileUtils.deleteDir(Paths.get(Constants.PATH_TO_RENDER + "partial/" + sceneName).toFile());
		}
		else { // Debug.KEEP_MP4_TMP
			int id = 0;
			
			for (Animation animation : animations) {
				FileUtils.deleteDir(Paths.get(
					String.format("%spartial/%s/%03d_%s/png", Constants.PATH_TO_RENDER, sceneName, id, animation.getName())
				).toFile());
				id++;
			}
		}
	}
}
