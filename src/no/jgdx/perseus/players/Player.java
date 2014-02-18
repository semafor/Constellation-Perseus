package no.jgdx.perseus.players;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;

public abstract class Player implements GameObject {

	private final Game game;

	private final List<Ship> hqs = new ArrayList<>();

	public Player(Game game, HqShip hq) {
		this.game = game;
		hqs.add(hq);
	}

	public abstract void tick(long time);

	@Override
	public double getDamage() {
		return 1;
	}

	@Override
	public Position getPosition() {
		if (!hqs.isEmpty())
			return hqs.get(0).getPosition();

		return Position.ORIGIN;
	}
}
