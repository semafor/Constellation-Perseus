package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.GameObjectState;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;

public abstract class Harvester extends Ship {

	/** The star it is connected to, might be null. */
	private Star star;

	private final HarvesterClassification hc;

	private int amount = 0;

	private final int capacity;

	private HqShip defaulHq;

	public Harvester(Position pos, HarvesterClassification hc, long coolDownTime, int capacity, HqShip hq, Player owner) {
		super("Harvester", ShipClassification.HARVESTER, pos, coolDownTime, owner);
		this.hc = hc;
		this.capacity = capacity;
		this.defaulHq = hq;
	}

	protected HqShip getDefaultHq() {
		return defaulHq;
	}

	/**
	 * Returns in percentages, the amount vs capacity
	 * 
	 * @return
	 */
	public int getPercentage() {
		if (isFull())
			return 100;
		if (isEmpty())
			return 0;
		float cap = capacity;
		float am = amount;
		if (cap == 0) {
			return 0;
		}
		return (int) ((100f * am) / cap);
	}

	public boolean isFull() {
		return capacity == amount;
	}

	public boolean isEmpty() {
		return amount == 0;
	}

	public int getCapacity() {
		return capacity;
	}

	public final int resetAmount() {
		int a = amount;
		amount = 0;
		return a;
	}

	public HarvesterClassification getHarvesterClassification() {
		return hc;
	}

	public Star getStar() {
		return star;
	}

	public boolean setStar(Star star) {
		if (star == null) {
			star = null;
			return true;
		}
		if (star == this.star)
			return true;
		if (!isReadyToJump())
			return false;
		jumpTo(star.getPosition());
		this.star = star;

		System.out.println(getClass().getSimpleName() + "@" + star.getName());

		return true;
	}

	@Override
	public void tick(long time) {
		if (star != null) {
			if (star.getStarClassification().getAllotrope() == hc.getAllotrope()) {
				amount += hc.getHarvestSpeed();
				if (amount > capacity)
					amount = capacity;
			}
		}
	}

	protected int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		String home = (star == null) ? "Homeless " : star.toString();
		if (harvesting()) {
			return home + "-" + hc + " (harvesting)";
		} else {
			return home + "-" + hc + "(idle)";
		}
	}

	public boolean harvesting() {
		return getState() == GameObjectState.HARVESTING;
	}

}
