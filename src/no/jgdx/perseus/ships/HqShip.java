package no.jgdx.perseus.ships;

import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player. A hq has an associated harvester,
 * which for the player is the same thing. However, the player can
 * upgrade/downgrade the harvester type during game play.
 * 
 * 
 * @author pgd
 * 
 */
public class HqShip extends Ship {

	private Harvester harvester;
	private final Map<Allotrope, Integer> assets;

	public HqShip(String name, Position position) {
		super(name, ShipClassification.HQ, position);
		this.assets = new HashMap<Allotrope, Integer>();
		for (Allotrope a : Allotrope.values()) {
			assets.put(a, 0);
		}
	}

	public Harvester getHarvester() {
		return harvester;
	}

	public void setHarvester(Harvester harvester) {
		this.harvester = harvester;
	}

	public void tick(long time) {
		int harvested = harvester.resetAmount();
		Allotrope a = harvester.getHarvesterClassification().getAllotrope();
		assets.put(a, assets.get(harvester.getHarvesterClassification().getAllotrope()) + harvested);
	};

}
