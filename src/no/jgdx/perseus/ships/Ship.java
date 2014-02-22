package no.jgdx.perseus.ships;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.GameObjectState;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.Gun;

public abstract class Ship implements GameObject {

	protected final List<Gun> guns;

	private final String name;

	private final ShipClassification classification;

	protected double damage = 1;

	protected GameObjectState state = GameObjectState.IDLE;

	private Position position;

	private final long coolDownTime;

	private long lastJumpTime = Long.MIN_VALUE;

	public Ship(String name, ShipClassification classification, Position position, long coolDown) {
		guns = new ArrayList<>();
		this.name = name;
		this.classification = classification;
		this.position = position;
		this.coolDownTime = coolDown;
	}

	public boolean isReadyToJump() {
		boolean canjump = getCooldownTimeLeft() == 0;

		// premature optimization
		if (canjump)
			lastJumpTime = Long.MIN_VALUE;

		return canjump;
	}

	public long getCooldownTimeLeft() {
		if (lastJumpTime == Long.MIN_VALUE)
			return 0;

		long now = Game.now();

		long diff = now - lastJumpTime;
		if (diff >= coolDownTime)
			return 0;

		return coolDownTime - diff;
	}

	public boolean jumpTo(Position position) {
		if (!isReadyToJump())
			return false;

		Game.getInstance().assignPosition(this, position);

		lastJumpTime = Game.now();
		return true;
	}

	public Position getPosition() {
		return position;
	}

	@Deprecated
	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Gun> getGuns() {
		return guns;
	}

	public String getName() {
		return name;
	}

	public ShipClassification getClassification() {
		return classification;
	}

	@Override
	public double getDamage() {
		return damage;
	}

	@Override
	public String toString() {
		String s = "Ship " + classification;

		if (!isReadyToJump()) {
			s += " (cooling down ... ";
			s += (getCooldownTimeLeft() / 1000L) + ")";
		}
		return s;
	}

	protected GameObjectState getState() {
		return this.state;
	}

	protected void setState(GameObjectState state) {
		this.state = state;
	}

	protected boolean destructor() {
		return true;
	}

	public void destroy() {
		if (destructor()) {
			setState(GameObjectState.DESTROYED);
		}
	}

	public boolean destroyed() {
		return getState() == GameObjectState.DESTROYED;
	}

	public boolean idle() {
		return getState() == GameObjectState.IDLE;
	}

}
