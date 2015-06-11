package browBalance;

import fisica.FCircle;

public class DisBall
{
	private long lifeTime;
	private long startTime;
	public FCircle circ;
	
	public DisBall(FCircle ball, long lifetime)// Life time in millis
	{
		lifeTime = lifetime;
		circ = ball;
		startTime = System.currentTimeMillis();
	}
	
	public boolean checkGone()
	{
		long elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime > lifeTime)
		{
			return true;
		}
		return false;
	}
}
