package com.mecanicasci.mathim.render.path;

public class GPathPoint {
	/** X point position */
	public float x;
	/** Y point position */
	public float y;
	/** Type of displaying path */
	public GPathType pathType;
	/** Additionnal datas */
	public float[] datas;
	/** Last point created */
	public GPathPoint lastPoint;
	/** Vertex thickness */
	public int thickness;
	
	
	/**
	 * Create a new point
	 * @param x X relative position
	 * @param y Y relative position
	 * @param thickness Vertex thickness
	 * @param pathType Type of displaying path
	 * @param datas Additionnal datas
	 * @param lastPoint Last point added to the array (null if none)
	 */
	public GPathPoint(float x, float y, int thickness, GPathType pathType, float[] datas, GPathPoint lastPoint) {
		this.set(x, y);
		
		this.thickness = thickness;
		this.pathType  = pathType;
		this.lastPoint = lastPoint;
		
		
		if(datas.length > 2) {
			this.datas = new float[datas.length - 2];
			for (int i = 2; i < datas.length; i++) {
				this.datas[i - 2] = datas[i];
			}
		}
		else this.datas = new float[0];
	}
	
	
	
	
	
	/**
	 * Change the point position point
	 * @param x New relative X position
	 * @param y New relative Y position
	 */
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
