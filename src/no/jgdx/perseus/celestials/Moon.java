package no.jgdx.perseus.celestials;

public class Moon extends Celestial {

	private final Celestial parent;

	private int theta = 0; // [0,360)

	public Moon(double luminosity, double mass, double radius, String name, Position position, Celestial parent) {
		super(luminosity, mass, radius, name, position);
		this.parent = parent;
	}

	@Override
	public void tick(long time) {

		theta += mass;
		theta = theta % 360;

		double radians = (theta / 180d) * Math.PI;
		double xpos = Math.cos(radians) * getSolarRadius();
		double ypos = Math.sin(radians) * getSolarRadius();

		Position newpos = parent.getPosition().add(new Position((long) xpos, (long) ypos, 0));
		setPosition(newpos);
	}

}
