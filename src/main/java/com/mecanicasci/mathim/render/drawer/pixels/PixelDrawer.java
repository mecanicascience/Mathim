package com.mecanicasci.mathim.render.drawer.pixels;

import com.mecanicasci.mathim.utils.Constants;


/** Draws specific pixels on the screen */
public class PixelDrawer {
	/** @return an array full of white pixels */
	public static int[] getBlankPixelsArray() {
		int[] pixels = new int[4 * Constants.WIDTH * Constants.HEIGHT];
		
		for (int i = 0; i < pixels.length - 3; i += 4) {
			pixels[i    ] = 255;
			pixels[i + 1] = 255;
			pixels[i + 2] = 255;
			pixels[i + 3] = 255;
		}
		
		return pixels;
	}
	
	
	
	
	/**
	 * Draw an horizontal simple line
	 * @param pixels Pixels array
	 * @param x1 From X
	 * @param y1 From Y
	 * @param x2 To X
	 * @param y2 To Y
	 * @param r Red color
	 * @param g Green color
	 * @param b Blue color
	 * @param a Alpha value
	 */
	protected static void drawHorizontalSimpleLine(int[] pixels, int x1, int y1, int x2, int y2, int r, int g, int b, int a) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		
		int y = -1;
		for (int x = x1; x <= x2; x++) {
			y = y1 + dy * (x - x1) / dx;
			setPixel(pixels, x, y, r, g, b, a);
		}
	}
	
	
	/**
	 * Draw a simple vertical line
	 * @param pixels Pixels array
	 * @param x1 From X
	 * @param y1 From Y
	 * @param x2 To X
	 * @param y2 To Y
	 * @param r Red color
	 * @param g Green color
	 * @param b Blue color
	 * @param a Alpha value
	 */
	protected static void drawVerticalSimpleLine(int[] pixels, int x1, int y1, int x2, int y2, int r, int g, int b, int a) {
		for (int y = y1; y <= y2; y++) {
			setPixel(pixels, x1, y, r, g, b, a);
		}
	}
	 
	
	
	/**
	 * Set a specific pixel on the pixel array
	 * @param pixels
	 * @param x Absolute X pixel screen position
	 * @param y Absolute Y pixel screen position
	 * @param r Red pixel value   (0 <= r <= 255)
	 * @param g Green pixel value (0 <= g <= 255)
	 * @param b Blue pixel value  (0 <= b <= 255)
	 * @param a Alpha pixel value (0 <= a <= 255)
	 */
	protected static void setPixel(int[] pixels, int x, int y, int r, int g, int b, int a) {
		if(
			   x < 0
			|| y < 0
			|| x > Constants.RELATIVE_WIDTH * Constants.WIDTH
			|| y > Constants.RELATIVE_HEIGHT * Constants.HEIGHT
			|| r < 0 || r > 255
			|| g < 0 || g > 255
			|| b < 0 || b > 255
			|| a < 0 || a > 255
		) return;
		
		int index = (x + y * Constants.WIDTH) * 4;
		
		pixels[index    ] = r;
		pixels[index + 1] = g;
		pixels[index + 2] = b;
		pixels[index + 3] = a;
	}
}
