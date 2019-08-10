package com.mecanicasci.mathim.render.path;

import java.util.ArrayList;

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
	
	
	
	
	/**
	 * GPath instance
	 * @param parent Parent object
	 * @param initialY Initial X relative value
	 * @param initialX Initial X relative value
	 */
	public GPath(GObject parent, float initialX, float initialY) {
		this.parent = parent;
		
		this.points = new ArrayList<GPathPoint>();
		
		this.newPoint(initialX, initialY, 1, GPathType.INITIAL_POINT);
	}
	

	
	
	/* ============ STRUCTURE ============ */
	/**
	 * Add a new point to the list
	 * @param x X point absolute position
	 * @param y Y point absolute position
	 * @param thickness Vertex thickness
	 * @param pathType
	 */
	private void newPoint(float x, float y, int thickness, GPathType pathType, float... datas) {
		this.points.add(new GPathPoint(x, y, thickness, pathType, datas, this.lastPointAdded()));
	}
	
	
	
	/**
	 * Add a new type to the path (from <a href="https://www.w3.org/TR/SVG/paths.html#PathData">https://www.w3.org/TR/SVG/paths.html#PathData</a>)
	 * @param pathType Type of type
	 * @param params Params of the path
	 * @return this
	 */
	public GPath add(GPathType pathType, float... params) {
		/** @TODO add thickness */
		int thickness = 1;
		
		switch (pathType) {
			case INITIAL_POINT:
				break;
		
				
			case MOVE_TO_ABS:
				if(checkParamsLength(2, params))
					newPoint(params[0], params[1], thickness, pathType, params);
				break;
				
				
			case MOVE_TO:
				if(checkParamsLength(2, params))
					newPoint(lastPointAdded().x + params[0], lastPointAdded().y + params[1], thickness, pathType, params);
				break;
				
				
			case LINE_TO_ABS:
				if(checkParamsLength(2, params))
					newPoint(params[0], params[1], thickness, pathType, params);
				break;
				
				
			case LINE_TO:
				if(checkParamsLength(2, params))
					newPoint(lastPointAdded().x + params[0], lastPointAdded().y + params[1], thickness, pathType, params);
				break;
				
				
			case HORIZONTAL_ABS:
				if(checkParamsLength(1, params))
					newPoint(params[0], lastPointAdded().y, thickness, pathType, params);
				break;
				
				
			case HORIZONTAL:
				if(checkParamsLength(1, params))
					newPoint(lastPointAdded().x + params[0], lastPointAdded().y, thickness, pathType, params);
				break;
				
				
			case VERTICAL_ABS:
				if(checkParamsLength(1, params))
					newPoint(lastPointAdded().x, params[0], thickness, pathType, params);
				break;
				
				
			case VERTICAL:
				if(checkParamsLength(1, params))
					newPoint(lastPointAdded().x, lastPointAdded().y + params[0], thickness, pathType, params);
				break;
				
				
			case CURVE_BEZ:
				break;
				
				
			case CURVE_BEZ_ABS:
				break;
				
				
			case SHORTHAND_CURVE_BEZ:
				break;
				
				
			case SHORTHAND_CURVE_BEZ_ABS:
				break;
				
				
			case QUADRATIC_CURVE_BEZ:
				break;
				
				
			case QUADRATIC_CURVE_BEZ_ABS:
				break;
				
				
			case SHORTHAND_QUADRATIC_CURVE_BEZ:
				break;
				
				
			case SHORTHAND_QUADRATIC_CURVE_BEZ_ABS:
				break;
				
				
			case ELLIPTICAL_ARC:
				break;
				
				
			case ELLIPTICAL_ARC_ABS:
				break;
				
				
			case ENDING_POINT:
				if(checkParamsLength(2, params))
					newPoint(params[0], params[1], thickness, pathType, params);
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
	private boolean checkParamsLength(int length, float... params) {
		return this.checkParamsLength(length, true, params);
	}
	
	/**
	 * @param length
	 * @param params
	 * @param throwError true : Throw an error if not 
	 * @return true if there is as many parameters (or more than expected) as it should be
	 */
	private boolean checkParamsLength(int length, boolean throwError, float... params) {
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
	public void close() { this.add(GPathType.ENDING_POINT, this.points.get(0).x, this.points.get(0).y); }
	
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
	 */
	public void renderAtTime(int[] pixels, float t) {
		for (GPathPoint pt : points) {
			if(pt.pathType == GPathType.INITIAL_POINT) continue;
			
			switch (pt.pathType) {
				case MOVE_TO_ABS:
				case MOVE_TO:
				case LINE_TO_ABS:
				case LINE_TO:
				case HORIZONTAL_ABS:
				case HORIZONTAL:
				case VERTICAL_ABS:
				case VERTICAL:
				case ENDING_POINT:
					MainShapesDrawer.drawLine(pixels, pt.lastPoint.x, pt.lastPoint.y, pt.x, pt.y, pt.thickness);
					break;
					
	
				default:
					Logger.err("Type currently not implemented in SVG rendering engine: " + pt.pathType, "GPath::renderAtTime()", true);
			}
		}
	}
}
