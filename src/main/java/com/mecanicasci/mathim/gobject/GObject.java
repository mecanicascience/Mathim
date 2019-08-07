package com.mecanicasci.mathim.gobject;

import java.util.ArrayList;

public abstract class GObject {
	/**
	 * All vertices
	 * Each vertex is stored as an integer set of coordonates <x, y, z>
	 * Each coordonate goes from 0 to RELATIVE_WIDTH / RELATIVE_HEIGHT
	 *  ====0###y=0####====
	 * |    #         #    |
	 * |    #         #    |
	 * |   x=0      x=100  |
	 * |    #         #    |
	 * |    #         #    |
	 *  ====###y=100###====
	 */
	protected ArrayList<float[]> vertices;
	
	/** X initial square coordinate */
	protected float x;
	
	/** Y initial square coordinate */
	protected float y;
	
	
	
	/**
	 * Create a simple game object
	 * @param x X position of the object
	 * @param y Y position of the object
	 */
	public GObject(float x, float y) {
		this.x = x;
		this.y = y;
		
		vertices = new ArrayList<float[]>();
	}
	
	/**
	 * Initialize the GObject
	 * @return this
	 */
	public GObject init() {
		this.generateVertices();
		return this;
	}
	
	
	
	
	/**
	 * Scale the GObject by a factor from the center of the GameObject
	 * @param factor
	 * @return this
	 */
	public GObject scale(float factor) {
		this.scale(factor, x, y);
		return this;
	}
	
	/**
	 * Scale the GObject by a factor
	 * @param factor
	 * @param pivotX X center scale point (GO origin X position by default)
	 * @param pivotY Y center scale point (GO origin Y position by default)
	 * @return this
	 */
	public GObject scale(float factor, float pivotX, float pivotY) {
		for (float[] vertex : vertices) {
			float dx     = vertex[0] - pivotX;
			float dy     = vertex[1] - pivotY;
			
			double r     = Math.sqrt(dx*dx + dy*dy);
			double theta = Math.atan2(dy, dx);
			
			double x2    = r * factor * Math.cos(theta) + pivotX;
			double y2    = r * factor * Math.sin(theta) + pivotY;
			
			vertex[0] = (float) x2;
			vertex[1] = (float) y2;
		}
		
		return this;
	}
	
	
	
	
	
	
	/**
	 * Rotate all points from the object by an angle, in clockwise direction from the center of the GameObject
	 * @param angle Angle in radians
	 * @return this
	 */
	public GObject rotate(float angle) {
		return this.rotate(angle, x, y);
	}
	
	/**
	 * Rotate all points from the object by an angle, in clockwise direction
	 * @param angle Angle in radians
	 * @param pivotX X center rotation point (GO origin X position by default)
	 * @param pivotY Y center rotation point (GO origin Y position by default)
	 * @return this
	 */
	public GObject rotate(float angle, float pivotX, float pivotY) {
		for (float[] vertex : vertices) {
			float dx     = vertex[0] - pivotX;
			float dy     = vertex[1] - pivotY;
			
			double r     = Math.sqrt(dx*dx + dy*dy);
			double theta = Math.atan2(dy, dx);
			
			double x2    = r * Math.cos(theta + angle) + pivotX;
			double y2    = r * Math.sin(theta + angle) + pivotY;
			
			vertex[0] = (float) x2;
			vertex[1] = (float) y2;
		}
		
		return this;
	}
	
	
	
	
	
	
	
	/** Generate all structure points */
	protected abstract void generateVertices();
	
	/**
	 * Add a vertex to the game object
	 * @param x Local X coordinate
	 * @param y Local Y coordinate
	 */
	protected void putVertex(float x, float y) {
		vertices.add(new float[] {x, y, 0});
	}
	
	/**
	 * Add a vertex to the game object
	 * @param x Local X coordinate
	 * @param y Local Y coordinate
	 * @param z Local Z coordinate
	 */
	protected void putVertex(float x, float y, float z) {
		vertices.add(new float[] {x, y, z});
	}
	
	
	



	
	

	/** @return true if the path is closed */
	public boolean isClosedPath() { return true; }
	
	/** @return all vertices from the Game Object */
	public ArrayList<float[]> getVertices() { return this.vertices; }
}
