package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;

public class BasicOxygenHarvester extends Harvester {

	public static final long COOLDOWN_TIME = 4000L; // 4 sec

	public BasicOxygenHarvester(Position pos) {
		super(pos, HarvesterClassification.OXYGEN_MINER, COOLDOWN_TIME);
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += " \t harvested: " + getAmount();
		return s;
	}
}
