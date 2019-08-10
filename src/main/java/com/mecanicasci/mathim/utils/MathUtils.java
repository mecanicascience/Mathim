package com.mecanicasci.mathim.utils;

import java.util.ArrayList;

public final class MathUtils {
	/**
	 * Create a float array from an arrayList
	 * @param params ArrayList to convert
	 * @return the float array
	 */
	public static float[] toFloatArray(ArrayList<Float> params) {
		float[] floatArray = new float[params.size()];
		int i = 0;

		for (Float f : params)
		    floatArray[i++] = (f != null ? f : Float.NaN);
		
		return floatArray;
	}
}
