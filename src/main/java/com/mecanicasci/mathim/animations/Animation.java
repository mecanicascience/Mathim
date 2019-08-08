package com.mecanicasci.mathim.animations;

import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.gobject.GObject;

public abstract class Animation {
	/** Duration of the animation */
	protected AnimationLength animLength;
	
	/** List of all animation GObjects */
	protected GObject[] gObjects;

	


	/**
	 * Main animation class
	 * @param animLength Animation duration (in seconds)
	 * @param config Configuration of the animation
	 * @param gObject List of all game objects in the animation
	 */
	public Animation(AnimationLength animLength, Object[] config, GObject... gObject) {
		this.animLength = animLength;
		this.gObjects   = gObject;
		
		handleConfig(config);
	}
	
	
	
	
	
	/**
	 * Handle configuration
	 * @param config Configuration of the animation
	 */
	public abstract void handleConfig(Object[] config);
	
	/**
	 * Update the pixels for the specific frame and for the specific gameObject
	 * @param pixels the pixel array
	 * @param t the time of the frame (in seconds)
	 * @param gameObject
	 */
	public abstract void renderFrameAt(int[] pixels, float t, GObject gameObject);
	
	
	
	
	/**
	 * Update the pixels for the specific frame
	 * @param pixels the pixel array
	 * @param t the time of the frame (in seconds)
	 */
	public void renderFrameAt(int[] pixels, float t) {
		for (GObject gameObject : this.gObjects)
			this.renderFrameAt(pixels, t, gameObject);
	}
	
	
	
	
	
	
	
	/** @return the animation duration */
	public AnimationLength getAnimLength() { return animLength; }
	
	/** @return animation name */
	public String getName() { return this.getClass().getSimpleName(); }
}
