package no.jgdx.perseus.weapons;

import no.jgdx.perseus.celestials.Position;

public class ViperMissile extends Gun {

	public ViperMissile() {
		super(1000, 0.4, 0.5, 1000000, 2000, "Conventional Colonial Viper Missile");
		// int ammo, double damage, double precision, long range, double
		// rechargeTime, String name
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick(long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
