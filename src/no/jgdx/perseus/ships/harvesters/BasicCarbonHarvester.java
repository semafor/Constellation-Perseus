package no.jgdx.perseus.ships.harvesters;

import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.helpers.Formatting;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.HqShip;

public class BasicCarbonHarvester extends Harvester {

	public static final long COOLDOWN_TIME = 200L; // 6 sec
	public static final int CAPACITY = 1100000;

	private static final Map<Allotrope, Integer> PRICE = new HashMap<Allotrope, Integer>();

	{
		PRICE.put(Allotrope.OXYGEN, 1000);
	}

	public BasicCarbonHarvester(Position pos, HqShip hq, Player owner) {
		super(pos, HarvesterClassification.CARBON_COLLECTOR, COOLDOWN_TIME, CAPACITY, hq, owner, PRICE);
	}

	@Override
	public String toString() {
		return "C-Harvester: " + Formatting.toUnitPrefix(getAmount()) + " Carbon";
	}
}
