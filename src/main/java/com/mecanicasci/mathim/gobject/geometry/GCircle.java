package com.mecanicasci.mathim.gobject.geometry;

import com.mecanicasci.mathim.gobject.GObject;

public class GCircle extends GObject {
	/** Circle radius */
	@SuppressWarnings("unused")
	private float r;
	
	
	/**
	 * Create a circle
	 * @param x X position of the object
	 * @param y Y position of the object
	 * @param r Circle radius
	 */
	public GCircle(float x, float y, float r) {
		super(x, y);
		
		this.r = r;
	}

	
	
	@Override
	protected void generateVertices() {
		
	}
}
