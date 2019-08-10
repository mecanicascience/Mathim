package com.mecanicasci.mathim.render.drawer;

import com.mecanicasci.mathim.config.Constants;

/**
 * Based on the algorithm from <a href="http://members.chello.at/~easyfilter/bresenham.html">http://members.chello.at/~easyfilter/bresenham.html</a>.
 * Extended with morphologic dilatations.
 */
public final class MainShapesDrawer {
	/**
	 * Draw a line from RELATIVE locations
	 * @param pixels Pixel array that needs to be updated
	 * @param xInit Initial X line position
	 * @param yInit Initial Y line position
	 * @param xEnd End X line position
	 * @param yEnd End Y line position
	 * @param thickness Thickness of the line (1 by default)
	 */
	public static void drawLine(int[] pixels, float xInit, float yInit, float xEnd, float yEnd, int thickness) {
		MainShapesDrawer.drawSimpleLine(
			pixels,
			(int) (xInit / Constants.RELATIVE_WIDTH  * Constants.WIDTH),
			(int) (yInit / Constants.RELATIVE_HEIGHT * Constants.HEIGHT),
			(int) (xEnd  / Constants.RELATIVE_WIDTH  * Constants.WIDTH),
			(int) (yEnd  / Constants.RELATIVE_HEIGHT * Constants.HEIGHT)
		);
	}
	
	
	
	
	/**
	 * Draw a line from ABSOLUTE locations
	 * @TODO implements thickness
	 * @param pixels Pixel array that needs to be updated
	 * @param xInit Initial X line position
	 * @param yInit Initial Y line position
	 * @param xEnd End X line position
	 * @param yEnd End Y line position
	 */
	private static void drawSimpleLine(int[] pixels, int xInit, int yInit, int xEnd, int yEnd) {
		int r = 0;
		int g = 0;
		int b = 0;
		int a = 255;
		
		float dx =  Math.abs(xEnd - xInit), sx = xInit < xEnd ? 1 : -1;
		float dy = -Math.abs(yEnd - yInit), sy = yInit < yEnd ? 1 : -1; 
		float err = dx + dy, e2; /* error value e_xy */
		
		while(true) {
			PixelDrawer.setPixel(pixels, xInit, yInit, r, g, b, a);
			
			if (xInit == xEnd && yInit == yEnd) break;
			
			e2 = 2 * err;
			
			if (e2 >= dy) { err += dy; xInit += sx; } /* e_xy + e_x > 0 */
			if (e2 <= dx) { err += dx; yInit += sy; } /* e_xy + e_y < 0 */
		}
	}
}
