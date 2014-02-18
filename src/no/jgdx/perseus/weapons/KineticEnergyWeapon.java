package no.jgdx.perseus.weapons;

import no.jgdx.perseus.celestials.Position;

public class KineticEnergyWeapon extends Gun {

	public KineticEnergyWeapon(int ammo, int damage, double precision, long range, double rechargeTime, String name) {
		super(ammo, damage, precision, range, rechargeTime, name);
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
