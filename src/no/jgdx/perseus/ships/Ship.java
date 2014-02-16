package no.jgdx.perseus.ships;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.Gun;

public abstract class Ship implements GameObject {

	protected final List<Gun> guns;

	private final String name;

	private final String classification;

	private Position position;

	public Ship(String name, String classification, Position position) {
		guns = new ArrayList<>();
		this.name = name;
		this.classification = classification;
		this.position = position;
	}

	public abstract void tick(long time);

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Gun> getGuns() {
		return guns;
	}

	public String getName() {
		return name;
	}

	public String getClassification() {
		return classification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classification == null) ? 0 : classification.hashCode());
		result = prime * result + ((guns == null) ? 0 : guns.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Ship other = (Ship) obj;
		if (classification == null) {
			if (other.classification != null)
				return false;
		} else if (!classification.equals(other.classification))
			return false;
		if (guns == null) {
			if (other.guns != null)
				return false;
		} else if (!guns.equals(other.guns))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}
