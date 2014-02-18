package no.jgdx.perseus.weapons;

import no.jgdx.perseus.GameObject;

public abstract class Gun implements GameObject {

	protected int ammo;

	protected final double damageCaused;

	protected final double precision;

	protected final long range;

	protected final double rechargeTime;

	protected final String name;

	public Gun(int ammo, double damageCaused, double precision, long range, double rechargeTime, String name) {
		this.ammo = ammo;
		this.damageCaused = damageCaused;
		this.precision = precision;
		this.range = range;
		this.rechargeTime = rechargeTime;
		this.name = name;
	}

	/**
	 * Returns amount of ammo left
	 * 
	 * @return ammo left
	 */
	public int getAmmonution() {
		return ammo;
	}

	/**
	 * Amount of damage this gun causes, between [0,1].
	 * 
	 * @return
	 */
	public double getCausingDamage() {
		return damageCaused;
	}

	/**
	 * Inherent percentage [0,1] miss probability
	 * 
	 * @return
	 */
	public double getPrecision() {
		return precision;
	}

	/**
	 * Maximum range of weapon specified in meters
	 * 
	 * @return range in meters
	 */
	public long getRange() {
		return range;
	}

	/**
	 * Time to recharge. Given in milliseconds
	 * 
	 * @return time it takes to recharge (ms)
	 */
	public double getRechargeTime() {
		return rechargeTime;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ammo;
		long temp;
		temp = Double.doubleToLongBits(damageCaused);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(precision);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (range ^ (range >>> 32));
		temp = Double.doubleToLongBits(rechargeTime);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gun other = (Gun) obj;
		if (ammo != other.ammo)
			return false;
		if (Double.doubleToLongBits(damageCaused) != Double.doubleToLongBits(other.damageCaused))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(precision) != Double.doubleToLongBits(other.precision))
			return false;
		if (range != other.range)
			return false;
		if (Double.doubleToLongBits(rechargeTime) != Double.doubleToLongBits(other.rechargeTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gun [ammo=" + ammo + ", damageCaused=" + damageCaused + ", precision=" + precision + ", range=" + range
				+ ", rechargeTime=" + rechargeTime + ", name=" + name + "]";
	}

}
