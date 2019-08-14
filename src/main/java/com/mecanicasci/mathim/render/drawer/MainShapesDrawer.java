package com.mecanicasci.mathim.render.drawer;

import com.mecanicasci.mathim.config.Constants;

/**
 * Based on the algorithm from <a href="http://members.chello.at/~easyfilter/bresenham.html">http://members.chello.at/~easyfilter/bresenham.html</a>.
 * Extended with morphologic dilatations.
 */
public final class MainShapesDrawer {
	/**
	 * Draw a line from RELATIVE locations @TODO implements thickness
	 * @param pixels Pixel array that needs to be updated
	 * @param xInit Initial X line position
	 * @param yInit Initial Y line position
	 * @param xEnd End X line position
	 * @param yEnd End Y line position
	 * @param thickness Thickness of the line (1 by default)
	 * @param showPercent Percentage of the animation shown
	 */
	public static void drawLine(int[] pixels, double xInit, double yInit, double xEnd, double yEnd, int thickness, float showPercent) {
		MainShapesDrawer.drawSimpleLine(
			pixels,
			(int) (xInit / Constants.RELATIVE_WIDTH  * Constants.WIDTH),
			(int) (yInit / Constants.RELATIVE_HEIGHT * Constants.HEIGHT),
			(int) (xEnd  / Constants.RELATIVE_WIDTH  * Constants.WIDTH),
			(int) (yEnd  / Constants.RELATIVE_HEIGHT * Constants.HEIGHT)
		);
	}
	
	
	/**
	 * Draws a bezier curve to the pixel array
	 * @param pixels Pixel array that needs to be updated
	 * @param xInit Initial X curve position
	 * @param yInit Initial Y curve position
	 * @param xEnd Final X curve position
	 * @param yEnd Final Y curve position
	 * @param x1 First X control point
	 * @param y1 First Y control point
	 * @param x2 Second X control point
	 * @param y2 Second Y control point
	 * @param thickness Thickness of the curve (1 by default)
	 * @param showPercent Percentage of the animation shown
	 */
	public static void drawBezierCurve(int[] pixels, double xInit, double yInit, double xEnd, double yEnd, double x1, double y1, double x2, double y2, int thickness, float showPercent) {
		for (float t = 0; t <= showPercent; t += Constants.BEZIER_ITERATION_SIZE) {
			double x = Math.pow(1 - t, 3) * xInit + 3 * t * Math.pow(1 - t, 2) * x1 + 3 * t*t * (1 - t) * x2 + t*t*t * xEnd;
			double y = Math.pow(1 - t, 3) * yInit + 3 * t * Math.pow(1 - t, 2) * y1 + 3 * t*t * (1 - t) * y2 + t*t*t * yEnd;
			
			MainShapesDrawer.drawLine(pixels, xInit, yInit, xEnd, yEnd, thickness, showPercent);
		}
	}
	
	
	
	
	
	
	/**
	 * Draw a line from ABSOLUTE locations
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
		
		double dx =  Math.abs(xEnd - xInit), sx = xInit < xEnd ? 1 : -1;
		double dy = -Math.abs(yEnd - yInit), sy = yInit < yEnd ? 1 : -1; 
		double err = dx + dy, e2; /* error value e_xy */
		
		while(true) {
			PixelDrawer.setPixel(pixels, xInit, yInit, r, g, b, a);
			
			if (xInit == xEnd && yInit == yEnd) break;
			
			e2 = 2 * err;
			
			if (e2 >= dy) { err += dy; xInit += sx; } /* e_xy + e_x > 0 */
			if (e2 <= dx) { err += dx; yInit += sy; } /* e_xy + e_y < 0 */
		}
	}
}
