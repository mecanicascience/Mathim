package com.mecanicasci.mathim.render.tex;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class TexUseElementUtils {
	/**
	 * Get the SVG String path from the link
	 * @param linkHref Link in the document
	 * @param doc Document to fetch
	 * @return the path String
	 */
	public static String getPathFromId(String linkHref, Document doc) {
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
