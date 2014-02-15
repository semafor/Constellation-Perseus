package no.jgdx.perseus.ships;

import java.util.List;

import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.weapons.Gun;

public interface Ship {
	List<Gun> getGuns();

	String getName();

	String getClassification();

	Position getPosition();

	void setPosition(Position position);
}
