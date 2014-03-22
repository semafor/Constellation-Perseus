package no.jgdx.perseus.views.gamepanels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.views.TickablePanel;

/**
 * statusPanel is the container for: playerInfoPanel playerAssetsPanel
 * playerMessagesPanel
 */
public class PlayerStatus extends JPanel implements GamePanels, TickablePanel {

	private static final long serialVersionUID = 1L;

	private final Player player;

	private PlayerInfo playerInfo;

	private PlayerMessages playerMessages;

	private PlayerAssets playerAssets;

	public PlayerStatus(Player player) {

		this.player = player;
		
		setup();

	}

	private void setup() {

		GridLayout l = new GridLayout(0, 3);
		setLayout(l);

		this.playerInfo = new PlayerInfo(player);
		this.playerMessages = new PlayerMessages(player);
		this.playerAssets = new PlayerAssets(player);

		add(playerInfo);
		add(playerAssets);
		add(playerMessages);

	}

	@Override
	public void render() {

		playerInfo.render();
		playerMessages.render();
		playerAssets.render();

		repaint();

	}

	@Override
	public void tick() {
		
		playerAssets.tick();
		playerMessages.tick();
		
	}

}
