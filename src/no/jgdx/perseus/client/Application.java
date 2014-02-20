package no.jgdx.perseus.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.stations.SpaceStation;

public class Application {

	private final Game game;

	public Application() {
		this.game = new Game();
	}

	public static void main(String[] args) {
		Application ui = new Application();
		JFrame frame = new JFrame("Constellation Perseus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(1080, 760));

		frame.setVisible(true);

		while (true) {
			try {
				Thread.sleep(75);

				ui.game.tick();
				// System.out.println(ui.game);

				//ui.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
