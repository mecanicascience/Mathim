package com.mecanicasci.mathim;

import com.mecanicasci.mathim.animations.utils.AnimationLength;
import com.mecanicasci.mathim.animations.utils.AnimationList;
import com.mecanicasci.mathim.gobject.geometry.GCircle;
import com.mecanicasci.mathim.gobject.geometry.GSquare;
import com.mecanicasci.mathim.render.Scene;
import com.mecanicasci.mathim.utils.DebugLevel;

public class MainClass {
	public static void main(String[] args) {
		GSquare square = new GSquare(50, 50, 50, 50);
		square
			.init();
//			.scale(1f)
//			.rotate(-Constants.PI / 4);
		
		GCircle circle = new GCircle(50, 50, 10);
		circle
			.init();
		
		
		Scene
			.init("SimpleTest", DebugLevel.KEEP_ALL_TMP)
			.animate(AnimationList.STATIC_ANIMATION, AnimationLength.LONG, square)
			.play();
		
		/** @TODO Show rendering loading bar */
		
		
		
//		Render render = new Render();
//		try {
//			render.render();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			String name = "6444844521";
//			RenderTex render = new RenderTex(name);
//			// render.getSVG("\\sum_{k=1}^\\infty {1 \\over k^2} = {\\pi^2 \\over 6} \\text{ Ceci est un test}");
//			String content = render.getSVGFileContent();
//			
//			ParseSVGToPoints parseSVG = new ParseSVGToPoints(content);
//			System.out.println(content);
//			parseSVG.parseSVG();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
