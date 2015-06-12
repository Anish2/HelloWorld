package paddleBalance;

import fisica.FCircle;

public class DisBall {
	private long lifeTime;
	private long startTime;
	public FCircle circ;
	
	// Life time in millis
	public DisBall(FCircle ball, long lifetime) {
		lifeTime = lifetime;
		circ = ball;
		startTime = System.currentTimeMillis();
	}
	
	public boolean checkGone() {
		long elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime > lifeTime)
			return true;
		return false;
	}
}
