package com.mecanicasci.mathim.gobject.tex;

import java.util.ArrayList;

import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.render.tex.TexParser;
import com.mecanicasci.mathim.render.tex.TexRenderer;

public class GTex extends GObject {
	private ArrayList<TexUseElement> elements;
	
	public GTex(float x, float y, String content) {
		super(x, y);
		
		elements = TexParser.parseSVGTex(TexRenderer.getSVGFromTex(content));
	}
	


	@Override
	protected void generateVertices() {
		for (TexUseElement texElement : elements)
			texElement.generatePath(this);
	}
}
