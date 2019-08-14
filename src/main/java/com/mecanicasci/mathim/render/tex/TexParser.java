package com.mecanicasci.mathim.render.tex;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mecanicasci.mathim.utils.Logger;


public class TexParser {
	public static ArrayList<TexUseElement> parseSVGTex(String res) {
		try {
			// remove first white spaces
			res = res.substring(1).replaceAll("[^\\x20-\\x7e]", "");
			
			
			// parse svg
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(res));
			Document doc = builder.parse(is);
			
			
			// List of all tex elements in order
			String viewBox = res.split("viewBox='")[1].split("'")[0];
			ArrayList<TexUseElement> pathToId = new ArrayList<TexUseElement>();
			NodeList nList = doc.getElementsByTagName("g");
			
			for (int i = 0; i < nList.getLength(); i++) {
				NodeList paths = nList.item(i).getChildNodes();
				for (int j = 0; j < paths.getLength(); j++) {
					Node item = paths.item(j);
					if(item.getNodeName().equals("use")) {
						String[] path = TexUseElementUtils.getPathFromId(item.getAttributes().getNamedItem("xlink:href").getNodeValue(), doc).split("Z");
						
						for (int k = 0; k < path.length; k++) {
							pathToId.add(new TexUseElement(
								Double.parseDouble(item.getAttributes().getNamedItem("x").getNodeValue()),
								Double.parseDouble(item.getAttributes().getNamedItem("y").getNodeValue()),
								viewBox,
								path[k] + "Z"
							));
						}
					}
					else
						Logger.warn("The SVG element " + item.getNodeName() + " is undefined", "TexParser::parseSVGTex()");
				}
			}
			
			return pathToId;
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			Logger.err("Error while parsing SVG", e, "GTex::GTex()", false);
			return null;
		}
	}
}
