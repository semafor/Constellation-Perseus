package no.jgdx.perseus.ships;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player. A hq has an associated harvester,
 * which for the player is the same thing. However, the player can
 * upgrade/downgrade the harvester type during game play.
 * 
 * @author pgd
 * 
 */
public class HqShip extends Ship {

	private Harvester harvester;

	public HqShip(String name, Position position) {
		super(name, ShipClassification.HQ, position);
	}

	public Harvester getHarvester() {
		return harvester;
	}

	public void setHarvester(Harvester harvester) {
		this.harvester = harvester;
	}

	public void tick(long time) {
	};

}
