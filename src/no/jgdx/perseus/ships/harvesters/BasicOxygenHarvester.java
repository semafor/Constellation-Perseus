package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;

public class BasicOxygenHarvester extends Harvester {

	public BasicOxygenHarvester(Position pos) {
		super(pos, HarvesterClassification.OXYGEN_MINER);
	}

	@Override
	public String toString() {
		return "Oxygen miner: " + getAmount();
	}
}
