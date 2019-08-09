package com.mecanicasci.mathim.render.path;

/**
 * Similar as SVG path parameters
 * Based on <a href="https://la-cascade.io/svg-la-syntaxe-path/">https://la-cascade.io/svg-la-syntaxe-path/</a>
 */
@SuppressWarnings("javadoc")
public enum GPathType {
	/**
	 * Initial point (please do not use)
	 */
	INITIAL_POINT,
	
	/**
	 * Ending point (please do not use, equivalent to 'Z' and 'z') => see {@code close()} method
	 */
	ENDING_POINT,
	
	
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
	CURVE_BEZ,
	
	/**
	 * Equivalent to 'c'
	 */
	CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 'S'
	 */
	SHORTHAND_CURVE_BEZ,
	
	/**
	 * Equivalent to 's'
	 */
	SHORTHAND_CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 'Q'
	 */
	QUADRATIC_CURVE_BEZ,
	
	/**
	 * Equivalent to 'q'
	 */
	QUADRATIC_CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 'T'
	 */
	SHORTHAND_QUADRATIC_CURVE_BEZ,
	
	/**
	 * Equivalent to 't'
	 */
	SHORTHAND_QUADRATIC_CURVE_BEZ_ABS,
	
	/**
	 * Equivalent to 'A'
	 */
	ELLIPTICAL_ARC,
	
	/**
	 * Equivalent to 'a'
	 */
	ELLIPTICAL_ARC_ABS
}
