package no.jgdx.perseus;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Moon;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.client.audio.Mood;
import no.jgdx.perseus.client.audio.SoundSystem;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.BasicOxygenHarvester;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;
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

	private final SoundSystem soundSystem;

	private volatile static boolean IS_INSTANTIATED = false;
	private static Game SINGLETON_INSTANCE;

	public synchronized static Game getInstance() {
		if (!IS_INSTANTIATED) {
			IS_INSTANTIATED = true;
			SINGLETON_INSTANCE = new Game();
			SINGLETON_INSTANCE.setup();
		}
		return SINGLETON_INSTANCE;
	}

	private Game() {
		initializeTime = System.currentTimeMillis();
		players = new ArrayList<>();
		celestials = new ArrayList<>();
		ships = new ArrayList<>();
		stations = new ArrayList<>();
		soundSystem = SoundSystem.getSoundSystem();
	}

	private void setup() {
		Ship w1 = new ColonialViper(new Position(200, 100, 50));

		Star sol = Star.SOL;
		sol.setPosition(new Position(400, 400, 400));

		addGameObject(w1);

		addGameObject(sol);

		Moon earth = new Moon(1, 10, 130, "Earth", sol.getPosition().add(new Position(10, 10, 10)), sol);

		Moon moon = new Moon(1, 35, 60, "Moon", earth.getPosition().add(new Position(10, 10, 20)), earth);

		addGameObject(earth);
		addGameObject(moon);

		HqShip hq = new HqShip("HeadQuarter", Position.ORIGIN);
		hq.setStar(sol);
		addGameObject(hq);

		Harvester oxMin = new BasicOxygenHarvester(hq.getPosition());
		hq.addHarvester(oxMin);
		addGameObject(oxMin);

		ShipYard yard = new ShipYard(hq.getPosition());
		addGameObject(yard);
	}

	/**
	 * Returns the current time in milliseconds relative to when the game
	 * started, i.e. it will be 0 immediately when the game starts.
	 * 
	 * @return
	 */
	public static long now() {
		long n = System.currentTimeMillis();
		return n - getInstance().initializeTime;
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

		// temp hack to test sound system
		if (obj instanceof Ship && now() > 2000) {
			soundSystem.changeMood(Mood.WAR);
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

		for (Player p : players) {
			p.tick(now());
		}

		for (Celestial c : celestials)
			c.tick(now());

		for (SpaceStation ss : stations) {
			ss.tick(now());
		}

		for (Ship s : ships) {
			s.tick(now());
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
