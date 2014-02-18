package no.jgdx.perseus.ships;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.KineticEnergyWeapon;
import no.jgdx.perseus.weapons.ViperMissile;

/**
 * The Mark I Colonial Viper, equipped with kinetic energy weapons and
 * conventional missiles.
 * 
 * @author pgd
 * 
 */
public class ColonialViper extends Ship {

	public ColonialViper(Position pos) {
		super("Mark I Colonial Viper", ShipClassification.VIPER, pos);
		guns.add(new ViperMissile());
		guns.add(new KineticEnergyWeapon());
		guns.add(new KineticEnergyWeapon());
	}

	@Override
	public void tick(long time) {
	}

	@Override
	public String toString() {
		return getName() + ", equipped with weapons: " + guns.toString();
	}
}
