package samples;

import processing.core.PApplet;

public class BounceText extends PApplet {


	public void setup() {
		size(300, 200);
		smooth();
		textAlign(LEFT, CENTER);
		textFont(createFont("H:\\git\\HelloWorld\\Brow Balance\\src\\samples\\BUBBLEBATH.ttf", 32));

	}

	public void draw() {
		background(0);
		text("test",10,10);
	}



}
