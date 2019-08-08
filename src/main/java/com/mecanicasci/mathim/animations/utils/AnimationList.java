package com.mecanicasci.mathim.animations.utils;

import com.mecanicasci.mathim.animations.Animation;
import com.mecanicasci.mathim.animations.apparition.AnimationStatic;
import com.mecanicasci.mathim.gobject.GObject;

/** List of all animations */
@SuppressWarnings("javadoc")
public enum AnimationList {
	/**
	 * A simple static animation
	 * @param none No parameters
	 */
	STATIC_ANIMATION;
	
	
	
	
	/**
	 * Get class by AnimationList ID name
	 * @param name Name of the animation
	 * @param runtime Duration of the animation
	 * @param config Config of the animation
	 * @param gObject List of all game objects
	 * @return the animation class
	 */
	public static Animation instanciateClassById(AnimationList name, AnimationLength runtime, Object[] config, GObject... gObject) {
		switch(name) {
			case STATIC_ANIMATION:
				return new AnimationStatic(runtime, config, gObject);
				
			default:
				return null;
		}
	}
}
