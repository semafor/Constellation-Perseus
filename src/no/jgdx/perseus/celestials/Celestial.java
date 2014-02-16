package no.jgdx.perseus.celestials;

import no.jgdx.perseus.GameObject;

/**
 * Astronomical objects or celestial objects are naturally occurring physical
 * entities, associations or structures that current science has demonstrated to
 * exist in the observable universe. The term astronomical object is sometimes
 * used interchangeably with astronomical body. Typically, an astronomical
 * (celestial) body refers to a single, cohesive structure that is bound
 * together by gravity (and sometimes by electromagnetism). Examples include the
 * asteroids, moons, planets and the stars. Astronomical objects are
 * gravitationally bound structures that are associated with a position in
 * space, but may consist of multiple independent astronomical bodies or
 * objects. These objects range from single planets to star clusters, nebulae or
 * entire galaxies. A comet may be described as a body, in reference to the
 * frozen nucleus of ice and dust, or as an object, when describing the nucleus
 * with its diffuse coma and tail.
 * 
 * @author jonas
 * 
 */
public abstract class Celestial implements GameObject {

	protected final double lumen;
	protected final double mass;
	protected final double radius;

	protected final String name;

	protected Position pos;

	public Celestial(double luminosity, double mass, double radius,
			String name, Position position) {

		this.lumen = luminosity;
		this.mass = mass;
		this.radius = radius;
		this.name = name;

		this.pos = position;
	}

	public Position getPosition() {
		return pos;
	}

	public void setPosition(Position position) {
		this.pos = position;
	}

	/**
	 * Returns the mass (kg) of this celestial object in solar mass unit.
	 * 
	 * @return mass of object
	 */
	public double getSolarMass() {
		return mass;
	}

	/**
	 * Returns the luminosity (watt) of this celestial object in solar
	 * luminosity unit.
	 * 
	 * @return luminosity of object
	 */
	double getSolarLuminosity() {
		return lumen;
	}

	/**
	 * Returns the radius (meter) of this celestial object in solar radius unit.
	 * 
	 * @return radius of object
	 */
	double getSolarRadius() {
		return radius;
	}

	/**
	 * The name of this celestial object.
	 * 
	 * @return name of celestial
	 */
	public String getName() {
		return name;
	}

}