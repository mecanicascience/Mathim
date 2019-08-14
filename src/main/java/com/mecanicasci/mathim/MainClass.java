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
		
		GTex tex2 = new GTex(50, 50, "\\int_3 \\frac{6/7x} + 8x");
		tex2.init();
		
		
		// ==== Animations ====
		scene
			.animate(AnimationList.STATIC_ANIMATION, AnimationLength.ONE_FRAME, tex2)
			.play();
	}
}
