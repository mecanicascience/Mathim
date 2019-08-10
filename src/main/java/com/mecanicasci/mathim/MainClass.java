package com.mecanicasci.mathim;

import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.animations.utils.AnimationList;
import com.mecanicasci.mathim.gobject.geometry.GSquare;
import com.mecanicasci.mathim.gobject.tex.GTex;
import com.mecanicasci.mathim.render.Scene;
import com.mecanicasci.mathim.utils.DebugLevel;

public class MainClass {
	public static void main(String[] args) {
		// ==== Scene ====
		Scene scene = Scene.init("SimpleTest", DebugLevel.SHOW_ALL);
		
		
		// ==== GObjects ====
		GSquare square = new GSquare(50, 50, 50, 50);
		square.init();
		
		GTex tex = new GTex(50, 50, "\\int_0^33 x \\times 5s + 2");
		tex.init();
		
		
		// ==== Animations ====
		scene
			.animate(AnimationList.STATIC_ANIMATION, AnimationLength.LONG, tex)
			.play();
	}
}
