package no.jgdx.perseus.ships;

import no.jgdx.perseus.celestials.Position;

public class Harvester extends Ship {
	public Harvester(Position pos) {
		super("Harvester", Classification.HARVESTER, pos);
	}

	@Override
	public void tick(long time) {
	}
}
