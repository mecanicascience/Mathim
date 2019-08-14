package com.mecanicasci.mathim.animations.apparition;

import com.mecanicasci.mathim.animations.Animation;
import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.gobject.GObject;

public class AnimationStatic extends Animation {
	/**
	 * Draw a simple element on screen by shape
	 * @param animLength
	 * @param config
	 * @param gObject
	 */
	public AnimationStatic(AnimationLength animLength, Object[] config, GObject... gObject) {
		super(animLength, config, gObject);
	}

	
	@Override
	public void handleConfig(Object[] config) {} // no configuration
	
	
	@Override
	public void renderFrameAt(int[] pixels, float t, float showPercent, GObject gameObject) {
		gameObject.renderAtTime(pixels, t, showPercent);
	}
}
