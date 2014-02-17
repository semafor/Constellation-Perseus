package no.jgdx.perseus.ships;

import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player. A HqShip on creation will yield
 * an element with which the player can advance the game quickly without having
 * to build something as the first step in the game.
 * 
<<<<<<< HEAD
 * 
 * 
 * @author pgd
 * 
 *         What the HqShip yields can depend on race/scenario/etc, so let us be
 *         flexible and
 * 
=======
 * What the HqShip yields can depend on race/scenario/etc, so let us be flexible
 *  
>>>>>>> ab0cfcc33a962d452d2ad6247a0c9a8099623bea
 * @author pgd, jgd
 * 
 * 
 */
public class HqShip extends Ship {

	private Harvester harvester;
	private final Map<Allotrope, Integer> assets;

	public HqShip(String name, Position position) {
		this(name, position, null);
	}

	public HqShip(String name, Position position, GameObject yield) {
		super(name, ShipClassification.HQ, position);

		// make game produce whatever should be yielded to the player upon
		// creation

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
