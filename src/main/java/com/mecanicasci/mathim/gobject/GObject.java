package com.mecanicasci.mathim.gobject;

import java.util.ArrayList;

import com.mecanicasci.mathim.render.path.GPath;

public abstract class GObject {
	/** X initial square coordinate */
	protected float x;
	
	/** Y initial square coordinate */
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
		this.generateVertices();
		this.isInitialized = true;
		
		return this;
	}
	
	
	
	
	
	
	
	
	
	/** Generate all structure points */
	protected abstract void generateVertices();
	
	
	
	/**
	 * Create a new path for a new composant of a GameObject
	 * @param parent Parent object
	 * @return the new GPath instance
	 */
	protected GPath newPath(GObject parent) {
		return this.newPath(parent, 0, 0);
	}
	
	/**
	 * Create a new path for a new composant of a GameObject
	 * @param parent Parent object
	 * @param initialX
	 * @param initialY
	 * @return the new GPath instance
	 */
	protected GPath newPath(GObject parent, float initialX, float initialY) {
		GPath obj = new GPath(parent, initialX, initialY);
		pathList.add(obj);
		
		return obj;
	}
	
	
	
	
	/**
	 * Renders the GObject at a certain time
	 * @param pixels
	 * @param t Time (in seconds)
	 */
	public void renderAtTime(int[] pixels, float t) {
		for (GPath path : pathList)
			path.renderAtTime(pixels, t);
	}
	


	
	

	/** @return true if the path is closed */
	public boolean isClosedPath() { return true; }
	
	/** @return true if the GObject has successfully been initialized */
	public boolean isInitialized() { return this.isInitialized; }

	/** @return the GObject name */
	public String getName() { return this.getClass().getSimpleName(); }
}
