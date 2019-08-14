package com.mecanicasci.mathim.gobject.geometry;

import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.render.path.GPathType;

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
		newPath(this) // centered by middle
			.add(GPathType.MOVE_TO,  x - w / 2, y - h / 2)
			.add(GPathType.LINE_TO,  w,  0)
			.add(GPathType.LINE_TO,  0,  h)
			.add(GPathType.LINE_TO, -w,  0)
			.close();
	}
}
