package com.mecanicasci.mathim.render;

import java.util.ArrayList;

import com.mecanicasci.mathim.animations.Animation;
import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.animations.utils.AnimationList;
import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.render.tex.TexRenderer;
import com.mecanicasci.mathim.utils.Constants;
import com.mecanicasci.mathim.utils.DebugLevel;
import com.mecanicasci.mathim.utils.Logger;

public class Scene {
	/** Debug level */
	public static DebugLevel debug = DebugLevel.getDefault();
	/** True : Scene is initialized */
	public static boolean isInitialized = false;
	
	
	/** Scene Renderer */
	private Renderer renderer;
	/** Animations list */
	private ArrayList<Animation> animations;
	/** Name of the Scene */
	private String sceneName;

	
	
	
	
	
	/**
	 * Create a new Scene
	 * @param name Name of the scene
	 * @param debug Debug level
	 */
	public Scene(String name, DebugLevel debug) {
		renderer   = new Renderer();
		animations = new ArrayList<Animation>();
		
		this.sceneName = name;
		Scene.debug    = debug;
		
		isInitialized = true;
		
		Logger.info("Starting rendering.\n\n\n", "==== Starting initial operations ====");
		TexRenderer.cleanLastConfig();
	}
	
	
	
	/**
	 * Create a new scene
	 * @param name Name of the scene
	 * @return the new generated Scene
	 */
	public static Scene init(String name) {
		return init(name, DebugLevel.getDefault());
	}
	
	/**
	 * Create a new scene
	 * @param name Name of the scene
	 * @param debug Debug level
	 * @return the new generated Scene
	 */
	public static Scene init(String name, DebugLevel debug) {
		return new Scene(name, debug);
	}
	
	
	
	
	
	/**
	 * Add an animation
	 * @param animation Animation name
	 * @param runtime Runtime of the animation (null for default)
	 * @param obj List of objects to animate 
	 * @return this
	 */
	public Scene animate(AnimationList animation, AnimationLength runtime, GObject... obj) {
		return this.animate(animation, runtime, new Object[0], obj);
	}
	
	/**
	 * Add an animation
	 * @param animation Animation name
	 * @param runtime Runtime of the animation (null for default)
	 * @param config Configuration of the GameObject
	 * @param obj List of objects to animate 
	 * @return this
	 */
	public Scene animate(AnimationList animation, AnimationLength runtime, Object config, GObject... obj) {
		return this.animate(animation, runtime, new Object[] {config}, obj);
	}
	
	
	/**
	 * Add an animation
	 * @param animation Animation name
	 * @param runtime Runtime of the animation
	 * @param config Configuration of the GameObject
	 * @param obj List of objects to animate 
	 * @return this
	 */
	public Scene animate(AnimationList animation, AnimationLength runtime, Object[] config, GObject... obj) {
		for (GObject gObject : obj) {
			if(!gObject.isInitialized())
				Logger.err("GObject named " + gObject.getName() + " has not been initialized (you forgot to call the init() method on him).", "Scene::animate()", true);
		}
		
		animations.add(AnimationList.instanciateClassById(animation, runtime, config, obj));
		return this;
	}
	
	
	
	

	/** Render scene */
	public void play() {
		// INITIAL RENDERING OPERATIONS
		Logger.info(" # Cleaning last partial rendering temporary files");
		renderer.cleanLastConfig(sceneName);
		Logger.info("==== End initial operations ====\n\n");
		
		
		
		// ANIMATIONS
		Logger.info("==== Rendering animations ====");
		int i = 0;
		for (Animation animation : animations) {
			i++;
			Logger.info(
				String.format(" # Generating animation %d/%d called \"%s\" (d = %ds)",
				i, animations.size(), animation.getClass().getSimpleName(), (int) animation.getAnimLength().getDuration())
			);
			
			renderer.render(animation, sceneName, i - 1);
		}
		
		Logger.info(String.format(" # Compiling all %d animations into one animation", animations.size()));
		renderer.compileAllAnimations(animations, String.format(Constants.PATH_TO_RENDER + "partial/%s", sceneName), sceneName);
		
		Logger.info("==== End rendering animations ====\n\n");
		
		
		
		// END OPERATIONS
		Logger.info("==== Running final operations ====");
		if(debug.equals(DebugLevel.NORMAL) || debug.equals(DebugLevel.KEEP_MP4_TMP)) {
			Logger.info(" # Cleaning LaTeX temporary files");
			TexRenderer.clean();
			
			Logger.info(" # Cleaning rendering temporary files");
			renderer.clean(sceneName, animations);
		}
		Logger.info("==== End running final operations ====\n\n\n");
		
		
		
		// DONE
		Logger.info(String.format("Rendering done.\nCheck file '%s'.", Constants.PATH_TO_RENDER + sceneName + ".mp4"));
	}
}
