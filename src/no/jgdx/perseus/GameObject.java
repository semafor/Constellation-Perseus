package no.jgdx.perseus;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.players.Player;

/**
 * Every object in the game, including stars, ships and stations.
 * 
 * Can be destroyed, and have a concept of time and position.
 * 
 * @author pgd
 * 
 */
public interface GameObject {

	/**
	 * The current position of the object.
	 * 
	 * @return current position
	 */
	@Deprecated
	Position getPosition();

	/**
	 * Set current position of the object.
	 * 
	 * @param position
	 *            new position
	 */
	@Deprecated
	void setPosition(Position position);

	/**
	 * Tick, time is the absolute game time, which might overflow. lol, not!
	 * 
	 * @param time
	 *            the current time in millisecond, since game started.
	 */
	void tick(long time);

	/**
	 * Get the damage of this object. 1 means fully operational, 0 means fully
	 * destroyed.
	 * 
	 * @return the damage of this object, [0,1] where 1 is fully operational, 0
	 *         is destroyed.
	 */
	double getDamage();

	/**
	 * Some identifier. Every object must provide some identifier
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Might return null, but if ship or station returns player
	 * 
	 * @return
	 */
	Player getOwner();
}
