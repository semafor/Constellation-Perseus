package no.jgdx.perseus.ships.harvesters;

import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.helpers.Formatting;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.HqShip;

public class BasicOxygenHarvester extends Harvester {

	public static final long COOLDOWN_TIME = 4000L; // 4 sec
	public static final int CAPACITY = 160000;

	private static final Map<Allotrope, Integer> PRICE = new HashMap<Allotrope, Integer>();

	{
		PRICE.put(Allotrope.CARBON, 1000);
	}

	public BasicOxygenHarvester(Position pos, HqShip hq, Player owner) {
		super(pos, HarvesterClassification.OXYGEN_MINER, COOLDOWN_TIME,
				CAPACITY, hq, owner, PRICE);
	}

	@Override
	public String toString() {
		return "O-Harvester: " + Formatting.toUnitPrefix(getAmount())
				+ " Oxygen";
	}

}