package no.jgdx.perseus.players;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;

public abstract class Player {

	private final List<Ship> hqs = new ArrayList<>();

	private final String name;

	public Player(HqShip hq, String name) {
		hqs.add(hq);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		if (!hqs.isEmpty())
			return hqs.get(0).getPosition();

		return Position.ORIGIN;
	}

	public void tick(long time) {
	}
}
