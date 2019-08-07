package com.mecanicasci.mathim.animations.apparition;

import java.util.ArrayList;

import com.mecanicasci.mathim.animations.Animation;
import com.mecanicasci.mathim.gobject.GObject;
import com.mecanicasci.mathim.gobject.elements.GSquare;
import com.mecanicasci.mathim.render.Renderer;
import com.mecanicasci.mathim.render.drawer.pixels.PixelShapeDrawer;
import com.mecanicasci.mathim.utils.Constants;

public class DrawElement extends Animation {
	/**
	 * Draw a simple element on screen by shape
	 * @param animLength
	 * @param gObject
	 */
	public DrawElement(AnimationLength animLength, GObject... gObject) {
		super(animLength, gObject);
	}

	
	
	@Override
	public int[] renderFrameAt(int[] pixels, float t, Renderer renderer) {
		for (GObject obj : gObjects) {
			/** @TODO Remove this */
			GSquare square = (GSquare) obj;
			square.rotate(Constants.PI / 300);
			
			ArrayList<float[]> vertices = obj.getVertices();
			
			for (int i = 1; i < vertices.size(); i++)
				PixelShapeDrawer.drawVertex(pixels, vertices.get(i - 1), vertices.get(i), 0, 0, 0, 255);
			
			if(obj.isClosedPath())
				PixelShapeDrawer.drawVertex(pixels, vertices.get(vertices.size() - 1), vertices.get(0), 0, 0, 0, 255);
		}
		
		return pixels;
	}
}
