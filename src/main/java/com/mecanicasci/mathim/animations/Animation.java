package com.mecanicasci.mathim.animations;

import com.mecanicasci.mathim.animations.apparition.DrawElement;
import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.render.Renderer;

public abstract class Animation {
	/** Duration of the animation */
	protected AnimationLength animLength;
	
	/** List of all animation GObjects */
	protected GObject[] gObjects;


	/**
	 * Main animation class
	 * @param animLength Animation duration (in seconds)
	 * @param gObject List of all game objects in the animation
	 */
	public Animation(AnimationLength animLength, GObject... gObject) {
		this.animLength = animLength;
		this.gObjects = gObject;
	}
	
	
	
	
	
	/**
	 * Render the specific frame
	 * @param pixels the pixel array
	 * @param t the time of the frame (in seconds)
	 * @param renderer Renderer of the pixel array
	 * @return the new int[] pixel array
	 */
	public abstract int[] renderFrameAt(int[] pixels, float t, Renderer renderer);
	
	
	
	/** @return the animation duration */
	public AnimationLength getAnimLength() { return animLength; }
	
	/** @return animation name */
	public String getName() { return this.getClass().getSimpleName(); }






	/** List of all animations */
	public enum AnimationList {
		/** Draw an element on the screen as a vector array */
		DRAW_ELEMENT;
		
		
		/**
		 * Get class by AnimationList ID name
		 * @param name Name of the animation
		 * @param runtime Duration of the animation
		 * @param gObject List of all game objects
		 * @return the animation class
		 */
		public static Animation instanciateClassById(AnimationList name, AnimationLength runtime, GObject... gObject) {
			switch(name) {
				case DRAW_ELEMENT:
					return new DrawElement(runtime, gObject);
			}
			
			return null;
		}
	}
	
	
	/** List of all animations lengths in seconds */
	public enum AnimationLength {
		/** 1 second */
		SHORT (1),
		/** 2 seconds (default value) */
		NORMAL(2),
		/** 4 seconds */
		LONG  (4);

		
		/** Animation duration (in seconds) */
		private float duration;
		private AnimationLength(float duration) {
			this.duration = duration;
		}
		
		
		
		
		/** @return the instanciated animation duration */
		public float getDuration() { return this.duration; }
		
		/**
		 * @param animation
		 * @return the specific animation duration (in seconds)
		 */
		public static float getDuration(AnimationLength animation) { return animation.duration; }
		
		/** @return the default animation length in seconds */
		public static AnimationLength getDefault() { return SHORT; }
	}
}
