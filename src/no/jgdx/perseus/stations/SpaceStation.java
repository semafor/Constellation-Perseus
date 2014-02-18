package no.jgdx.perseus.stations;

import java.util.Collection;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.Ship;

public abstract class SpaceStation implements GameObject {

	/** double between 1 and 0. 0 means totally destroyed! */
	protected double damage = 1; // 100%
	protected Position position;

	public SpaceStation(Position pos) {
		this.position = pos;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	/**
	 * True if and only if is under construction.
	 * 
	 * @return construction status
	 */
	public abstract boolean isUnderConstruction();

	/**
	 * Returns a collection of the ships this SpaceStation can construct
	 * 
	 * @return collection of ships we can construct
	 */
	public abstract Collection<Ship> constructableShips();

	/**
	 * Returns a collection of the stations this SpaceStation can construct
	 * 
	 * @return collection of stations we can construct
	 */
	public abstract Collection<SpaceStation> constructableSpaceStations();

	/**
	 * 
	 * @return
	 */
	public double getDamage() {
		return damage;
	}

}
