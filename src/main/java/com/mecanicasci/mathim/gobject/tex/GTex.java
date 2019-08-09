package com.mecanicasci.mathim.gobject.tex;

import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.render.tex.TexRenderer;

public class GTex extends GObject {
	public GTex(float x, float y, String content) {
		super(x, y);
		
		String contentSVG = TexRenderer.getSVGFromTex(content);
		
		// System.out.println(TexRenderer.getSVGFromTex(content));
	}
	
	
	
	@Override
	protected void generateVertices() {
		
	}
}
