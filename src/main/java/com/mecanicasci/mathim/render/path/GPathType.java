package com.mecanicasci.mathim.render.path;

import com.mecanicasci.mathim.utils.Logger;

/**
 * Similar as SVG path parameters
 * Based on <a href="https://la-cascade.io/svg-la-syntaxe-path/">https://la-cascade.io/svg-la-syntaxe-path/</a>
 * List of all types:
 *  - M MOVE_TO_ABS
 *  - m MOVE_TO
 *  - L LINE_TO_ABS
 *  - l LINE_TO
 *  - H HORIZONTAL_ABS
 *  - h HORIZONTAL
 *  - V VERTICAL_ABS
 *  - v VERTICAL
 *  - C CURVE_BEZ_ABS
 *  - c CURVE_BEZ
 *  - S SHORTHAND_CURVE_BEZ_ABS
 *  - s SHORTHAND_CURVE_BEZ
 *  - Q QUADRATIC_CURVE_BEZ_ABS
 *  - q QUADRATIC_CURVE_BEZ
 *  - T SHORTHAND_QUADRATIC_CURVE_BEZ_ABS
 *  - t SHORTHAND_QUADRATIC_CURVE_BEZ
 *  - A ELLIPTICAL_ARC_ABS
 *  - a ELLIPTICAL_ARC
 *  - Z ENDING_POINT
 *  - z ENDING_POINT
 */
@SuppressWarnings("javadoc")
public enum GPathType {
	/**
	 * Initial point (please do not use)
	 */
	INITIAL_POINT,
	
	/**
	 * Move the cursor to a new ABSOLUTE position without drawing
	 * Equivalent to 'M'
	 * @param New X position value
	 * @param New Y position value
	 */
	MOVE_TO_ABS,
	
	/**
	 * Move the cursor to a new RELATIVE position without drawing
	 * Equivalent to 'm'
	 * @param X displacement value
	 * @param Y displacement value
	 */
	MOVE_TO,
	
	/**
	 * Draw a line to a specific ABSOLUTE position from current position
	 * Equivalent to 'L'
	 * @param x X pointing position
	 * @param y Y pointing position
	 */
	LINE_TO_ABS,
	
	/**
	 * Draw a line to a specific RELATIVE position from current position
	 * Equivalent to 'l'
	 * @param x X pointing position
	 * @param y Y pointing position
	 */
	LINE_TO,
	
	/**
	 * Draw a horizontal line to a specific ABSOLUTE position from current position
	 * Equivalent to 'H'
	 * @param x X pointing position
	 */
	HORIZONTAL_ABS,
	
	/**
	 * Draw a horizontal line to a specific RELATIVE position from current position
	 * Equivalent to 'h'
	 * @param x X pointing position
	 */
	HORIZONTAL,
	
	/**
	 * Draw a vertical line to a specific ABSOLUTE position from current position
	 * Equivalent to 'V'
	 * @param y Y pointing position
	 */
	VERTICAL_ABS,
	
	/**
	 * Draw a vertical line to a specific RELATIVE position from current position
	 * Equivalent to 'v'
	 * @param y Y pointing position
	 */
	VERTICAL,
	
	/**
	 * Draws a cubic Bézier curve from the current point to (x,y) as ABSOLUTE positions
	 * Using (x1,y1) as the control point at the beginning of the curve and (x2,y2) as the control point at the end of the curve
	 * This uses ABSOLUTE coordinates
	 * Equivalent to 'C'
	 * @param x End X position
	 * @param y End Y position
	 * @param x1 First X control point
	 * @param y1 First Y control point
	 * @param x2 Second X control point
	 * @param y2 Second Y control point
	 */
	CURVE_BEZ_ABS,
	
	/**
	 * Draws a cubic Bézier curve from the current point to (x, y) as RELATIVE positions
	 * Using (x1, y1) as the control point at the beginning of the curve and (x2, y2) as the control point at the end of the curve
	 * This uses RELATIVE coordinates
	 * Equivalent to 'c'
	 * @param x End X position
	 * @param y End Y position
	 * @param x1 First X control point
	 * @param y1 First Y control point
	 * @param x2 Second X control point
	 * @param y2 Second Y control point
	 */
	CURVE_BEZ,
	
	/**
	 * Draws a cubic Bézier curve from the current point to (x, y) as ABSOLUTE positions
	 * It's like a 'C' Bezier curve with the first control point (x1, y1) that equals (x, y)
	 * Equivalent to 'S'
	 * @param x End X position (and first X control point)
	 * @param y End Y position (and first Y control point)
	 * @param x1 Second X control point
	 * @param y1 Second Y control point
	 */
	SHORTHAND_CURVE_BEZ_ABS,
	
	/**
	 * Draws a cubic Bézier curve from the current point to (x, y) as RELATIVE positions
	 * It's like a 'c' Bezier curve with the first control point (x1, y1) that equals (x, y)
	 * Equivalent to 's'
	 * @param x End X position (and first X control point)
	 * @param y End Y position (and first Y control point)
	 * @param x1 Second X control point
	 * @param y1 Second Y control point
	 */
	SHORTHAND_CURVE_BEZ,
	
	
	
	/** @TODO Add these followings elements description */
	/**
	 * Equivalent to 'Q'
	 */
	QUADRATIC_CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 'q'
	 */
	QUADRATIC_CURVE_BEZ,
	
	/**
	 * Equivalent to 'T'
	 */
	SHORTHAND_QUADRATIC_CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 't'
	 */
	SHORTHAND_QUADRATIC_CURVE_BEZ,
	
	/**
	 * Equivalent to 'A'
	 */
	ELLIPTICAL_ARC_ABS,
	
	/**
	 * Equivalent to 'a'
	 */
	ELLIPTICAL_ARC,
	
	/**
	 * Ending point (please do not use, equivalent to 'Z' and 'z') => see {@code close()} method
	 */
	ENDING_POINT;
	
	
	
	
	
	
	/**
	 * Converts any path type (as one letter) to a GPathType
	 * @param id ID of the path type
	 * @return the GPathType
	 */
	public static GPathType toPathType(String id) {
		switch (id) {
			case "M":
				return MOVE_TO_ABS;
				
			case "m":
				return MOVE_TO;
				
			case "L":
				return LINE_TO_ABS;
				
			case "l":
				return LINE_TO;
				
			case "H":
				return HORIZONTAL_ABS;
				
			case "h":
				return HORIZONTAL;
				
			case "V":
				return VERTICAL_ABS;
				
			case "v":
				return VERTICAL;
				
			case "C":
				return CURVE_BEZ_ABS;
				
			case "c":
				return CURVE_BEZ;
				
			case "S":
				return SHORTHAND_CURVE_BEZ_ABS;
				
			case "s":
				return SHORTHAND_CURVE_BEZ;
				
			case "Q":
				return QUADRATIC_CURVE_BEZ_ABS;
				
			case "q":
				return QUADRATIC_CURVE_BEZ;
				
			case "T":
				return SHORTHAND_QUADRATIC_CURVE_BEZ_ABS;
				
			case "t":
				return SHORTHAND_QUADRATIC_CURVE_BEZ;
				
			case "A":
				return ELLIPTICAL_ARC_ABS;
				
			case "a":
				return ELLIPTICAL_ARC;
				
			case "Z":
				return ENDING_POINT;
				
			case "z":
				return ENDING_POINT;
				
	
			default:
				Logger.err("Unknown PathType id " + id, "GPathType::toPathType()", false);
				return null;
		}
	}
}
