package browBalance;

import processing.core.PApplet;

public class BrowMenu extends PApplet
{
	public static void main(String[] args)
	{
		BrowDisplay brow = new BrowDisplay(1,100,0);
		brow.paint(createGraphics(40,40));
	}
}
