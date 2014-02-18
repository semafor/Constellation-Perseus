package no.jgdx.perseus.ships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.GameObjectState;
import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player.
 * 
 * @author pgd
 * 
 */
public class HqShip extends Ship {

	/**
	 * This is the star the HqShip is orbiting. Might be null.
	 */
	private Star star;

	/**
	 * The list of all harvesters this Hq operates. Note that this is not the
	 * same as all the harvesters a player has.
	 */
	private final List<Harvester> harvesters;

	/**
	 * The assets owned by this hq.
	 */
	private final Map<Allotrope, Integer> assets;

	public HqShip(String name, Position position) {
		this(name, position, null);
	}

	public HqShip(String name, Position position, GameObject yield) {
		super(name, ShipClassification.HQ, position);

		harvesters = new ArrayList<>();
		// adds the default harvester for this type of hq

		this.assets = new HashMap<Allotrope, Integer>();
		for (Allotrope a : Allotrope.values()) {
			assets.put(a, 0);
		}
	}

	/**
	 * Sets the star this hq is assigned to, updates position to reflect that of
	 * the star
	 * 
	 * @param star
	 */
	public void setStar(Star star) {
		this.star = star;
		setPosition(star.getPosition());
	}

	public Star getStar() {
		return star;
	}

	public List<Harvester> getHarvesters() {
		return harvesters;
	}

	/**
	 * Adds a harvester to this hq's internal harvesters, sets star of harvester
	 * to be this hq's star.
	 * 
	 * @param harvester
	 */
	public void addHarvester(Harvester harvester) {
		harvesters.add(harvester);
		harvester.setStar(getStar());
		harvester.setState(GameObjectState.HARVESTING);
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

	@Override
	public String toString() {
		return "HQ, assets: " + assets.toString();
	}

}
