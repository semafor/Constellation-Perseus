package no.jgdx.perseus;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.Ship;

public class Game {
	public final List<Celestial> celestials;

	public final List<Ship> ships;

	public Game() {
		celestials = new ArrayList<Celestial>();
		ships = new ArrayList<Ship>();
	}

	public void tick() {
		for (Ship s : ships) {
			Position p = s.getPosition();
			Position n = p.add(new Position(30, 20, 100));
			s.setPosition(n);

		}
		for (Celestial c : celestials)
			c.tick(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return ships.toString();
	}

}
