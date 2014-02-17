package no.jgdx.perseus.ships;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.harvesters.Harvester;

/**
 * A HqShip is the/a head quarter of a player. A HqShip on creation will yield
 * an element with which the player can advance the game quickly without having
 * to build something as the first step in the game.
 * 
 * What the HqShip yields can depend on race/scenario/etc, so let us be flexible
 * and 
 * 
 * @author pgd, jgd
 * 
 */
public class HqShip extends Ship {

	public HqShip(String name, Position position, GameObject yield) {
		super(name, ShipClassification.HQ, position);
		
		// make game produce whatever should be yielded to the player upon creation
	}

	public void tick(long time) {
	};

}
