package no.jgdx.perseus.ships;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.Gun;

/**
 * The Mark I Colonial Viper, comes with kinetic energy weapons and conventional
 * missiles.
 * 
 * @author pgd
 * 
 */
public class ColonialViper extends Ship {

	private Gun kinetic;

	public ColonialViper(Position pos) {
		super(ColonialViper.class.getCanonicalName(), ShipClassification.VIPER, pos);
	}

	@Override
	public void tick(long time) {
	}
}
