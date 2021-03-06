package no.jgdx.perseus.ships.harvesters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import no.jgdx.perseus.GameObjectActions;
import no.jgdx.perseus.GameObjectState;
import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.ShipClassification;

public abstract class Harvester extends Ship {

	/** The star it is connected to, might be null. */
	private Star star;

	private final HarvesterClassification hc;

	private int amount = 0;

	private final int capacity;

	private HqShip defaulHq;

	private boolean isAtHq = false;

	private final List<GameObjectActions> actions = new ArrayList<GameObjectActions>();

	public Harvester(Position pos, HarvesterClassification hc,
			long coolDownTime, int capacity, HqShip hq, Player owner,
			Map<Allotrope, Integer> price) {
		super("Harvester", ShipClassification.HARVESTER, pos, coolDownTime,
				owner, price);
		this.hc = hc;
		this.capacity = capacity;
		this.defaulHq = hq;
	}

	protected HqShip getDefaultHq() {
		return defaulHq;
	}

	/**
	 * Returns in percentages, the amount vs capacity
	 * 
	 * @return
	 */
	public int getPercentage() {
		if (isFull())
			return 100;
		if (isEmpty())
			return 0;
		float cap = capacity;
		float am = amount;
		if (cap == 0) {
			return 0;
		}
		return (int) ((100f * am) / cap);
	}

	public boolean isFull() {
		return capacity == amount;
	}

	public boolean isEmpty() {
		return amount == 0;
	}

	public int getCapacity() {
		return capacity;
	}

	public final int resetAmount() {
		int a = amount;
		amount = 0;
		return a;
	}

	public HarvesterClassification getHarvesterClassification() {
		return hc;
	}

	public Star getStar() {
		return star;
	}

	public boolean setStar(Star star) {
		if (star == null) {
			star = null;
			return true;
		}
		if (star == this.star)
			return true;
		if (!isReadyToJump())
			return false;
		jumpTo(star.getPosition());
		this.star = star;
		isAtHq = false;

		System.out.println(getClass().getSimpleName() + "@" + star.getName());

		return true;
	}

	@Override
	public void tick(long time) {
		if (isAtHq) {
			if (!isEmpty()) {
				defaulHq.empty(this);
			}
		}

		if (star != null) {
			if (star.getStarClassification().getAllotrope() == hc
					.getAllotrope()) {
				amount += hc.getHarvestSpeed();
				if (amount > capacity)
					amount = capacity;
			}
		}

	}

	protected int getAmount() {
		return amount;
	}

	public boolean harvesting() {
		return getState() == GameObjectState.HARVESTING;
	}

	public boolean sendHome(HqShip s) {
		if (jumpTo(s.getPosition())) {
			isAtHq = true;
			star = null;
		}
		return false;
	}

	@Override
	public List<GameObjectActions> getPossibleActions() {
		return actions;
	}

	@Override
	public boolean getActionPossible(GameObjectActions action) {
		return actions.contains(action);
	}

}
