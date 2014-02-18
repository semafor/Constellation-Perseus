package no.jgdx.perseus.weapons;

import no.jgdx.perseus.celestials.Position;

public class KineticEnergyWeapon extends Gun {

	public KineticEnergyWeapon() {
		super(Integer.MAX_VALUE, 0.1, 0.7, 500, 50, "Kinetic Energy Laser");
		// int ammo, double damage, double precision, long range, double
		// rechargeTime, String name
	}

	@Override
	public Position getPosition() {
		return null;
	}

	public void tick(long time) {
	};

	public double getDamage() {
		return 1;
	};
}
