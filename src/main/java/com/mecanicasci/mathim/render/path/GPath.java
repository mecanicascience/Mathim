package com.mecanicasci.mathim.render.path;

import java.util.ArrayList;

import com.mecanicasci.mathim.config.Constants;
import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.render.drawer.MainShapesDrawer;
import com.mecanicasci.mathim.utils.Logger;

/**
 * A path that renders a specific path
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
public class GPath {
	private ArrayList<GPathPoint> points;
	
	private GObject parent;
	
	private float minX;
	private float minY;
	private float widthX;
	private float heightY;
	
	
	
	/**
	 * GPath instance
	 * @param parent Parent object
	 * @param minX Initial X relative value
	 * @param minY Initial X relative value
	 * @param widthX Equivalent to 100% width
	 * @param heightY Equivalent to 100% height
	 */
	public GPath(GObject parent, double minX, double minY, float widthX, float heightY) {
		this.parent = parent;
		
		this.minX    = (float) minX;
		this.minY    = (float) minY;
		this.widthX  = widthX;
		this.heightY = heightY;
		
		this.points = new ArrayList<GPathPoint>();
		
		this.newPoint(minX, minY, widthX, heightY, 1, GPathType.INITIAL_POINT);
	}
	

	
	
	/* ============ STRUCTURE ============ */
	/**
	 * Add a new point to the list
	 * @param x X point absolute position
	 * @param y Y point absolute position
	 * @param width Relative element width
	 * @param height Relative element height
	 * @param thickness Vertex thickness
	 * @param pathType
	 */
	private void newPoint(double x, double y, float width, float height, int thickness, GPathType pathType, double... datas) {
		for (int i = 0; i < datas.length; i++) {
			if(i % 2 == 0)
				datas[i] = (datas[i] + minX) / widthX  * Constants.RELATIVE_WIDTH;
			else
				datas[i] = (datas[i] + minY) / heightY * Constants.RELATIVE_HEIGHT;
		}
		
		this.points.add(new GPathPoint(
			(x + minX) / widthX  * Constants.RELATIVE_WIDTH,
			(y + minY) / heightY * Constants.RELATIVE_HEIGHT,
			width,
			height,
			minX,
			minY,
			thickness, pathType, datas, this.lastPointAdded()
		));
	}
	
	
	
	
	/**
	 * Add a new type to the path (from <a href="https://www.w3.org/TR/SVG/paths.html#PathData">https://www.w3.org/TR/SVG/paths.html#PathData</a>)
	 * @param pathType Type of type
	 * @param params Params of the path
	 * @return this
	 */
	public GPath add(GPathType pathType, double... params) {
		return add(pathType, widthX, heightY, false, params);
	}
	
	
	/**
	 * Add a new type to the path (from <a href="https://www.w3.org/TR/SVG/paths.html#PathData">https://www.w3.org/TR/SVG/paths.html#PathData</a>)
	 * @param pathType Type of type
	 * @param width Width of the SVG element
	 * @param height Height of the SVG element
	 * @param areWHSpecified true : width and height should be recalculated
	 * @param params Params of the path
	 * @return this
	 */
	public GPath add(GPathType pathType, float width, float height, boolean areWHSpecified, double... params) {
		/** @TODO add thickness */
		int thickness = 1;
		
		double lastX = lastPointAdded().getOr()[0];
		double lastY = lastPointAdded().getOr()[1];
		
		switch (pathType) {
			case INITIAL_POINT:
				break;
		
				
			case MOVE_TO_ABS:
				if(checkParamsLength(2, params))
					newPoint(params[0], params[1], width, height, thickness, pathType, params);
				break;
				
				
			case MOVE_TO:
				if(checkParamsLength(2, params))
					newPoint(lastX + params[0], lastY + params[1], width, height, thickness, pathType, params);
				break;
				
				
			case LINE_TO_ABS:
				if(checkParamsLength(2, params))
					newPoint(params[0], params[1], width, height, thickness, pathType, params);
				break;
				
				
			case LINE_TO:
				if(checkParamsLength(2, params))
					newPoint(lastX + params[0], lastY + params[1], width, height, thickness, pathType, params);
				break;
				
				
			case HORIZONTAL_ABS:
				if(checkParamsLength(1, params))
					newPoint(params[0], lastY, width, height, thickness, pathType, params);
				break;
				
				
			case HORIZONTAL:
				if(checkParamsLength(1, params))
					newPoint(lastX + params[0], lastY, width, height, thickness, pathType, params);
				break;
				
				
			case VERTICAL_ABS:
				if(checkParamsLength(1, params))
					newPoint(lastX, params[0], width, height, thickness, pathType, params);
				break;
				
				
			case VERTICAL:
				if(checkParamsLength(1, params))
					newPoint(lastX + params[0], lastY + params[1], width, height, thickness, pathType, params);
				break;
				
				
			case CURVE_BEZ:
				if(checkParamsLength(6, params)) {
					if(params.length <= 6)
						newPoint(lastX + params[0], lastY + params[1], width, height, thickness, pathType, params);
					else
						Logger.err("Type currently not implemented in SVG rendering engine with more points than 6: " + pathType, "GPath::add()", false);
				}
				break;
				
				
			case CURVE_BEZ_ABS:
				if(checkParamsLength(6, params)) {
					if(params.length <= 6)
						newPoint(params[0], params[1], width, height, thickness, pathType, params);
					else
						Logger.err("Type currently not implemented in SVG rendering engine with more points than 6: " + pathType, "GPath::add()", false);
				}
				break;
				
				
			case SHORTHAND_CURVE_BEZ:
				if(checkParamsLength(4, params)) {
					if(params.length <= 4)
						newPoint(lastX + params[0], lastY + params[1], width, height, thickness, pathType, params);
					else
						Logger.err("Type currently not implemented in SVG rendering engine with more points than 4: " + pathType, "GPath::add()", false);
				}
				break;
				
				
			case SHORTHAND_CURVE_BEZ_ABS:
				if(checkParamsLength(4, params)) {
					if(params.length <= 4)
						newPoint(params[0], params[1], width, height, thickness, pathType, params);
					else
						Logger.err("Type currently not implemented in SVG rendering engine with more points than 4: " + pathType, "GPath::add()", false);
				}
				break;
				
				
			case ENDING_POINT:
				if(checkParamsLength(2, params))
					newPoint(params[0], params[1], width, height, thickness, pathType, params);
				break;
				
				
			default:
				Logger.err("Type currently not implemented in SVG rendering engine: " + pathType, "GPath::add()", false);
		}
		
		return this;
	}



	
	

	/**
	 * @param length
	 * @param params
	 * @return true if there is as many parameters (or more than expected) as it should be
	 */
	private boolean checkParamsLength(int length, double... params) {
		return this.checkParamsLength(length, true, params);
	}
	
	/**
	 * @param length
	 * @param params
	 * @param throwError true : Throw an error if not 
	 * @return true if there is as many parameters (or more than expected) as it should be
	 */
	private boolean checkParamsLength(int length, boolean throwError, double... params) {
		if(params.length >= length) return true;
		else if(throwError) {
			Logger.err(
				"The number of parameters of a path element is not correct, for GObject called " + parent.getName() + ".",
				"GPath::checkParamsLength()",
				true
			);
			return false;
		}
		return false;
	}
	
	
	/**
	 * Close path (basically creates a new point at the position of the first one)
	 */
	public void close() {
		this.add(GPathType.ENDING_POINT, this.points.get(1).getOr()[0], this.points.get(1).getOr()[1]);
	}
	
	/**
	 * @return the last point added to the list
	 */
	private GPathPoint lastPointAdded() {
		if(this.points.size() != 0)
			return this.points.get(this.points.size() - 1);
		else
			return null;
	}


	
	
	
	

	
	/* ============ RENDERING ============ */
	/**
	 * Render the pixel array at a specific t time
	 * @param pixels Pixel array that needs to be modified
	 * @param t Time (in seconds)
	 * @param showPercent Percentage of the animation shown
	 */
	public void renderAtTime(int[] pixels, float t, float showPercent) {
		for (GPathPoint pt : points) {
			if(pt.pathType == GPathType.INITIAL_POINT) continue;
			
			switch (pt.pathType) {
				case MOVE_TO_ABS:
				case MOVE_TO:
					// Nothing to do
					break;
					
					
				case LINE_TO_ABS:
				case LINE_TO:
				case HORIZONTAL_ABS:
				case HORIZONTAL:
				case VERTICAL_ABS:
				case VERTICAL:
					MainShapesDrawer.drawLine(
						pixels,
						pt.lastPoint.x, pt.lastPoint.y,
						pt.x		  , pt.y,
						1,
						showPercent
					);
					break;
					
					
				case CURVE_BEZ:
				case CURVE_BEZ_ABS:
					MainShapesDrawer.drawBezierCurve(
						pixels,
						pt.lastPoint.x, pt.lastPoint.y,
						pt.x, 			pt.y,
						pt.datas[0], 	pt.datas[1],
						pt.datas[2], 	pt.datas[3],
						pt.thickness,
						showPercent
					);
					break;
					
					
				case SHORTHAND_CURVE_BEZ:
				case SHORTHAND_CURVE_BEZ_ABS:
					MainShapesDrawer.drawBezierCurve(
						pixels,
						pt.lastPoint.x, pt.lastPoint.y,
						pt.x, 			pt.y,
						pt.lastPoint.x, pt.lastPoint.y,
						pt.datas[0],    pt.datas[1],
						pt.thickness,
						showPercent
					);
					break;
					
					
				case ENDING_POINT:
					MainShapesDrawer.drawLine(
						pixels,
						pt.lastPoint.x , pt.lastPoint.y,
						points.get(2).x, points.get(2).y,
						1,
						showPercent
					);
					break;
	
					
				default:
					Logger.err("Type currently not implemented in SVG rendering engine: " + pt.pathType, "GPath::renderAtTime()", false);
			}
		}
	}
}
