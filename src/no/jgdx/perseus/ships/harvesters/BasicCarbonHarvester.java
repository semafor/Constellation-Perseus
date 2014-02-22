package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.helpers.Formatting;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.HqShip;

public class BasicCarbonHarvester extends Harvester {

	public static final long COOLDOWN_TIME = 200L; // 6 sec
	public static final int CAPACITY = 1500000;

	public BasicCarbonHarvester(Position pos, HqShip hq, Player owner) {
		super(pos, HarvesterClassification.CARBON_COLLECTOR, COOLDOWN_TIME, CAPACITY, hq, owner);
	}

	@Override
	public void tick(long time) {
		super.tick(time);
		if (!isReadyToJump())
			return;

		if (isFull()) {
			jumpTo(getDefaultHq().getPosition());
			getDefaultHq().empty(this);
			setStar(null);
		} else if (isEmpty()) {
			if (getStar() != Star.MEROPE)
				setStar(Star.MEROPE);
		}
	}

	@Override
	public String toString() {
		return "C-Harvester: " + Formatting.toUnitPrefix(getAmount()) + " Carbon";
	}
}
