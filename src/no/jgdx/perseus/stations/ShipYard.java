package no.jgdx.perseus.stations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;

public class ShipYard extends SpaceStation {

	public static final Map<ShipClassification, Integer> SHIP_CONSTRUCTION_TIME = new HashMap<>();
	{
		SHIP_CONSTRUCTION_TIME.put(ShipClassification.HARVESTER, 8000);
		SHIP_CONSTRUCTION_TIME.put(ShipClassification.VIPER, 3000);
	}

	private final int constructionTime = 2000; // 2000 ms = 2 sec;

	private final long constructedAt;

	private boolean constructed = false;

	private final Map<Ship, Long> shipConstruction = new HashMap<>();

	private final Game game;

	public ShipYard(Position pos) {
		super(pos, "ShipYard");
		this.constructedAt = Game.now();
		this.game = Game.getInstance();
	}

	@Override
	public boolean isUnderConstruction() {
		return !constructed;
	}

	public void constructShip(Ship ship, long time) {
		System.out.println("Constructing ship now: " + (time / 1000) + " sec");
		shipConstruction.put(ship, time);
	}

	private void deployShip(Ship ship) {
		System.out.println("Deploying ship ... ");
		game.addGameObject(ship);
		shipConstruction.remove(ship);
		System.out.println("deployed ship " + ship);
		ship.jumpTo(getPosition().add(new Position(-50, -50, 0)));
	}

	@Override
	public void tick(long time) {

		if (!constructed) {
			if (constructedAt + constructionTime <= time) {
				constructed = true;
				constructShip(new ColonialViper(getPosition()), time);
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
			if (conTime + totalConTime <= time) {
				shipConstruction.remove(s);
				deployShip(s);
			}

		}
	}

	@Override
	public String toString() {
		if (isUnderConstruction())
			return getName() + " (under construction)";
		if (!shipConstruction.isEmpty())
			return getName() + " constructing ship " + shipConstruction;
		return getName();
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
