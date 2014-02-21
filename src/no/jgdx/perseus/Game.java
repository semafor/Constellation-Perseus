package no.jgdx.perseus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import no.jgdx.perseus.celestials.Celestial;
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

	private final Map<Position, GameObject> map = new HashMap<>();

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
		Ship w1 = new ColonialViper(Position.ORIGIN);
		w1.jumpTo(new Position(200, 100, 50));
		addGameObject(w1);

		addGameObject(Star.SOL);

		addGameObject(Star.ALCYONE);
		addGameObject(Star.ATLAS);
		addGameObject(Star.ELECTRA);
		addGameObject(Star.MAIA);
		addGameObject(Star.MEROPE);

		addGameObject(Star.TAYGETA);
		addGameObject(Star.PLEIONE);
		addGameObject(Star.CELAENO);
		addGameObject(Star.STEROPE);
		addGameObject(Star.ASTEROPE);

		HqShip hq = new HqShip("HeadQuarter", Position.ORIGIN);
		hq.setStar(Star.SOL);
		addGameObject(hq);

		Harvester oxMin = new BasicOxygenHarvester(hq.getPosition(), hq);
		hq.addHarvester(oxMin);
		oxMin.setStar(Star.SOL);
		addGameObject(oxMin);

		ShipYard yard = new ShipYard(hq.getPosition(), hq);
		addGameObject(yard);

		ColonialViper v1 = new ColonialViper(Star.ELECTRA.getPosition());
		ColonialViper v2 = new ColonialViper(Star.ELECTRA.getPosition());
		ColonialViper v3 = new ColonialViper(Star.ELECTRA.getPosition());
		ColonialViper v4 = new ColonialViper(Star.ELECTRA.getPosition());
		ColonialViper v5 = new ColonialViper(Star.ELECTRA.getPosition());
		ColonialViper v6 = new ColonialViper(Star.ELECTRA.getPosition());
		addGameObject(v1);
		addGameObject(v2);
		addGameObject(v3);
		addGameObject(v4);
		addGameObject(v5);
		addGameObject(v6);
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

	/**
	 * Finds the nearest uninhabitated position in space and puts obj there.
	 * This method calls setPosition on obj with the position it returns.
	 * Returns the position it was moved to.
	 * 
	 * @param obj
	 *            the object to position
	 * @param position
	 * @return
	 */
	public Position assignPosition(GameObject obj, Position position) {
		Position storedPos = null;
		for (Entry<Position, GameObject> e : map.entrySet()) {
			if (e.getValue().equals(obj)) {
				if (e.getKey().equals(position))
					return position;
				else
					storedPos = e.getKey();
			}
		}
		if (storedPos != null)
			map.remove(storedPos);

		if (!map.containsKey(position) || map.get(position).equals(obj)) {
			map.put(position, obj);
			obj.setPosition(position);

			System.out.println("Assigned ... stationary. " + obj);

			return position;
		}

		int posdif = 20;
		int iteration = 0;
		while (true) {
			iteration++;
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if (x != 0 || y != 0) {
						int nx = posdif * iteration * x;
						int ny = posdif * iteration * y;
						Position attempt = position
								.add(new Position(nx, ny, 0));
						if (!map.containsKey(attempt)) {
							map.put(attempt, obj);
							obj.setPosition(attempt);
							System.out.println("Assigned " + position + " → "
									+ attempt + "\t" + obj);
							return attempt;
						}
					}
				}
			}
		}
	}

	public void addGameObject(GameObject obj) {
		if (obj instanceof Ship) {
			ships.add((Ship) obj);
		} else if (obj instanceof Celestial) {
			celestials.add((Celestial) obj);
			// System.out.println("Added celestial to game: " + obj);
		} else if (obj instanceof Player) {
			players.add((Player) obj);
			// what just happened?
			// System.out.println("Player added to game:" + obj);
		} else if (obj instanceof SpaceStation) {
			stations.add((SpaceStation) obj);
			// System.out.println("SpaceStation added to game: " + obj);
		}

		assignPosition(obj, obj.getPosition());

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
