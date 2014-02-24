package no.jgdx.perseus.views;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import no.jgdx.perseus.Messages;
import no.jgdx.perseus.players.Player;

public class GameView extends Screen implements InGameInterface {

	private final List<Messages> messages;

	private final JPanel statusPanel = new JPanel();

	private final JPanel HUDPanel = new JPanel();

	private final JPanel gamePanel = new JPanel();

	private final JPanel playerInfoPanel = new JPanel();

	private final JPanel playerAssetsPanel = new JPanel();

	private final JPanel playerMessagesPanel = new JPanel();

	private final JPanel selectionVisualPanel = new JPanel();

	private final JPanel selectionActionsPanel = new JPanel();

	private final JPanel selectionStatusPanel = new JPanel();

	private final JPanel miniMapPanel = new JPanel();

	private Player player;

	public GameView(LayoutManager m, Player player) {
		super(m);
		this.player = player;
		this.messages = new ArrayList<Messages>();
	}

	@Override
	public JPanel getStatusPanel() {
		return statusPanel;
	}

	@Override
	public JPanel getHUDPanel() {
		return HUDPanel;
	}

	@Override
	public JPanel getGamePanel() {
		return gamePanel;
	}

	@Override
	public JPanel getPlayerInfoPanel() {
		return playerInfoPanel;
	}

	@Override
	public JPanel getPlayerAssetsPanel() {
		return playerAssetsPanel;
	}

	@Override
	public JPanel getPlayerMessagesPanel() {
		return playerMessagesPanel;
	}

	@Override
	public JPanel getSelectionVisualRepresentationPanel() {
		return selectionVisualPanel;
	}

	@Override
	public JPanel getSelectionAvailableActionsPanel() {
		return selectionActionsPanel;
	}

	@Override
	public JPanel getSelectionCurrentStatusPanel() {
		return selectionStatusPanel;
	}

	@Override
	public JPanel getMiniMapPanel() {
		return miniMapPanel;
	}

}
