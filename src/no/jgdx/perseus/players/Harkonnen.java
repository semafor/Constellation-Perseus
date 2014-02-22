package no.jgdx.perseus.players;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.stations.ShipYard;

public class Harkonnen extends Player {
	public Harkonnen() {
		super("Harkonnen");
	}

	private boolean hasYard = false;

	private ShipYard yard = null;

	private final Position basePos = new Position(700, 700, 0);

	private final List<Ship> ships = new ArrayList<>();

	@Override
	public void tick(long time) {
		super.tick(time);

		HqShip hq = hqs.get(0);

		if (!hasYard) {
			yard = new ShipYard(basePos, hq, this);
			hasYard = true;
			return;
		}
		if (!yard.isUnderConstruction()) {
			System.out.println("Harkonnen builds ship ... ");
			ColonialViper cv = new ColonialViper(basePos, this);
			yard.constructShip(cv, time);
			ships.add(cv);
		} else {
			return;
		}

	}
}
