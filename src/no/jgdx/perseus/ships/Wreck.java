package no.jgdx.perseus.ships;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.VolvoPistol;

/**
 * A Volvo S60
 * 
 * @author jonas
 * 
 */
public class Wreck extends Ship {

	public Wreck() {
		super("Volvo S60^^^^^^^^Wreck", ShipClassification.VIPER, Position.ORIGIN);
		guns.add(new VolvoPistol());
	}

	public String toString() {
		return "My Volvo (" + getPosition() + ")";
	}

	@Override
	public void tick(long time) {
	}
}
