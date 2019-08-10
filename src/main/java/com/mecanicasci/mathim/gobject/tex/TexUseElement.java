package com.mecanicasci.mathim.gobject.tex;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
	 * @param linkHref Relative href of the element
	 * @param doc Main XML Document
	 * @param viewBox ViewBox of the xml
	 */
	public TexUseElement(float x, float y, String linkHref, Document doc, String viewBox) {
		this.elementX = x;
		this.elementY = y;
		this.path = this.getPathFromId(linkHref, doc);
		
		String[] viewB = viewBox.split(" ");
		this.width   = Float.parseFloat(viewB[2]);
		this.height  = Float.parseFloat(viewB[3]);
		this.svgMinX = Float.parseFloat(viewB[0]);
		this.svgMinY = Float.parseFloat(viewB[1]);
	}

	
	
	/**
	 * Generate path from this TexElement
	 * @param gTexParent From GTex object parent
	 */
	public void generatePath(GTex gTexParent) {
//		gTex.newPath(gTex, elementX - 50 / 2, elementY - 50 / 2)
//			.add(GPathType.LINE_TO,  50,  0, 5)
//			.add(GPathType.LINE_TO,  0,  50)
//			.add(GPathType.LINE_TO, -50,  0)
//			.close();
	}
	
	
	
	
	
	/**
	 * Get the SVG String path from the link
	 * @param linkHref
	 * @param doc
	 */
	private String getPathFromId(String linkHref, Document doc) {
		NodeList defs = doc.getElementsByTagName("defs");
		
		for (int i = 0; i < defs.getLength(); i++) {
			NodeList paths = defs.item(i).getChildNodes();
			for (int j = 0; j < paths.getLength(); j++) {
				Node item = paths.item(j);
				if(item.getNodeName().equals("path") && item.getAttributes().getNamedItem("id").getNodeValue().equals(linkHref.split("#")[1])) {
					return item.getAttributes().getNamedItem("d").getNodeValue();
				}
			}
		}
		
		return "";
	}
}
