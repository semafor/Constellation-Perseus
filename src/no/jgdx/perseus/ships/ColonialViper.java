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

	public static final long COOLDOWN_TIME = 3500L; // 1.5 sec

	public ColonialViper(Position pos) {
		super("Mark I Colonial Viper", ShipClassification.VIPER, pos,
				COOLDOWN_TIME);
		guns.add(new ViperMissile());
		guns.add(new KineticEnergyWeapon());
		guns.add(new KineticEnergyWeapon());
	}

	@Override
	public void tick(long time) {
	}

}
