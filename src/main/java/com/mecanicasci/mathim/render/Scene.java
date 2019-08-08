package com.mecanicasci.mathim.render;

import java.util.ArrayList;

import javax.management.InstanceNotFoundException;

import com.mecanicasci.mathim.animations.Animation;
import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.animations.utils.AnimationList;
import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.utils.Constants;
import com.mecanicasci.mathim.utils.DebugLevel;

public class Scene {
	/** Debug level */
	public static DebugLevel debug = DebugLevel.getDefault();
	
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
			try {
				if(!gObject.isInitialized())
					throw new InstanceNotFoundException(
						"GObject named " + gObject.getName() + " has not been initialized (you forgot to call the init() method on him)."
					);
			}
			catch(InstanceNotFoundException e) {
				e.printStackTrace();
				return this;
			}
		}
		
		animations.add(AnimationList.instanciateClassById(animation, runtime, config, obj));
		return this;
	}
	
	
	
	

	/** Render scene */
	public void play() {
		System.out.println("Starting rendering.\n\n");
		
		System.out.println("====== Cleaning last partial temporary files ======");
		renderer.cleanLastConfig(sceneName);
		System.out.println("====== End cleaning last partial temporary files ======\n\n\n");
		
		
		int i = 0;
		for (Animation animation : animations) {
			i++;
			System.out.println(String.format("=== %d/%d === Generating animation called \"%s\" (d = %ds) ======",
					i, animations.size(), animation.getClass().getSimpleName(), (int) animation.getAnimLength().getDuration()));
			
			renderer.render(animation, sceneName, i - 1);
			
			System.out.println(String.format("=== %d/%d === End generating animation ======\n\n", i, animations.size()));
		}
		
		
		System.out.println(String.format("\n====== Compiling all %d animations into one animation ======", animations.size()));
		renderer.compileAllAnimations(animations, String.format(Constants.PATH_TO_RENDER + "partial/%s", sceneName), sceneName);
		System.out.println("====== End compiling all animations ======");
		
		
		if(debug.equals(DebugLevel.NORMAL) || debug.equals(DebugLevel.KEEP_MP4_TMP)) {
			System.out.println("\n\n====== Cleaning render temporary files ======");
			renderer.clean(sceneName, animations);
			System.out.println("====== End cleaning render temporary files ======");
		}
		
		
		System.out.println(String.format("\n\nRendering done.\nSee the file '%s'.", Constants.PATH_TO_RENDER + sceneName + ".mp4"));
	}
}
