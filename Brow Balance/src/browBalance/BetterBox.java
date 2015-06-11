package browBalance;

import fisica.FBox;

public class BetterBox 
{
	public FBox b;
	public boolean inWorld;
	
	public BetterBox(FBox box, boolean inWrld)
	{
		inWorld = inWrld;
		b = box;
	}
}
