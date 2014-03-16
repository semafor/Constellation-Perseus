package no.jgdx.perseus.views.gamepanels;

import javax.swing.JPanel;

import no.jgdx.perseus.Messages;
import no.jgdx.perseus.players.Player;

/**
 * playerMessagesPanel contains {@link Messages} read and unread to the
 * {@link Player}
 */
public class PlayerMessages extends JPanel implements GamePanels {

	private static final long serialVersionUID = 1L;

	private final Player player;

	public PlayerMessages(Player player) {
		this.player = player;
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}
