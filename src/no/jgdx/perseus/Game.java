package no.jgdx.perseus;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.Ship;

/**
 * This class controls the main logic behind the game, and keeps complete lists
 * for the main objects in this game: players, celestials and ships.
 * 
 * @author pgd
 * 
 */
public class Game {

	public final List<Player> players;

	public final List<Celestial> celestials;

	public final List<Ship> ships;

	public Game() {
		players = new ArrayList<>();
		celestials = new ArrayList<>();
		ships = new ArrayList<>();
	}

	public void addGameObject(GameObject obj) {

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
