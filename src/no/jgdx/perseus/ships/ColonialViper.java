package no.jgdx.perseus.ships;

import java.util.HashMap;
import java.util.Map;

import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.players.Player;
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

	private static final Map<Allotrope, Integer> PRICE = new HashMap<Allotrope, Integer>();

	{
		PRICE.put(Allotrope.OXYGEN, 1000);
		PRICE.put(Allotrope.CARBON, 1000);
	}

	public ColonialViper(Position pos, Player owner) {
		super("Mark I Colonial Viper", ShipClassification.VIPER, pos, COOLDOWN_TIME, owner, PRICE);
		guns.add(new ViperMissile());
		guns.add(new KineticEnergyWeapon());
		guns.add(new KineticEnergyWeapon());
	}

	@Override
	public void tick(long time) {
	}

}
