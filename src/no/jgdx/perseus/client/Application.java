package no.jgdx.perseus.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.GameObject;
import no.jgdx.perseus.View;
import no.jgdx.perseus.View.MainMenuButton;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.BasicCarbonHarvester;
import no.jgdx.perseus.ships.harvesters.BasicOxygenHarvester;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;

public class Application {

	public final static float MOUSE_SLACK = 20;

	private final Game game;

	private final View view;

	public Application() {
		this.game = Game.getInstance();
		this.view = new View("Constellation Perseus");

		display();
	}

	// sets up the main menu
	// listens in on menu button actions
	private void mainMenu() {

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuButton b = (MainMenuButton) e.getSource();
				display(b.getButtonContent());

			}
		};

		view.showMainMenu(game.getMainMenuItems());
		view.addMainMenuListener(listener);

	}

	private void display() {
		mainMenu();
	}

	private GameObject selectedObject = null;

	private void userTyped(KeyEvent key) {
		System.out.println(getClass().getSimpleName() + ".userTyped = " + key.getKeyChar());
		if (selectedObject instanceof ShipYard) {
			ShipYard sy = (ShipYard) selectedObject;
			Position placement = selectedObject.getPosition();
			char c = key.getKeyChar();

			Ship s = null;

			switch (c) {
			case 'v':
				System.out.println("Ordered new viper to go to " + Star.MAIA);
				s = new ColonialViper(game.getPositionOfObject(Star.MAIA), sy.getOwner());
				break;
			case 'o':
				System.out.println("Ordered new oxygen miner");
				s = new BasicOxygenHarvester(placement, sy.getHq(), sy.getOwner());
				break;
			case 'c':
				System.out.println("Ordered new carbon miner");
				s = new BasicCarbonHarvester(placement, sy.getHq(), sy.getOwner());
				break;
			}

			if (s == null)
				return;

			if (game.buy(s, Game.getInstance().getHumanPlayer())) {
				sy.constructShip(s, Game.now());
				System.out.println("Bought " + s);
			} else {
				System.err.println("Could not afford to buy " + s);
			}
		}
	}

	/**
	 * 
	 * @param pos
	 * @param button
	 *            see MouseEvent.button
	 */
	private void userClick(Position pos, int button) {
		//
		// README: If you return early from this function, remember that
		// selectedObject should be sync'd with
		// view.getCurrentlySelctedGameObject!
		//
		GameObject o = game.getObject(pos, MOUSE_SLACK);
		System.out.println("Click " + pos + " ... " + o);

		view.setCurrentlySelectedGameObject(o);

		// user pressed the shipyard, probably wants to build a ship
		if (o instanceof ShipYard) {
			ShipYard sy = ((ShipYard) o);
			// arbitrary placement
			Position placement = game.getPositionOfObject(Star.MAIA);

			if (button == MouseEvent.BUTTON1) {
				System.out.println("Constructing viper?");
				Ship cv = new ColonialViper(placement, sy.getOwner());
				if (game.buy(cv, Game.getInstance().getHumanPlayer())) {
					sy.constructShip(cv, Game.now());
				} else {
					System.err.println("Could not afford viper");
				}
			} else if (button == MouseEvent.BUTTON2) {
				System.out.println("Constructing Oxygen Harvester");
				Ship s = new BasicOxygenHarvester(placement, sy.getHq(), sy.getOwner());
				if (game.buy(s, Game.getInstance().getHumanPlayer())) {
					sy.constructShip(s, Game.now());
				} else {
					System.err.println("Could not afford oxygen harvester");
				}
			} else {
				System.out.println("Constructing Carbon Harvester");
				Ship s = new BasicCarbonHarvester(placement, sy.getHq(), sy.getOwner());
				if (game.buy(s, Game.getInstance().getHumanPlayer())) {
					sy.constructShip(s, Game.now());
				} else {
					System.err.println("Could not afford carbon harvester");
				}
			}
			selectedObject = view.getCurrentlySelectedGameObject();
			return;
		}

		if (o instanceof Ship) {
			Ship s = (Ship) o;
			System.out.println("Selected " + s);

			if (s instanceof HqShip) {
				if (selectedObject instanceof Harvester) {
					System.out.println("Sending harvester home!");

					Harvester h = (Harvester) selectedObject;
					h.sendHome((HqShip) s);

					selectedObject = view.getCurrentlySelectedGameObject();
					return;
				}
			}

			selectedObject = view.getCurrentlySelectedGameObject();
			return;
		}

		if (selectedObject instanceof Ship) {
			if (selectedObject instanceof Harvester && o != null && o instanceof Star) {
				((Harvester) selectedObject).setStar((Star) o);
			} else if (o != null && o instanceof Celestial) {
				game.sendShipToCelestial((Ship) selectedObject, (Celestial) o);
				System.out.println("Sending ship to star " + o.getName());
			} else {

				System.out.println("Requesting ship to jump to " + pos);
				boolean success = ((Ship) selectedObject).jumpTo(pos);

				System.out.println(success ? "Jump successful!" : "No jump this time.");
			}
		}
		selectedObject = view.getCurrentlySelectedGameObject();
	}

	// display this or that
	private void display(Game.MainMenuItems item) {
		if (item == Game.MainMenuItems.CREDITS) {
			view.showCredits(game.getContributors());
		} else if (item == Game.MainMenuItems.NEW_GAME) {

			// XXX: let view query game directly?
			view.showNewGame(game.getShips(), game.getCelestials(), game.getStations(), new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Position pos = new Position(e.getX(), e.getY(), 0);
					userClick(pos, e.getButton());
				}
			}, new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					System.out.println();
					userTyped(e);
				}
			});
		}
	}

	public static void main(String[] args) {

		Application ui = new Application();

		while (true) {
			try {
				Thread.sleep(75);

				ui.game.tick();
				// System.out.println(ui.game);

				ui.view.tick();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
