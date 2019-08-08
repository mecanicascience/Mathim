package com.mecanicasci.mathim.animations.utils;

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
