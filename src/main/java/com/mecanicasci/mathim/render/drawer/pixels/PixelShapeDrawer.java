package com.mecanicasci.mathim.render.drawer.pixels;

import com.mecanicasci.mathim.utils.Constants;

/** Draws certain very basic shapes as pixels */
public class PixelShapeDrawer extends PixelDrawer {
	/**
	 * Add a vertex to the pixels
	 * @param pixels Pixels array list
	 * @param v1 First point of the vertex
	 * @param v2 Second point of the vertex
	 * @param r Red color value
	 * @param g Green color value
	 * @param b Blue color value
	 * @param a Alpha color valye
	 */
	public static void drawVertex(int[] pixels, float[] v1, float[] v2, int r, int g, int b, int a) {
		int x1 = (int) (v1[0] / Constants.RELATIVE_WIDTH  * Constants.WIDTH);
		int y1 = (int) (v1[1] / Constants.RELATIVE_HEIGHT * Constants.HEIGHT);
		// int z1 = v1[2];
		
		int x2 = (int) (v2[0] / Constants.RELATIVE_WIDTH  * Constants.WIDTH);
		int y2 = (int) (v2[1] / Constants.RELATIVE_HEIGHT * Constants.HEIGHT);
		// int z2 = v2[2];
		
		
		if       (x1  > x2) drawHorizontalSimpleLine(pixels, x2, y2, x1, y1, r, g, b, a);
		else if  (x1  < x2) drawHorizontalSimpleLine(pixels, x1, y1, x2, y2, r, g, b, a);
		else { // x1 == x2
			if      (y1 > y2) drawVerticalSimpleLine(pixels, x2, y2, x1, y1, r, g, b, a);
			else if (y1 < y2) drawVerticalSimpleLine(pixels, x1, y1, x2, y2, r, g, b, a);
			else              setPixel				(pixels, x1, y1,         r, g, b, a); // draw a point
		}
	}
}
