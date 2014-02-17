package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;

public abstract class Harvester extends Ship {

	/** The star it is connected to, might be null. */
	private Star star;

	private final HarvesterClassification hc;

	private int amount = 0;

	public Harvester(Position pos, HarvesterClassification hc) {
		super("Harvester", ShipClassification.HARVESTER, pos);
		this.hc = hc;
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

	public void setStar(Star star) {
		this.star = star;
	}

	@Override
	public void tick(long time) {
		if (star != null) {
			if (star.getStarClassification().getAllotrope() == hc.getAllotrope()) {
				amount += hc.getHarvestSpeed();
			}
		}
	}

	@Override
	public String toString() {
		if (star == null)
			return "Homeless " + hc + super.toString();
		return star + "-" + hc + ": " + super.toString();
	}

}
