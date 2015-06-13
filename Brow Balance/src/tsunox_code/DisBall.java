package tsunox_code;

import fisica.FCircle;

/**
 * DisBall class - to handle ball spawning in Tsunox game
 * 
 * @author Anish Visaria and Eitan Zlatin
 *
 */
public class DisBall {
	
	private long lifeTime;
	private long startTime;
	public FCircle circ;
	
	/**
	 * Constructs DisBall given FCircle and lifetime in milliseconds.
	 * @param ball - FCircle object
	 * @param lifetime - lifetime in milliseconds
	 */
	public DisBall(FCircle ball, long lifetime) {
		lifeTime = lifetime;
		circ = ball;
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Returns whether ball should be in existence in world based on lifetime.
	 * @return lifetime is over or not
	 */
	public boolean checkGone() {
		long elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime > lifeTime)
			return true;
		return false;
	}
}
