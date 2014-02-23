package no.jgdx.perseus.players;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;

public abstract class Player {

	protected final List<HqShip> hqs = new ArrayList<>();

	private final String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addHq(HqShip hq) {
		hqs.add(hq);
	}

	public HqShip getHq() {
		return hqs.get(0);
	}

	public Position getPosition() {
		if (!hqs.isEmpty())
			return hqs.get(0).getPosition();

		return Position.ORIGIN;
	}

	public void tick(long time) {
	}
}
