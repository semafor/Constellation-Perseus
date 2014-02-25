package no.jgdx.perseus.assets;

import java.awt.Color;

public enum Allotrope {
	OXYGEN("Oxygen", "O", Color.PINK),

	CARBON("Carbon", "C", Color.LIGHT_GRAY),

	PHOSPORUS("Phosphorus", "P", Color.ORANGE),

	SULFUR("Sulfur", "S", Color.MAGENTA),

	SELENIUM("Selenium", "Se", Color.WHITE);

	private final String name;
	private final String abbr;
	private final Color color;

	private Allotrope(String name, String abbr, Color color) {
		this.name = name;
		this.abbr = abbr;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public String getAbbreviation() {
		return abbr;
	}

	@Override
	public String toString() {
		return abbr;
	}
}
