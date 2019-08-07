package com.mecanicasci.mathim;

import com.mecanicasci.mathim.animations.Animation.AnimationLength;
import com.mecanicasci.mathim.animations.Animation.AnimationList;
import com.mecanicasci.mathim.gobject.elements.GSquare;
import com.mecanicasci.mathim.render.Scene;
import com.mecanicasci.mathim.utils.Constants;

public class MainClass {
	public static void main(String[] args) {
		GSquare square = new GSquare(50, 50, 50, 50);
		square
			.init()
			.scale(1f)
			.rotate(-Constants.PI / 4);
		
		
		Scene
			.init("SimpleTest")
			.animate(AnimationList.DRAW_ELEMENT, AnimationLength.LONG, square) /** @TODO Show rendering loading bar */
			.animate(AnimationList.DRAW_ELEMENT, AnimationLength.getDefault(), square)
			.play();
		
		
		
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
