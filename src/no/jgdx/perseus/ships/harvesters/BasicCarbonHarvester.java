package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.HqShip;

public class BasicCarbonHarvester extends Harvester {

	public static final long COOLDOWN_TIME = 6000L; // 6 sec
	public static final int CAPACITY = 1000;

	public BasicCarbonHarvester(Position pos, HqShip hq) {
		super(pos, HarvesterClassification.CARBON_COLLECTOR, COOLDOWN_TIME, CAPACITY, hq);
	}

	@Override
	public void tick(long time) {
		super.tick(time);
		if (!isReadyToJump())
			return;

		if (isFull()) {
			jumpTo(getDefaultHq().getPosition());
		} else if (isEmpty()) {
			setStar(Star.ATLAS);
		}
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += " \t harvested: " + getAmount();
		return s;
	}
}
