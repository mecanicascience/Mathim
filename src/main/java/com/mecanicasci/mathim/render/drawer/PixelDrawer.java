package com.mecanicasci.mathim.render.drawer;

import com.mecanicasci.mathim.utils.Constants;

public final class PixelDrawer {
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
	 * Set a specific pixel on the pixel array (must be ABSOLUTE)
	 * @param pixels
	 * @param x Absolute X pixel screen position
	 * @param y Absolute Y pixel screen position
	 * @param r Red pixel value   (0 <= r <= 255)
	 * @param g Green pixel value (0 <= g <= 255)
	 * @param b Blue pixel value  (0 <= b <= 255)
	 * @param a Alpha pixel value (0 <= a <= 255)
	 */
	public static void setPixel(int[] pixels, int x, int y, int r, int g, int b, int a) {
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
