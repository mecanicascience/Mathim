package com.mecanicasci.mathim.gobject.elements;

import com.mecanicasci.mathim.gobject.GObject;

public class GSquare extends GObject {
	/** Square width */
	private float w;
	/** Square height */
	private float h;


	/**
	 * Creation of a square based on center coordinates
	 * @param x X center initial square coordinate
	 * @param y Y center initial square coordinate
	 * @param w Square width
	 * @param h Square height
	 */
	public GSquare(float x, float y, float w, float h) {
		super(x, y);
		
		this.w = w;
		this.h = h;
	}
	
	
	

	
	
	@Override
	protected void generateVertices() {
		float w2 = w / 2;
		float h2 = h / 2;
		
		putVertex(x - w2, y - h2);
		putVertex(x + w2, y - h2);
		putVertex(x + w2, y + h2);
		putVertex(x - w2, y + h2);
	}
}
