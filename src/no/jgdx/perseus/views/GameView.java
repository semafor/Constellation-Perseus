package no.jgdx.perseus.views;

import java.util.ArrayList;
import java.util.List;

import no.jgdx.perseus.Messages;
import no.jgdx.perseus.players.Player;

public class GameView {

	private final List<Messages> messages;

	private Player player;

	public GameView(Player player) {
		this.player = player;
		this.messages = new ArrayList<Messages>();
	}

}
