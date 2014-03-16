package no.jgdx.perseus.views.gamepanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.players.HumanPlayer;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;
import no.jgdx.perseus.stations.SpaceStation;
import no.jgdx.perseus.views.TickablePanel;

/**
 * HUDPanel is the rendering of the {@link Game}
 */
public class GamePanel extends JPanel implements GamePanels, TickablePanel {

	private static final long serialVersionUID = 1L;

	private List<Ship> ships;

	private List<Celestial> celestials;

	private List<SpaceStation> spaceStations;

	private GameObject currentSelectedGameObject;

	public GamePanel(List<Ship> ships, List<Celestial> celestials,
			List<SpaceStation> spaceStations) {
		this.ships = ships;
		this.celestials = celestials;
		this.spaceStations = spaceStations;
		setBackground(new Color(0, 0, 0, 100));
		setFocusable(true);

		getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "released");
		
		setup();

	}

	public void addKListener(final KeyListener keyListener) {

		addKeyListener(keyListener);

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				if (event.getID() == KeyEvent.KEY_TYPED) {
					keyListener.keyTyped(event);
					return true;
				}
				return false;
			}
		});

	}

	public void addMListener(MouseListener mouseListener) {
		addMouseListener(mouseListener);
	}

	public void setCurrentlySelectedGameObject(GameObject g) {
		this.currentSelectedGameObject = g;
	}

	public GameObject getCurrentlySelectedGameObject() {
		return currentSelectedGameObject;
	}

	private void drawHighlight(Graphics g, int x, int y) {
		g.setColor(Color.GRAY);
		g.drawLine(x + 5, y + 5, x + 10, y + 10);
		g.drawLine(x - 5, y + 5, x - 10, y + 10);
		g.drawLine(x + 5, y - 5, x + 10, y - 10);
		g.drawLine(x - 5, y - 5, x - 10, y - 10);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// System.out.println("Have " + ships.size() + " ships");

		Game game = Game.getInstance();

		// SHIPS
		for (Ship s : ships) {
			if (getCurrentlySelectedGameObject() == s) {
				drawHighlight(g, (int) s.getPosition().getX(), (int) s
						.getPosition().getY());
			}
			if (s.getOwner() instanceof HumanPlayer)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			Position pos = game.getPositionOfObject(s);
			int x = (int) pos.getX();
			int y = (int) pos.getY();

			if (s instanceof HqShip)
				g.drawString("⛫  HQ", x, y);

			else if (s instanceof Harvester) {
				int percentage = ((Harvester) s).getPercentage();
				String t = "";
				if (!s.isReadyToJump())
					t += String.format(" (%.1f)",
							(s.getCooldownTimeLeft() / 1000f));

				g.drawString("⛴ " + s + t, x, y);

				// progress bar!
				g.setColor(Color.DARK_GRAY);
				g.drawRect(x, y, 100, 4);

				g.setColor(Color.CYAN);
				g.fillRect(x + 1, y + 1, percentage, 2);

			}

			else {
				if (s.isReadyToJump())
					g.drawString("✈", x, y);
				else {
					String t = String.format("%.1f",
							(s.getCooldownTimeLeft() / 1000f));
					g.drawString("✈ " + t, x, y);
				}
			}
		}

		// CELESTIALS
		for (Celestial cel : celestials) {
			if (getCurrentlySelectedGameObject() == cel) {
				drawHighlight(g, (int) cel.getPosition().getX(), (int) cel
						.getPosition().getY());
			}
			g.setColor(Color.YELLOW);
			if (cel instanceof Star) {
				Star s = (Star) cel;
				g.setColor(s.getStarClassification().getAllotrope().getColor());
			}
			Position pos = game.getPositionOfObject(cel);
			int x = (int) pos.getX();
			int y = (int) pos.getY();
			g.drawString("★ " + cel.getName(), x, y);
		}

		// SPACE STATIONS
		for (SpaceStation ss : spaceStations) {
			if (getCurrentlySelectedGameObject() == ss) {
				drawHighlight(g, (int) ss.getPosition().getX(), (int) ss
						.getPosition().getY());
			}
			if (ss.getOwner() instanceof HumanPlayer)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);

			Position pos = game.getPositionOfObject(ss);
			int x = (int) pos.getX();
			int y = (int) pos.getY();

			if (ss instanceof ShipYard)
				g.drawString("⛶", x, y);
			else
				g.drawString("Station", x, y);
		}
	}

	private void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {

	}

	@Override
	public void tick() {
		
		
	}
}
