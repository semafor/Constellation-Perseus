package no.jgdx.perseus;

import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.stations.SpaceStation;
import no.jgdx.perseus.weapons.Gun;

public enum GameObjectActions {

	/**
	 * build specific {@link Ship}s
	 */
	BUILD_SHIP, BUILD_COLONIALVIPER, BUILD_HQSHIP, 
	BUILD_CARBONHARVESTER, BUILD_OXYGENHARVESTER, BUILD_PHOSPHORUSHARVESTER, BUILD_SULFURHARVESTER, BUILD_SELENIUMHARVESTER,

	/**
	 * build specific {@link SpaceStation}s
	 */
	BUILD_SPACESTATIONS, BUILD_SHIPYARD,

	/**
	 * build specific {@link Celestial}s
	 */
	BUILD_CELESTIALS, BUILD_MOON, BUILD_STAR,
	
	/**
	 * build specific {@link Gun}s
	 */
	BUILD_GUNS, BUILD_VIPERMISSILE,

	// defensive actions
	DEFEND, DEFEND_SHIP, DEFEND_STATION, DEFEND_CELESTIAL, DEFEND_COLONIALVIPER, DEFEND_HQSHIP, 
	DEFEND_CARBONHARVESTER, DEFEND_OXYGENHARVESTER, DEFEND_PHOSPHORUSHARVESTER, DEFEND_SULFURHARVESTER, DEFEND_SELENIUMHARVESTER,

	// aggressive actions
	ATTACK, ATTACK_SHIP, ATTACK_STATION, ATTACK_CELESTIAL, ATTACK_COLONIALVIPER, ATTACK_HQSHIP, 
	ATTACK_CARBONHARVESTER, ATTACK_OXYGENHARVESTER, ATTACK_PHOSPHORUSHARVESTER, ATTACK_SULFURHARVESTER, ATTACK_SELENIUMHARVESTER,

	// moods
	PATROL, HOLD_GROUND, PREEMPTIVE_STRIKE, INFILTRATE, KAMIKAZE, SELF_DESTRUCT,

	// harvesting
	HARVEST, HARVEST_OXYGEN, HARVEST_CARBON, HARVEST_PHOSPHORUS, HARVEST_SULFUR, HARVEST_SELENIUM,

	// travelling
	TRAVEL, SUBLIGHT_TRAVEL, INTERSTELLAR_TRAVEL
}
