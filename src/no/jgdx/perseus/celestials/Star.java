package no.jgdx.perseus.celestials;

/**
 * Stars are nuclear fusion devices converting hydrogen to helium.
 * 
 */
public class Star extends Celestial {

	/*
	 * SOME STARS
	 */
	public static final Star SOL = new Star(1, 1, 1, "Sol", new Position(400, 400, 400), StarClassification.G);

	public static final Star ALCYONE = new Star(2400, 6, 8.2, "Alcyone", new Position(300, 300, 0), StarClassification.B);

	public static final Star ATLAS = new Star(940, 5, 5, "Atlas", new Position(50, 200, 0), StarClassification.O);

	public static final Star ELECTRA = new Star(1, 1, 1, "Electra", new Position(0, 0, 0), StarClassification.B);

	public static final Star MAIA = new Star(850, 5, 6.04, "Maia", new Position(500, 500, 500), StarClassification.B);

	public static final Star MEROPE = new Star(630, 4.5, 4, "Merope", new Position(400, 100, 500), StarClassification.B);

	// fix these
	public static final Star TAYGETA = new Star(850, 5, 6.04, "Taygeta", new Position(600, 600, 500), StarClassification.B);
	public static final Star PLEIONE = new Star(850, 5, 6.04, "Pleione", new Position(50, 250, 500), StarClassification.B);
	public static final Star CELAENO = new Star(850, 5, 6.04, "Celaeno", new Position(650, 500, 500), StarClassification.B);
	public static final Star STEROPE = new Star(850, 5, 6.04, "Sterope", new Position(550, 650, 500), StarClassification.B);
	public static final Star ASTEROPE = new Star(850, 5, 6.04, "Asterope", new Position(530, 630, 500), StarClassification.B);

	/*
	 * END OF SOME STARS
	 */

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
