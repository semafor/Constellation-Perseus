package no.jgdx.perseus.helpers;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Formatting {

	public static String toUnitPrefix(Double n) {
		
		String symbol = null;
		int magnitude = 0;
		

		// determine to which magnitude our number belongs
		// TODO: make quickier
		for (Prefixes pre : Prefixes.values()) {
			if(n >= Math.pow(10, pre.getMagnitude())) {
				symbol = pre.getSymbol();
				magnitude = pre.getMagnitude();
			}
		}
		
		if(symbol == null) {
		
			return n.toString();

		} else {

			DecimalFormat df = new DecimalFormat("#.#");
			df.setRoundingMode(RoundingMode.HALF_EVEN);
			
			Double level = n / Math.pow(10, magnitude);
			return df.format(level) + symbol;
 
		}
		
	}
	
	public static String toUnitPrefix(int n) {
		return toUnitPrefix((double)n);
	}
	
	private enum Prefixes {
		
		KILO("k", "kilo", 3),
		
		MEGA("M", "mega", 6),
		
		GIGA("G", "giga", 9);
		
		private final String symbol;
		
		private final String prefix;
		
		private final int magnitude;
		
		private Prefixes(String symbol, String prefix, int magnitude) {
			this.symbol = symbol;
			this.prefix = prefix;
			this.magnitude = magnitude;
		}
		
		public String getSymbol() {
			return symbol;
		}
		
		public String getPrefix() {
			return prefix;
		}
		
		public int getMagnitude() {
			return magnitude;
		}
		
	}
	
}
