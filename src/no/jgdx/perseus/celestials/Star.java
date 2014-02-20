package no.jgdx.perseus.celestials;

/**
 * Stars are nuclear fusion devices converting hydrogen to helium.
 * 
 */
public class Star extends Celestial {

	public static final Star SOL = new Star(1, 1, 1, "Sol", new Position(100, 100, 100), StarClassification.G);

	private final StarClassification sc;

	public Star(double luminosity, double mass, double radius, String name, Position position, StarClassification sc) {
		super(luminosity, mass, radius, name, position);
		this.sc = sc;
	}

	public StarClassification getStarClassification() {
		return sc;
	}

	@Override
	public void tick(long time) {
	}

	@Override
	public String toString() {
		return "Star " + sc;
	}
}
