package no.jgdx.perseus.ships;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player.
 * 
 * @author pgd
 * 
 */
public class HqShip extends Ship {

	private List<Harvester> harvesters;
	private final Map<Allotrope, Integer> assets;

	public HqShip(String name, Position position) {
		this(name, position, null);
	}

	public HqShip(String name, Position position, GameObject yield) {
		super(name, ShipClassification.HQ, position);

		this.assets = new HashMap<Allotrope, Integer>();
		for (Allotrope a : Allotrope.values()) {
			assets.put(a, 0);
		}

	}

	public List<Harvester> getHarvesters() {
		return harvesters;
	}

	public void setHarvesters(List<Harvester> harvesters) {
		this.harvesters = harvesters;
	}

	public void tick(long time) {
		int harvested = 0;
		List<Harvester> l = getHarvesters();

		for (Harvester h : l) {

			if (h.destroyed()) {
				l.remove(h);
			} else if (h.harvesting()) {
				harvested += h.resetAmount();
				Allotrope a = h.getHarvesterClassification().getAllotrope();
				assets.put(a, assets.get(h.getHarvesterClassification().getAllotrope()) + harvested);
			}

		}

	};

}
