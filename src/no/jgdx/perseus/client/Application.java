package no.jgdx.perseus.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	/**
	 * 
	 * @param pos
	 * @param button
	 *            see MouseEvent.button
	 */
	private void userClick(Position pos, int button) {
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
				if (game.buy(cv)) {
					sy.constructShip(cv, Game.now());
				} else {
					System.err.println("Could not afford");
				}
			} else if (button == MouseEvent.BUTTON2) {
				System.out.println("Constructing Oxygen Harvester");
				sy.constructShip(new BasicOxygenHarvester(placement, sy.getHq(), sy.getOwner()), Game.now());
			} else {
				System.out.println("Constructing Carbon Harvester");
				sy.constructShip(new BasicCarbonHarvester(placement, sy.getHq(), sy.getOwner()), Game.now());
			}
			selectedObject = null;
			return;
		}

		if (o instanceof Ship) {
			Ship s = (Ship) o;
			System.out.println("Selected " + s);
			selectedObject = s;
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
		selectedObject = null;
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
