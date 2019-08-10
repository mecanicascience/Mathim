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
	 * Equivalent to 'M'
	 */
	MOVE_TO_ABS,
	
	/**
	 * Equivalent to 'm'
	 */
	MOVE_TO,
	
	/**
	 * Equivalent to 'L'
	 */
	LINE_TO_ABS,
	
	/**
	 * Draw a line to a specific RELATIVE position
	 * Equivalent to 'l'
	 * @param x Relative X pointing position
	 * @param y Relative Y pointing position
	 * @param thickness (Optional) thickness of the line
	 */
	LINE_TO,
	
	/**
	 * Equivalent to 'H'
	 */
	HORIZONTAL_ABS,
	
	/**
	 * Equivalent to 'h'
	 */
	HORIZONTAL,
	
	/**
	 * Equivalent to 'V'
	 */
	VERTICAL_ABS,
	
	/**
	 * Equivalent to 'v'
	 */
	VERTICAL,
	
	/**
	 * Equivalent to 'C'
	 */
	CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 'c'
	 */
	CURVE_BEZ,
	
	/**
	 * Equivalent to 'S'
	 */
	SHORTHAND_CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 's'
	 */
	SHORTHAND_CURVE_BEZ,
	
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
