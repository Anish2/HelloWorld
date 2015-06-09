package samples;

import processing.core.PApplet;

public class BounceText extends PApplet {

	class BouncyWord {
		final static short DIM = 80;
		final static int INK = 0100 << 030;

		String word;
		float px, py, vx, vy;

		BouncyWord(String iword, float ivy, float ipx, float ipy) {
			word = iword;
			vy = ivy;
			px = ipx;
			py = ipy;
		}

		void bounce() {
			if ((py += vy) >= height - (DIM>>2) | py < DIM>>2)  vy *= -1;
			text(word, px, py);
		}
	}

	static final int NUM = 3;
	BouncyWord[] words = new BouncyWord[NUM];

	public void setup() {
		size(300, 200);
		smooth();

		fill(BouncyWord.INK);
		textAlign(LEFT, CENTER);
		textFont(createFont("Univers-Bold", BouncyWord.DIM));

		words[0] = new BouncyWord("History", (float) -1.5, 0, 50);
		words[1] = new BouncyWord("Design", (float) 2.3, 0, 100);
		words[2] = new BouncyWord("Studio", -3, 0, 150);
	}

	public void draw() {
		background(-1);
		for (BouncyWord w: words)  w.bounce();
	}



}
