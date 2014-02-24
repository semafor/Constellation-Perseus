package no.jgdx.perseus.views;

import javax.swing.JPanel;

import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.GameObjectState;
import no.jgdx.perseus.Messages;

public interface InGameInterface {

	/**
	 * @return The panel holding panels which constitutes the overall status
	 */
	JPanel getStatusPanel();

	/**
	 * @return The HUD panel, or heads up display for selected
	 *         {@link GameObject}s. The main interface with which the player
	 *         interacts
	 */
	JPanel getHUDPanel();

	/**
	 * @return The main game panel, where we visually render what the player can
	 *         see of the world
	 */
	JPanel getGamePanel();

	/**
	 * @return The panel which renders the players colors, name, etc
	 */
	JPanel getPlayerInfoPanel();

	/**
	 * @return informs the player of his/hers assets
	 */
	JPanel getPlayerAssetsPanel();

	/**
	 * @return {@link Messages} sent to player
	 */
	JPanel getPlayerMessagesPanel();

	/**
	 * @return The panel which visually represents whatever {@link GameObject}
	 *         currently selected
	 */
	JPanel getSelectionVisualRepresentationPanel();

	/**
	 * @return The panel which lays out available actions for selected
	 *         {@link GameObject}
	 */
	JPanel getSelectionAvailableActionsPanel();

	/**
	 * @return The panel which displays the {@link GameObject}'s current
	 *         {@link GameObjectState}
	 */
	JPanel getSelectionCurrentStatusPanel();

	/**
	 * @return The panel which displays the mini map
	 */
	JPanel getMiniMapPanel();

}
