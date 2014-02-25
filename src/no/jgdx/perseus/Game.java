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
import no.jgdx.perseus.players.Harkonnen;
import no.jgdx.perseus.players.HumanPlayer;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
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

	private final List<String> contributors;

	private final SoundSystem soundSystem;

	private final Map<Position, GameObject> posToObj = new HashMap<>();
	private final Map<GameObject, Position> objToPos = new HashMap<>();

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
		contributors = new ArrayList<String>();
	}

	private void setup() {
		Player human = new HumanPlayer();
		Player harkonnen = new Harkonnen();
		players.add(human);
		players.add(harkonnen);

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

		HqShip hq = new HqShip("HeadQuarter", getPositionOfObject(Star.SOL),
				human);
		human.addHq(hq);
		addGameObject(hq);
		hq.setStar(Star.SOL);

		ShipYard yard = new ShipYard(hq.getPosition().add(
				new Position(0, 50, 0)), hq, human);
		addGameObject(yard);

		setContributors();

		ColonialViper v1 = new ColonialViper(Star.ELECTRA.getPosition(), human);
		ColonialViper v2 = new ColonialViper(Star.ELECTRA.getPosition(), human);
		ColonialViper v3 = new ColonialViper(Star.ELECTRA.getPosition(), human);
		ColonialViper v4 = new ColonialViper(Star.ELECTRA.getPosition(), human);
		ColonialViper v5 = new ColonialViper(Star.ELECTRA.getPosition(), human);
		ColonialViper v6 = new ColonialViper(Star.ELECTRA.getPosition(), human);
		addGameObject(v1);
		addGameObject(v2);
		addGameObject(v3);
		addGameObject(v4);
		addGameObject(v5);
		addGameObject(v6);

		HqShip harkonnenHq = new HqShip("Harkonnen",
				Star.PLEIONE.getPosition(), harkonnen);
		harkonnen.addHq(harkonnenHq);
		addGameObject(harkonnenHq);
		harkonnenHq.setStar(Star.PLEIONE);
	}

	public Player getHumanPlayer() {
		return players.get(0);
	}

	/**
	 * Returns true if player can afford to buy ship AND the money is withdrawn
	 * from the Hq's assets!
	 * 
	 * @param ship
	 * @return true if buy successful
	 */
	public boolean buy(Ship ship, Player player) {
		HqShip hq = player.getHq();
		return hq.buy(ship);
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
	 * Returns null if ship not ready to jump
	 * 
	 * @param ship
	 * @param cel
	 * @return
	 */
	public Position sendShipToCelestial(Ship ship, Celestial cel) {
		assert datainvariant(ship);
		assert datainvariant(cel);
		if (!ship.isReadyToJump())
			return null;

		ship.jumpTo(objToPos.get(cel));

		return assignPosition(ship, objToPos.get(cel));

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
		synchronized (positionLock) {

			System.out.println("assigning " + obj + " ... ");
			if (objToPos.containsKey(obj)) {
				Position oldPos = objToPos.get(obj);
				if (oldPos.equals(position)) {
					assert posToObj.containsKey(position) : " did not contain "
							+ position + " for " + obj;
					assert posToObj.get(position) == obj : " mismatch "
							+ posToObj.get(position) + " vs " + obj;
					return position; // nothing to do
				} else {
					objToPos.remove(obj);
					posToObj.remove(oldPos);
					obj.setPosition(null);
				}
			}

			int iteration = 0;
			while (true) {
				iteration++;
				for (int x = 0 - iteration; x <= iteration; x++) {
					for (int y = 0 - iteration; y <= iteration; y++) {
						if (x != 0 || y != 0) {
							int nx = x;
							int ny = y;
							Position attempt = position.add(new Position(nx,
									ny, 0));
							if (getObject(attempt, 20) == null) {
								posToObj.put(attempt, obj);
								objToPos.put(obj, attempt);
								obj.setPosition(attempt);
								System.out.println("Assigned " + position
										+ " → " + attempt + "\t" + obj);

								assert posToObj.containsKey(attempt);
								assert posToObj.get(attempt).equals(obj);
								assert objToPos.containsKey(obj);
								assert objToPos.get(obj).equals(attempt);
								assert attempt.equals(obj.getPosition());

								return attempt;
							}
						}
					}
				}
			}
		}

	}

	public Position getPositionOfObject(GameObject object) {
		if (objToPos.containsKey(object))
			return objToPos.get(object);
		System.err.println("Unknown object " + object);
		return null;
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

	/**
	 * Returns the object closest to given position. Returns null if no object
	 * is closer than range.
	 * 
	 * @param target
	 * @param range
	 * @return
	 */
	public GameObject getObject(Position target, float range) {
		GameObject optObj = null;
		Position optPos = null;
		double optDist = 0;

		for (Entry<Position, GameObject> e : posToObj.entrySet()) {
			Position p = e.getKey();
			GameObject o = e.getValue();
			if (optPos == null) {
				optObj = o;
				optPos = p;
				optDist = p.distance(target);
			} else {
				double dist = target.distance(p);
				if (dist < optDist) {
					optObj = o;
					optPos = p;
					optDist = dist;
				}
			}
		}

		if (optDist > range)
			return null;

		return optObj;
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

	/**
	 * Simple semafor.
	 */
	private final Object positionLock = new Object();

	public void tick() {
		synchronized (positionLock) {
			for (Entry<GameObject, Position> e : objToPos.entrySet()) {
				GameObject g = e.getKey();
				Position p = e.getValue();

				assert p.equals(g.getPosition()) : "Stored pos mismatch " + p
						+ " vs " + g.getPosition();

				assert posToObj.containsKey(p) : "posToObj doesn't contain "
						+ p + ", the position of " + g + " - " + g.getName()
						+ " vs " + g.getPosition();
				assert posToObj.get(p).equals(g);

			}

			for (Entry<Position, GameObject> e : posToObj.entrySet()) {
				GameObject g = e.getValue();
				Position p = e.getKey();
				assert objToPos.containsKey(g) : "objToPos doesn't contain "
						+ g;
				assert objToPos.get(g).equals(p) : "objToPos mismatch: "
						+ objToPos.get(g) + " vs " + p + " \t for " + g + " - "
						+ g.getName();
				assert g.getPosition().equals(p) : "stored pos mismatch " + p
						+ " vs " + g.getPosition() + " \t for " + g + " - "
						+ g.getName();
			}

			for (Player p : players) {
				p.tick(now());
			}

			for (Celestial c : celestials) {
				assert datainvariant(c);
				c.tick(now());
			}

			for (SpaceStation ss : stations) {
				assert datainvariant(ss);
				ss.tick(now());
			}

			for (Ship s : ships) {
				assert datainvariant(s);
				s.tick(now());
			}
		}
	}

	public boolean datainvariant(GameObject obj) {
		assert objToPos.containsKey(obj) : "objToPos doesn't contain " + obj
				+ " posToObj " + posToObj.get(obj.getPosition()) + " at pos "
				+ obj.getPosition();

		Position p = objToPos.get(obj);

		assert posToObj.containsKey(p) : "posToObj doesn't contain " + p;

		assert posToObj.get(p).equals(obj) : "posToObj at " + p + " contains "
				+ posToObj.get(p) + " vs " + obj;

		assert p.equals(obj.getPosition()) : "Position mismatch: " + p + " vs "
				+ obj.getPosition();

		return true;
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

	public ArrayList<MainMenuItems> getMainMenuItems() {
		ArrayList<MainMenuItems> l = new ArrayList<MainMenuItems>();
		l.add(MainMenuItems.NEW_GAME);
		l.add(MainMenuItems.CREDITS);
		return l;
	}

	public enum MainMenuItems {
		MAIN_MENU("Main menu"),

		CREDITS("Credits"),

		NEW_GAME("New game");

		private final String label;

		private MainMenuItems(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	private void setContributors() {
		contributors.add("Pål Grønås Drange");
		contributors.add("Jonas Grønås Drange");
	}

	public List<String> getContributors() {
		return contributors;
	}

}
