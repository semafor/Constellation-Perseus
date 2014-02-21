package no.jgdx.perseus.weapons;

import no.jgdx.perseus.celestials.Position;

public class VolvoPistol extends Gun {
	public VolvoPistol() {
		super(10, 10, 10, 10, 10, "My Volvo Pistol");
	}

	@Override
	public double getDamage() {
		return 0;
	}

	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public void setPosition(Position position) {
	}

	@Override
	public void tick(long time) {
	}

	@Override
	public String toString() {
		return "Hurricane 2000! " + getAmmonution() + " (ammo left)";
	}

}
