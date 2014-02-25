package no.jgdx.perseus.ships.harvesters;

import no.jgdx.perseus.assets.Allotrope;

/**
 * Defines the harvester type, what it harvests/mines and at what speed it does
 * so.
 * 
 * @author pgd
 * 
 */
public enum HarvesterClassification {
	SPICE_SHIP(2, Allotrope.SULFUR) /* get it? */,

	CARBON_COLLECTOR(36, Allotrope.CARBON),

	PHOSPORUS_MINER(12, Allotrope.PHOSPORUS),

	OXYGEN_MINER(17, Allotrope.OXYGEN),

	SULFUR_MINER(16, Allotrope.SULFUR),

	SELENIUM_MINER(72, Allotrope.SELENIUM),

	DYSON_SPHERE(450, Allotrope.OXYGEN);

	private final double harvestSpeed;

	private final Allotrope allotrope;

	private HarvesterClassification(double harvestSpeed, Allotrope allotrope) {
		this.harvestSpeed = harvestSpeed;
		this.allotrope = allotrope;
	}

	public double getHarvestSpeed() {
		return harvestSpeed;
	}

	public Allotrope getAllotrope() {
		return allotrope;
	}

	@Override
	public String toString() {
		return this.name() + ": " + getHarvestSpeed() + " " + getAllotrope();
	}

}
