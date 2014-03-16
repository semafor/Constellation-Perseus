package no.jgdx.perseus.ships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.GameObjectState;
import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player.
 * 
 * @author pgd
 * 
 */
public class HqShip extends Ship {

	public final static long COOLDOWN_TIME = 10L * 1000; // 10 seconds

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

	public HqShip(String name, Position position, Player owner) {
		this(name, position, null, owner);
	}

	public HqShip(String name, Position position, GameObject yield, Player owner) {
		super(name, ShipClassification.HQ, position, COOLDOWN_TIME, owner, new HashMap<Allotrope, Integer>());

		harvesters = new ArrayList<>();
		// adds the default harvester for this type of hq

		this.assets = new HashMap<Allotrope, Integer>();
		for (Allotrope a : Allotrope.values()) {
			assets.put(a, 0);
			if (a == Allotrope.OXYGEN) {
				assets.put(a, 1000);
			}
			if (a == Allotrope.CARBON) {
				assets.put(a, 800);
			}
			if (a == Allotrope.SELENIUM) {
				assets.put(a, 7000);
			}
		}
	}

	public boolean buy(Ship ship) {
		synchronized (assets) {
			assert ship.getOwner() == getOwner() : "Owner of ship is not owner of hq buying";

			Map<Allotrope, Integer> shipprice = ship.getPrice();
			for (Entry<Allotrope, Integer> e : shipprice.entrySet()) {
				if (e.getValue() > getAsset(e.getKey())) {
					return false;
				}
			}
			for (Entry<Allotrope, Integer> e : shipprice.entrySet()) {
				if (!withdrawAsset(e.getKey(), e.getValue())) {
					assert false : "Suddenly has no cash left: " + shipprice + " vs " + assets + " on input " + ship;
					return false;
				}
			}
		}
		return true;
	}

	public boolean canAfford(Ship ship) {
		synchronized (assets) {
			assert ship.getOwner() == getOwner() : "Owner of ship is not owner of hq buying";

			Map<Allotrope, Integer> shipprice = ship.getPrice();
			for (Entry<Allotrope, Integer> e : shipprice.entrySet()) {
				if (e.getValue() > getAsset(e.getKey())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean withdrawAsset(Allotrope allotrope, int amount) {
		if (amount <= 0)
			return true;
		synchronized (assets) {
			if (assets.containsKey(allotrope)) {
				int cash = assets.get(allotrope);
				if (cash < amount)
					return false;
				assets.put(allotrope, cash - amount);
				return true;
			} else {
				return false;
			}
		}
	}

	public int getAsset(Allotrope allotrope) {
		synchronized (assets) {
			if (assets.containsKey(allotrope))
				return assets.get(allotrope);
			return 0;
		}
	}

	/**
	 * Sets the star this hq is assigned to, updates position to reflect that of
	 * the star
	 * 
	 * @param star
	 */
	public boolean setStar(Star star) {
		// if (star == null) {
		// this.star = null;
		// return true;
		// }
		//
		// this.star = star;
		// return jumpTo(star.getPosition());
		return true;
	}

	public Star getStar() {
		return star;
	}

	public List<Harvester> getHarvesters() {
		return harvesters;
	}

	public void empty(Harvester harvester) {
		Allotrope a = harvester.getHarvesterClassification().getAllotrope();
		int mined = harvester.resetAmount();
		addAllotrope(a, mined);
		System.out.println("HQ:" + assets);
	}

	private void addAllotrope(Allotrope a, int amount) {
		if (assets.containsKey(a)) {
			int current = assets.get(a);
			assets.put(a, current + amount);
		} else {
			assets.put(a, amount);
		}
	}
	
	public int getAllotrope(Allotrope a) {
		int total = 0;
		if(assets.containsKey(a)) {
			total = total + assets.get(a);
		}
		return total;
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
		List<Harvester> l = getHarvesters();
		for (Harvester h : l) {
			if (h.destroyed()) {
				l.remove(h);
			} else if (h.harvesting()) {
				int harvested = h.resetAmount();
				Allotrope a = h.getHarvesterClassification().getAllotrope();
				addAllotrope(a, harvested);
			}
		}
	};

	@Override
	public String toString() {
		return "HQ, assets: " + assets.toString();
	}

}
