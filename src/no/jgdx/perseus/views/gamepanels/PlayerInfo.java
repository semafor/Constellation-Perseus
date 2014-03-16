package no.jgdx.perseus.views.gamepanels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.jgdx.perseus.players.Player;

/**
 * playerInfoPanel talks about who the {@link Player} is, how he/she is doing,
 * where he/she is going, and to an extend what he/she is doing
 */
public class PlayerInfo extends JPanel implements GamePanels {

	private static final long serialVersionUID = 1L;

	private Player player;

	private JLabel name;

	public PlayerInfo(Player player) {

		this.player = player;
		this.name = new JLabel(player.getName());
		setup();

	}

	private void setup() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(name);

	}

	@Override
	public void render() {

		name.setText(player.getName());

	}

}
