package no.jgdx.perseus.stations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;
import no.jgdx.perseus.ships.harvesters.BasicCarbonHarvester;

public class ShipYard extends SpaceStation {

	public static final Map<ShipClassification, Integer> SHIP_CONSTRUCTION_TIME = new HashMap<>();
	{
		SHIP_CONSTRUCTION_TIME.put(ShipClassification.HARVESTER, 2000);
		SHIP_CONSTRUCTION_TIME.put(ShipClassification.VIPER, 1000);
	}

	private final int constructionTime = 1500; // 2000 ms = 2 sec;

	private final long constructedAt;

	private boolean constructed = false;

	private final Map<Ship, Long> shipConstruction = new HashMap<>();

	private final Game game;

	public ShipYard(Position pos, HqShip hq) {
		super(pos, "ShipYard", hq);
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
		System.out.println("deployed ship " + ship + " " + ship.getPosition());

		if (!constructedHarvester) {
			constructedHarvester = true;
			constructShip(new BasicCarbonHarvester(Position.ORIGIN, getHq()),
					Game.now());
		}
	}

	private boolean constructedHarvester = false;

	@Override
	public void tick(long time) {

		if (!constructed) {
			if (constructedAt + constructionTime <= time) {
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
			if (conTime + totalConTime <= time) {
				shipConstruction.remove(s);
				deployShip(s);
				if (s instanceof BasicCarbonHarvester) {
					((BasicCarbonHarvester) s).setStar(Star.ATLAS);
				}
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
