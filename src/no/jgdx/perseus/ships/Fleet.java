package no.jgdx.perseus.ships;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.GameObjectActions;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.players.Player;

public class Fleet implements GameObject {

	private final List<Ship> ships = new ArrayList<>();
	private Position position;
	private String name = "";

	private final List<GameObjectActions> actions = new ArrayList<GameObjectActions>();

	private final Player owner;

	public Fleet(Position position, Player owner) {

		this.position = position;
		this.owner = owner;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	public boolean jumpTo(Position pos) {
		for (Ship s : ships) {
			if (!s.isReadyToJump())
				return false;
		}
		for (Ship s : ships) {
			s.jumpTo(pos);
		}

		return true;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void tick(long time) {
		// a fleet is more like an abstract concept so no action necessary
	}

	@Override
	public double getDamage() {
		// a fleet isn't damaged
		return 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Fleet: " + ships;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((ships == null) ? 0 : ships.hashCode());
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
		Fleet other = (Fleet) obj;
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
		if (ships == null) {
			if (other.ships != null)
				return false;
		} else if (!ships.equals(other.ships))
			return false;
		return true;
	}

	@Override
	public List<GameObjectActions> getPossibleActions() {
		return actions;
	}

	@Override
	public boolean getActionPossible(GameObjectActions action) {
		return actions.contains(action);
	}
}
