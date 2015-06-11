package samples;

import processing.core.PApplet;

public class BounceText extends PApplet {


	public void setup() {
		size(300, 200);
		smooth();
		textAlign(LEFT, CENTER);
		textFont(createFont("C:\\Users\\Anish\\git\\HelloWorld\\Brow Balance\\src\\samples\\data\\Nosifer-Regular.ttf", 54));

	}

	public void draw() {
		background(0);
		text("You Won!",100,100);
	}



}
