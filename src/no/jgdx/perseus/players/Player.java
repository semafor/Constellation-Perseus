package no.jgdx.perseus.players;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.Messages;
import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;

public abstract class Player {

	protected final List<HqShip> hqs = new ArrayList<>();

	protected final List<Messages> inbox = new ArrayList<Messages>();

	private final String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addHq(HqShip hq) {
		hqs.add(hq);
	}

	public HqShip getHq() {
		return hqs.get(0);
	}

	public Position getPosition() {
		if (!hqs.isEmpty())
			return hqs.get(0).getPosition();

		return Position.ORIGIN;
	}

	public void tick(long time) {
	}

	public int getTotalsForAllotrope(Allotrope a) {
		int total = 0;
		for (HqShip h : hqs) {
			total = total + h.getAllotrope(a);
		}
		return total;
	}

	public int getTotalOxygen() {
		return getTotalsForAllotrope(Allotrope.OXYGEN);
	}

	public int getTotalCarbon() {
		return getTotalsForAllotrope(Allotrope.CARBON);
	}

	public int getTotalPhosphorus() {
		return getTotalsForAllotrope(Allotrope.PHOSPORUS);
	}

	public int getTotalSulfur() {
		return getTotalsForAllotrope(Allotrope.SULFUR);
	}

	public int getTotalSelenium() {
		return getTotalsForAllotrope(Allotrope.SELENIUM);
	}

	public void sendMessage(Messages message) {
		this.inbox.add(message);
	}

	public List<Messages> getMessages() {
		return inbox;
	}

}
