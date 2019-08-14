package com.mecanicasci.mathim.render.path;

import com.mecanicasci.mathim.config.Constants;

public class GPathPoint {
	/** X converted point position */
	public double x;
	/** Y converted point position */
	public double y;
	/** Min X SVG */
	public float minX;
	/** Min Y SVG */
	public float minY;
	/** Initial object width */
	public float width;
	/** Initial object height */
	public float height;
	
	
	/** Type of displaying path */
	public GPathType pathType;
	/** Additionnal datas */
	public double[] datas;
	/** Last point created */
	public GPathPoint lastPoint;
	/** Vertex thickness */
	public int thickness;
	
	
	
	
	/**
	 * Create a new point
	 * @param x X relative position
	 * @param y Y relative position
	 * @param width Initial object width
	 * @param height Initial object height
	 * @param minX Min X SVG
	 * @param minY Min Y SVG
	 * @param thickness Vertex thickness
	 * @param pathType Type of displaying path
	 * @param datas Additionnal datas
	 * @param lastPoint Last point added to the array (null if none)
	 */
	public GPathPoint(
		double x, double y, float width, float height,
		float minX, float minY,
		int thickness, GPathType pathType, double[] datas, GPathPoint lastPoint
	) {
		this.set(x, y);
		
		this.width  = width;
		this.height = height;
		this.minX   = minX;
		this.minY   = minY;
		
		this.thickness = thickness;
		this.pathType  = pathType;
		this.lastPoint = lastPoint;
		
		if(datas.length > 2) {
			this.datas = new double[datas.length - 2];
			for (int i = 2; i < datas.length; i++) {
				this.datas[i - 2] = datas[i];
			}
		}
		else this.datas = new double[0];
	}
	
	
	
	
	
	/**
	 * Change the point position point
	 * @param x New relative X position
	 * @param y New relative Y position
	 */
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get original point positions as a float array
	 * @return the float array
	 */
	public double[] getOr() {
		return new double[] {
			x * width  / Constants.RELATIVE_WIDTH  - minX,
			y * height / Constants.RELATIVE_HEIGHT - minY
		};
	}
}
