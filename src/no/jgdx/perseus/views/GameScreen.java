package no.jgdx.perseus.views;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;

import no.jgdx.perseus.Messages;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.stations.SpaceStation;
import no.jgdx.perseus.views.gamepanels.GamePanel;
import no.jgdx.perseus.views.gamepanels.HUD;
import no.jgdx.perseus.views.gamepanels.PlayerStatus;

public class GameScreen extends Screen {

	private final List<Messages> messages = new ArrayList<Messages>();

	private final List<Object> possibleActions = new ArrayList<Object>();

	private PlayerStatus playerStatus;

	private HUD hud;

	private GamePanel gamePanel;

	private Player player;

	private List<Ship> ships;

	private List<Celestial> celestials;

	private List<SpaceStation> spaceStations;

	public GameScreen(Player player, List<Ship> ships,
			List<Celestial> celestials, List<SpaceStation> spaceStations,
			MouseListener mouseListener, final KeyListener keyListener,
			Container host) {

		super();

		this.player = player;
		this.ships = ships;
		this.celestials = celestials;
		this.spaceStations = spaceStations;

		this.playerStatus = new PlayerStatus(player);
		this.gamePanel = new GamePanel(ships, celestials, spaceStations);

		gamePanel.addKListener(keyListener);
		gamePanel.addMListener(mouseListener);

		this.hud = new HUD();

		setup(host);

	}

	protected void setUpLayoutManager(Container host) {

		GroupLayout layout = new GroupLayout(host);

		playerStatus.setPreferredSize(new Dimension(host.getWidth(), 30));
		gamePanel.setPreferredSize(new Dimension(host.getWidth(), 675));
		hud.setPreferredSize(new Dimension(host.getWidth(), 100));

		// add all panels to frame
		layout.setHorizontalGroup(layout
				.createParallelGroup()
				.addComponent(playerStatus, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(hud, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addComponent(playerStatus, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(hud, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));

		setLayoutManager(layout);
		host.setLayout(layout);

	}

	private void setup(Container host) {

		setUpLayoutManager(host);

	}

	public void render() {
		playerStatus.render();
		gamePanel.render();
		hud.render();
	}

	public void tick() {
		
		playerStatus.tick();
		
	}

}
