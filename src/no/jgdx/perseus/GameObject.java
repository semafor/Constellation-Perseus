package no.jgdx.perseus;

public interface GameObject {

	/**
	 * Tick, time is the absolute game time, which might overflow. lol, not!
	 * 
	 * @param time
	 */
	void tick(long time);
}
