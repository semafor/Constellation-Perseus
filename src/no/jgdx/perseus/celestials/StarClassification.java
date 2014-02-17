package no.jgdx.perseus.celestials;

public enum StarClassification {
	O("Blue", 30000, 60, 15, 1400000, "Singly ionized helium lines (H I) either in emission or absorption. Strong UV continuum.",
			"10 Lacertra"),

	B("Blue", 11000, 18, 7, 20000, "Neutral helium lines (H II) in absorption.", "Rigel Spica"),

	A("Blue", 7500, 3.2, 2.5, 80, "Hydrogen (H) lines strongest for A0 stars, decreasing for other A's.", "Sirius, Vega"),

	F("Blue to White", 6000, 1.7, 1.3, 6, "Ca II absorption. Metallic lines become noticeable.", "Canopus, Procyon"),

	G("White to Yellow", 5000, 1.1, 1.1, 1.2, "Absorption lines of neutral metallic atoms and ions (e.g. once-ionized calcium).",
			"Sun, Capella"),

	K("Orange to Red", 3500, 0.8, 0.9, 0.4, "Metallic lines, some blue continuum.", "Arcturus, Aldebaran"),

	M("Red	under", 3500, 0.3, 0.4, 0.04, "Some molecular bands of titanium oxide.", "Betelgeuse, Antares");

	private final String color;
	private final int surfaceTemperature;
	private final double avgMass;
	private final double avgRadius;
	private final double avgLumen;
	private final String characteristics;
	private final String examples;

	private StarClassification(String color, int surfaceTemperature, double avgMass, double avgRadius, double avgLumen,
			String characteristics, String examples) {
		this.color = color;
		this.surfaceTemperature = surfaceTemperature;
		this.avgMass = avgMass;
		this.avgRadius = avgRadius;
		this.avgLumen = avgLumen;
		this.characteristics = characteristics;
		this.examples = examples;
	}

	public String getColor() {
		return color;
	}

	public int getSurfaceTemperature() {
		return surfaceTemperature;
	}

	public double getAvgMass() {
		return avgMass;
	}

	public double getAvgRadius() {
		return avgRadius;
	}

	public double getAvgLumen() {
		return avgLumen;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public String getExamples() {
		return examples;
	}

}
