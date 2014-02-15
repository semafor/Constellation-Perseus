package no.jgdx.perseus.ships;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.Gun;
import no.jgdx.perseus.weapons.VolvoPistol;

/**
 * A Volvo S60
 * 
 * @author jonas
 * 
 */
public class Wreck implements Ship {

	private final List<Gun> guns;

	private Position pos = Position.ORIGIN;

	public Wreck() {
		guns = new ArrayList<Gun>();
		guns.add(new VolvoPistol());
	}

	@Override
	public List<Gun> getGuns() {
		return guns;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

	@Override
	public void setPosition(Position position) {
		this.pos = position;
	}

	@Override
	public String getName() {
		return "Volvo S60^^^^^^^^Wreck";
	}

	@Override
	public String toString() {
		return "My Volvo (" + pos + ")";
	}

	@Override
	public String getClassification() {
		return "Sedan";
	}

}
