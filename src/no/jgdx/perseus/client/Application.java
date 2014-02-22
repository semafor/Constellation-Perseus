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

	private void userClick(Position pos) {
		System.out.println("Click " + pos + " ... " + game.getObject(pos, MOUSE_SLACK));

		// currently no object selected
		if (selectedObject == null) {
			GameObject o = game.getObject(pos, MOUSE_SLACK);
			if (o == null) {
				System.out.println("\tUser missed");
				return;
			} else {
				System.out.println("\tUser hit " + o);
				selectedObject = o;
			}
			System.out.println("\tUser selected object " + selectedObject);

			if (o instanceof ShipYard) {
				System.out.println("\t>User selected a ShipYard!!!");
				ShipYard y = (ShipYard) o;
				y.constructShip(new ColonialViper(Star.MAIA.getPosition(), game.getPlayers().get(0)), Game.now());
				System.out.println(y + " constructing ship ... ");
				selectedObject = null;
			}

			// we already have a selected object, and it is a ship, we will move
			// it
		} else {
			if (selectedObject instanceof Ship) {
				GameObject o = game.getObject(pos, MOUSE_SLACK);

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

		System.out.println(selectedObject == null ? "No object selected" : "User selected " + selectedObject);

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
					userClick(pos);
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
