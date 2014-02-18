package no.jgdx.perseus.stations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;

public class ShipYard extends SpaceStation {

	public static final Map<ShipClassification, Integer> SHIP_CONSTRUCTION_TIME = new HashMap<>();
	{
		SHIP_CONSTRUCTION_TIME.put(ShipClassification.HARVESTER, 5000);
		SHIP_CONSTRUCTION_TIME.put(ShipClassification.VIPER, 1000);
	}

	private final int constructionTime = 1000; // 1000 ms = 1 sec;

	private final long constructedAt;

	private boolean constructed = false;

	private final Map<Ship, Long> shipConstruction = new HashMap<>();

	private final Game game;

	public ShipYard(Position pos, long time, Game game) {
		super(pos);
		this.constructedAt = time;
		this.game = game;
	}

	@Override
	public boolean isUnderConstruction() {
		return !constructed;
	}

	public void constructShip(Ship ship, long time) {
		shipConstruction.put(ship, time);
	}

	private void deployShip(Ship ship) {
		game.addGameObject(ship);
		shipConstruction.remove(ship);
	}

	@Override
	public void tick(long time) {

		if (!constructed) {
			if (constructedAt + constructionTime >= time) {
				constructed = true;
			}
		}

		for (Map.Entry<Ship, Long> e : shipConstruction.entrySet()) {
			Ship s = e.getKey();
			long conTime = e.getValue();

			ShipClassification sc = s.getClassification();
			if (!SHIP_CONSTRUCTION_TIME.containsKey(sc)) {
				shipConstruction.remove(sc); // Concurrency exception???
			}

			long totalConTime = SHIP_CONSTRUCTION_TIME.get(sc);
			if (conTime + totalConTime >= time) {
				shipConstruction.remove(s);
				deployShip(s);
			}

		}
	}

	@Override
	public Collection<Ship> constructableShips() {
		return null;
	}

	@Override
	public Collection<SpaceStation> constructableSpaceStations() {
		return null;
	}

}
