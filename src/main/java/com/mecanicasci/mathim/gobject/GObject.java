package com.mecanicasci.mathim.gobject;

import java.util.ArrayList;

import com.mecanicasci.mathim.render.Scene;
import com.mecanicasci.mathim.render.path.GPath;
import com.mecanicasci.mathim.render.path.GPathType;
import com.mecanicasci.mathim.utils.Logger;

public abstract class GObject {
	/** X initial coordinate */
	protected float x;
	
	/** Y initial coordinate */
	protected float y;
	
	
	/** List of all path from the GObject */
	private ArrayList<GPath> pathList;
	
	/** true if GObject has successfully been initalized */
	private boolean isInitialized;
	
	
	
	
	
	/**
	 * Create a simple game object
	 * @param x X position of the object
	 * @param y Y position of the object
	 */
	public GObject(float x, float y) {
		this.x = x;
		this.y = y;
		
		this.isInitialized = false;
		this.pathList      = new ArrayList<GPath>();
	}
	
	/**
	 * Initialize the GObject
	 * @return this
	 */
	public GObject init() {
		if(!Scene.isInitialized) Logger.err("You must initialize the Scene before creating GObjects.", "GObject::init()", true);
		
		this.generateVertices();
		this.isInitialized = true;
		
		return this;
	}
	
	
	
	
	
	
	
	
	
	/** Generate all structure points */
	protected abstract void generateVertices();
	
	
	
	
	
	/**
	 * Create a new path for a new composant of a GameObject.
	 * Default width and height max SVG value are 100 and 100.
	 * @param parent Parent object
	 * @return the new GPath instance
	 */
	public GPath newPath(GObject parent) {
		GPath p = newPath(parent, 0, 0, 100, 100);
		p.add(GPathType.MOVE_TO_ABS, 0, 0);
		return p;
	}
	
	/**
	 * Create a new path for a new composant of a GameObject
	 * @param parent Parent object
	 * @param minX X offset in relative width
	 * @param minY Y offset in relative height
	 * @param widthX Equivalent to 100% width
	 * @param heightY Equivalent to 100% height
	 * @return the new GPath instance
	 */
	public GPath newPath(GObject parent, double minX, double minY, float widthX, float heightY) {
		GPath obj = new GPath(parent, minX, minY, widthX, heightY);
		pathList.add(obj);
		
		return obj;
	}
	
	
	
	
	/**
	 * Renders the GObject at a certain time
	 * @param pixels
	 * @param t Time (in seconds)
	 * @param showPercent Percentage of the animation shown
	 */
	public void renderAtTime(int[] pixels, float t, float showPercent) {
		for (GPath path : pathList)
			path.renderAtTime(pixels, t, showPercent);
	}
	


	
	

	/** @return true if the path is closed */
	public boolean isClosedPath() { return true; }
	
	/** @return true if the GObject has successfully been initialized */
	public boolean isInitialized() { return this.isInitialized; }

	/** @return the GObject name */
	public String getName() { return this.getClass().getSimpleName(); }
}
