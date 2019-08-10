package com.mecanicasci.mathim.render.tex;

import java.util.ArrayList;

import com.mecanicasci.mathim.config.Constants;
import com.mecanicasci.mathim.gobject.tex.GTex;
import com.mecanicasci.mathim.render.path.GPath;
import com.mecanicasci.mathim.render.path.GPathType;
import com.mecanicasci.mathim.utils.MathUtils;


/**
 * Tex Element for Rendering equations SVG
 * Please note that (considering left element vertex) :
 * 	FROM (0,0) => Left-side-corner
 * 	IF   svgMinX  = 10 (offset by 10 to the left)
 * 	AND  elementX = 12 (offset by 12 to the right)
 * 	THEN it will be drawn on  x = 12 - 10 = 2 (relative from the left) => means a little bit offsetted from the left
 */
public class TexUseElement {
	/** Main SVG relative width  (equivalent to 100% width) */
	private float width;
	/** Main SVG relative height (equivalent to 100% height) */
	private float height;
	
	/** SVG initial relative X offset (from (0,0) => top left corner) AND inverted (lower means right) */
	private float svgMinX;
	/** SVG initial relative Y offset (from (0,0) => top left corner) AND inverted (lower means bottom) */
	private float svgMinY;
	
	/** X relative element offset (not inverted : lower means left) */
	private float elementX;
	/** Y relative element offset (not inverted : lower means top) */
	private float elementY;
	
	/**
	 * Element String raw path
	 * Example path :
	 * 	"
	 * 	M4.07472 -2.291407
	 * 	H6.854296C6.993773 -2.291407 7.183064 -2.291407 7.183064 -2.49066
	 * 	S6.993773 -2.689913 6.854296 -2.689913
	 * 	Z
	 * 	"
	 */
	private String path;
	
	
	
	
	/**
	 * Tex Element from Tex SVG String
	 * @param x X element value
	 * @param y Y element value
	 * @param viewBox ViewBox of the xml
	 * @param path Path of the SVG element
	 */
	public TexUseElement(float x, float y, String viewBox, String path) {
		this.elementX = x;
		this.elementY = y;
		this.path = path;
		
		String[] viewB = viewBox.split(" ");
		this.width   = Float.parseFloat(viewB[2]);
		this.height  = Float.parseFloat(viewB[3]);
		this.svgMinX = Float.parseFloat(viewB[0]);
		this.svgMinY = Float.parseFloat(viewB[1]); 
	}

	



	/**
	 * Generate GPath from this TexElement
	 * @param gTexParent From GTex object parent
	 */
	public void generatePath(GTex gTexParent) {
		float relX = (elementX - svgMinX) / width  * Constants.RELATIVE_WIDTH;
		float relY = (elementY - svgMinY) / height * Constants.RELATIVE_HEIGHT;
		
		String[] pathSpl 		= path.split("");
		GPath p 		 		= gTexParent.newPath(gTexParent, relX, relY);
		String tmpStringToFloat = "";
		
		int letterId 			= 0;
		ArrayList<Float> params = null;
		String paramId 			= null;
		
		
		while(letterId < pathSpl.length) {
			String c = pathSpl[letterId];
			
			if(Character.isLetter(c.charAt(0))) { // c equals letter
				if(params != null && paramId != null) {// ifnot first char
					params.add(Float.parseFloat(tmpStringToFloat));
					p.add(GPathType.toPathType(paramId), MathUtils.toFloatArray(params));
					
					tmpStringToFloat = "";
				}
				
				params  = new ArrayList<Float>();
				paramId = c;
				
				if(c.equals("Z")) // close path
					p.close();
			}
			else if(params != null) { // c equals float
				if(c.equals(" ")) {
					params.add(Float.parseFloat(tmpStringToFloat));
					tmpStringToFloat = "";
				}
				else
					tmpStringToFloat += c;
			}
			
			letterId++;
		}
	}
}
