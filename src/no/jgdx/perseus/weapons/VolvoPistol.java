package no.jgdx.perseus.weapons;

public class VolvoPistol extends Gun {
	public VolvoPistol() {
		super(10, 10, 10, 10, 10, "My Volvo Pistol");
	}

	@Override
	public String toString() {
		return "Hurricane 2000! " + getAmmonution() + " (ammo left)";
	}
}
