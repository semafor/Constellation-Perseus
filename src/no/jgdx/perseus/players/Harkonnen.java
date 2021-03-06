package no.jgdx.perseus.players;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.BasicCarbonHarvester;
import no.jgdx.perseus.ships.harvesters.BasicOxygenHarvester;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;

/**
 * House Harkonnen, from the volcanic wastelands of Giedi Prime. The Harkonnen
 * know only malevolence, hatred and brutality. Their leader is the corrupt and
 * vile Baron Rakan. Rakan's power-hungry sons — Gunseng and Copec — eagerly
 * await the Baron's death. Each plots to take his place. But while he lives,
 * they feed upon him like parasites.
 * 
 * @author pgd
 * 
 */
public class Harkonnen extends Player {
	public Harkonnen() {
		super("Harkonnen");
	}

	private ShipYard yard = null;

	private final Position basePos = Star.PLEIONE.getPosition();

	private final List<Ship> ships = new ArrayList<>();

	private final List<Harvester> harvesters = new ArrayList<>();

	private long lastTick = -1;

	private final static int THINK_TIME = 25;
	private final static int INIT_TIME = 3000;

	@Override
	public void tick(long time) {
		if (time < INIT_TIME)
			return;

		if (lastTick == -1) {
			lastTick = time;
			return;
		}

		if (lastTick + THINK_TIME > time) {
			return;
		}
		lastTick = time;

		if (ships.size() < 5) {
			initPhase();
			return;
		} else if (ships.size() <= 20) {
			developPhase();
		} else {
			killPhase();
		}
	}

	private void killPhase() {
		Ship enemy = Game.getInstance().getClosestEnemyShip(getHq());
		if (enemy == null) {
			System.err.println("No close enemey ship!");
			return;
		}
		Position enemyPos = Game.getInstance().getPositionOfObject(enemy);

		int nonReady = 0;
		for (Ship s : ships) {
			if (!s.isReadyToJump()) {
				nonReady += 1;
			}
		}
		if (nonReady > 0) {
			System.out.println("Let's wait until we're all ready and then KILL! Waiting for " + nonReady + " ships");
			return;
		}

		System.out.println("JUMP AND KILL!");
		for (Ship s : ships) {
			s.jumpTo(enemyPos);
		}
	}

	private void developPhase() {
		if (harvesters.get(0).isEmpty()) {
			harvesters.get(0).setStar(Star.ATLAS);
		} else if (harvesters.get(0).isFull()) {
			harvesters.get(0).sendHome(getHq());
		}
		if (harvesters.get(1).isEmpty()) {
			harvesters.get(1).setStar(Star.ALCYONE);
		} else if (harvesters.get(1).isFull()) {
			harvesters.get(1).sendHome(getHq());
		}

		if (harvesters.size() == 2) {
			if (getHq().canAfford(new BasicCarbonHarvester(basePos, getHq(), this))) {
				buildCarbonMiner();
			}
			return;
		}
		if (harvesters.size() == 3) {
			if (getHq().canAfford(new BasicOxygenHarvester(basePos, getHq(), this))) {
				buildOxygenMiner();
			}
			return;
		}

		if (harvesters.get(2).isEmpty()) {
			harvesters.get(2).setStar(Star.ATLAS);
		} else if (harvesters.get(0).isFull()) {
			harvesters.get(2).sendHome(getHq());
		}
		if (harvesters.get(3).isEmpty()) {
			harvesters.get(3).setStar(Star.ALCYONE);
		} else if (harvesters.get(1).isFull()) {
			harvesters.get(3).sendHome(getHq());
		}
		if (ships.size() <= 20 && getHq().canAfford(new ColonialViper(getPosition(), this))) {
			buildViper();
		}
	}

	private void initPhase() {
		if (yard == null) {
			buildShipYard();
			return;
		}

		if (yard != null && yard.isUnderConstruction())
			return;

		if (harvesters.size() == 0) {
			buildCarbonMiner();
			System.out.println("HARKONNEN CARBON");
			return;
		}

		if (harvesters.get(0).isEmpty()) {
			harvesters.get(0).setStar(Star.ATLAS);
			return;
		}
		if (harvesters.get(0).isFull()) {
			harvesters.get(0).sendHome(getHq());
			return;
		}

		if (harvesters.size() == 1) {
			buildOxygenMiner();
			return;
		}
		if (harvesters.get(1).isEmpty()) {
			harvesters.get(1).setStar(Star.ALCYONE);
			return;
		}
		if (harvesters.get(1).isFull()) {
			harvesters.get(1).sendHome(getHq());
			return;
		}

		if (ships.size() <= 5 && getHq().canAfford(new ColonialViper(getPosition(), this))) {
			buildViper();
			return;
		}
	}

	private void buildViper() {
		ColonialViper v = new ColonialViper(Game.getInstance().getPositionOfObject(Star.MAIA), this);
		if (Game.getInstance().buy(v, this)) {
			System.out.println("HARKONNEN BUILDING VIPER!");

			yard.constructShip(v, Game.now());
			ships.add(v);
		} else {
			System.out.println("HARKONNEN TO POOR FOR VIPER :(");
		}
	}

	private void buildOxygenMiner() {
		System.out.println("HARKONNEN builds oxygen miner.");

		Harvester cm = new BasicOxygenHarvester(basePos, getHq(), this);
		if (Game.getInstance().buy(cm, this)) {
			yard.constructShip(cm, Game.now());
			harvesters.add(cm);
		}
	}

	private void buildCarbonMiner() {
		System.out.println("HARKONNEN builds carbon miner.");

		Harvester cm = new BasicCarbonHarvester(basePos, getHq(), this);
		if (Game.getInstance().buy(cm, this)) {
			yard.constructShip(cm, Game.now());
			harvesters.add(cm);
		}
	}

	private void buildShipYard() {
		yard = new ShipYard(basePos, getHq(), this);
		Game.getInstance().addGameObject(yard);
	}
}
