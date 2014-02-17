package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;

public abstract class Harvester extends Ship {

	/** The star it is connected to, might be null. */
	private Star star;

	public Harvester(Position pos) {
		super("Harvester", ShipClassification.HARVESTER, pos);
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	@Override
	public void tick(long time) {
	}

	@Override
	public String toString() {
		if (star == null)
			return "Homeless harvester: " + super.toString();
		return star + "-harvester: " + super.toString();
	}

}
