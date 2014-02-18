package no.jgdx.perseus;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.celestials.StarClassification;
import no.jgdx.perseus.players.Harkonnen;
import no.jgdx.perseus.players.HumanPlayer;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.stations.SpaceStation;

/**
 * This class controls the main logic behind the game, and keeps complete lists
 * for the main objects in this game: players, celestials and ships.
 * 
 * @author pgd
 * 
 */
public class Game {

	private final long initializeTime;

	private final List<Player> players;

	private final List<Celestial> celestials;

	private final List<Ship> ships;

	private final List<SpaceStation> stations;

	public Game() {
		initializeTime = System.currentTimeMillis();
		players = new ArrayList<>();
		celestials = new ArrayList<>();
		ships = new ArrayList<>();
		stations = new ArrayList<>();
	}

	private void initialize() {
		players.add(new HumanPlayer(this, null));
		players.add(new Harkonnen(this, null));

		celestials.add(new Star(1, 1, 1, "Sol", new Position(1, 1, 1), StarClassification.G));
		celestials.add(new Star(78.5, 2.69, 12.2, "Capella", new Position(100, 100, 100), StarClassification.G));
	}

	public void addGameObject(GameObject obj) {
		if (obj instanceof Ship) {
			ships.add((Ship) obj);
		} else if (obj instanceof Celestial) {
			celestials.add((Celestial) obj);
			System.out.println("Added celestial to game: " + obj);
		} else if (obj instanceof Player) {
			players.add((Player) obj);
			// what just happened?
			System.out.println("Player added to game:" + obj);
		} else if (obj instanceof SpaceStation) {
			stations.add((SpaceStation) obj);
			System.out.println("SpaceStation added to game: " + obj);
		}
	}

	public List<Celestial> getCelestials() {
		return celestials;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public List<SpaceStation> getStations() {
		return stations;
	}

	public void tick() {
		long currentTime = System.currentTimeMillis();
		long relativeTime = currentTime - initializeTime;

		for (Player p : players) {
			p.tick(relativeTime);
		}

		for (Celestial c : celestials)
			c.tick(relativeTime);

		for (SpaceStation ss : stations) {
			ss.tick(relativeTime);
		}

		for (Ship s : ships) {
			Position p = s.getPosition();
			Position n = p.add(new Position(30, 20, 100));
			s.setPosition(n);
			s.tick(relativeTime);
		}
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();

		b.append("Players:");
		for (Player p : players) {
			b.append("\n\t" + p);
		}

		b.append("Celestials:");
		for (Celestial c : celestials) {
			b.append("\n\t" + c);
		}

		b.append("Stations:");
		for (SpaceStation ss : stations) {
			b.append("\n\t" + ss);
		}

		b.append("Ships:");
		for (Ship s : ships) {
			b.append("\n\t" + s);
		}

		return b.toString();
	}

}
