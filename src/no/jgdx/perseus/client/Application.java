package no.jgdx.perseus.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.View;
import no.jgdx.perseus.View.MainMenuButton;

public class Application {

	private final Game game;
	
	private final View view;

	public Application() {
		this.game = Game.getInstance();
		this.view = new View("Constellation Perseus");
		
		display();
	}
	
	private void mainMenu() {

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuButton b = (MainMenuButton)e.getSource();
				display(b.getButtonContent());
				
			}
		};
		
		view.showMainMenu(game.getMainMenuItems());
		view.addMainMenuListener(listener);
		
	}
	
	private void display() {
		mainMenu();
	}
	
	private void display(Game.MainMenuItems item) {
		if(item == Game.MainMenuItems.CREDITS) {
			view.showCredits(game.getContributors());
		} else if(item == Game.MainMenuItems.NEW_GAME) {
			view.showNewGame(game.getShips(), game.getCelestials(), game.getStations());
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
