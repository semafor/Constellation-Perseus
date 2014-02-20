package no.jgdx.perseus.assets;

public enum Allotrope {
	OXYGEN("Oxygen", "O"),

	CARBON("Carbon", "C"),

	PHOSPORUS("Phosphorus", "P"),

	SULFUR("Sulfur", "S"),

	SELENIUM("Selenium", "Se");

	private final String name;
	private final String abbr;

	private Allotrope(String name, String abbr) {
		this.name = name;
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbr;
	}

	@Override
	public String toString() {
		return abbr;
	}
}
