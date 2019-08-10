package com.mecanicasci.mathim;

import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.animations.utils.AnimationList;
import com.mecanicasci.mathim.config.DebugLevel;
import com.mecanicasci.mathim.gobject.geometry.GSquare;
import com.mecanicasci.mathim.gobject.tex.GTex;
import com.mecanicasci.mathim.render.Scene;

public class MainClass {
	public static void main(String[] args) {
		// ==== Scene ====
		Scene scene = Scene.init("SimpleTest", DebugLevel.KEEP_ALL_TMP);
		
		
		// ==== GObjects ====
		GSquare square = new GSquare(50, 50, 50, 50);
		square.init();
		
		GTex tex = new GTex(50, 50, "\\frac{\\int_0^33 x \\times 5s}{5t \\times 2} + 2");
		tex.init();
		
		
		// ==== Animations ====
		scene
			.animate(AnimationList.STATIC_ANIMATION, AnimationLength.LONG, tex)
			.play();
	}
}
