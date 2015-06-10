package samples;

import processing.core.PApplet;
import processing.core.PFont;


public class Textify extends PApplet {

	PFont font;
	int ydirection = 1;  // Top to Bottom
	int startingMillis;
	final int durationTillMovement = 30000;

	class bouncyWord {
		String theWord;
		float px, py, vx, vy, ypos;
		bouncyWord(String iWord, float ivy, float ipx, float ipy) {
			theWord = iWord;
			px=ipx;
			vx=0;
			vy=ivy;
			py=ipy;
			frameRate(200);
			smooth();
		}
		void draw() {
			px+=vx;
			py+=vy;
			if (py<0) {
				py=0;
				vy=-vy;
			}
			if (py>height) {
				py=height;
				vy=-vy;
			}
			text(theWord, px, py);
		}
	}

	bouncyWord aa, bb, cc;

	public void setup() {
		size(300, 200);
		font = createFont("Univers-Bold", 86);
		textFont(font, 32);
		textAlign(LEFT);
		aa = new bouncyWord("History", (float) -.1, 0, 50);
		bb = new bouncyWord("Design", (float) .13, 0, 100);
		cc = new bouncyWord("Studio", (float) -.15, 0, 150);
		fill(0, 0, 0, 75);
		textSize(86);
	}

	public void draw() {
		background(255);
		aa.draw();
		bb.draw();
		cc.draw();
	}
}