package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;

public class BasicOxygenHarvester extends Harvester {

	public static final long COOLDOWN_TIME = 4000L; // 4 sec
	public static final int CAPACITY = 8000;

	public BasicOxygenHarvester(Position pos, HqShip hq) {
		super(pos, HarvesterClassification.OXYGEN_MINER, COOLDOWN_TIME, CAPACITY, hq);
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += " \t harvested: " + getAmount();
		return s;
	}
}
