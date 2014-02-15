package no.jgdx.perseus.celestials;

public class Star extends Celestial {
	public static final Star SOL = new Star(1, 1, 1, "Sol", new Position(100,
			100, 100));

	public Star(double luminosity, double mass, double radius, String name,
			Position position) {
		super(luminosity, mass, radius, name, position);
	}

	@Override
	public void tick(long time) {
	}
}
