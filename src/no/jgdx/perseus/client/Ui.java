package no.jgdx.perseus.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Moon;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.celestials.Star;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.Wreck;

public class Ui extends JPanel {
	private final Game game;

	public Ui() {
		this.game = new Game();

		Ship w1 = new Wreck();
		w1.setPosition(new Position(200, 100, 50));

		Ship w2 = new Wreck();
		w2.setPosition(new Position(100, 200, 50));

		Star s = Star.SOL;
		s.setPosition(new Position(400, 400, 400));

		game.addGameObject(w1);
		game.addGameObject(w2);

		game.addGameObject(s);

		Moon earth = new Moon(1, 10, 130, "Earth", s.getPosition().add(new Position(10, 10, 10)), s);

		Moon moon = new Moon(1, 35, 60, "Moon", earth.getPosition().add(new Position(10, 10, 20)), earth);

		game.addGameObject(earth);
		game.addGameObject(moon);

		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.RED);

		for (Ship s : game.getShips()) {
			g.drawString(s.getName(), (int) s.getPosition().getX(), (int) s.getPosition().getY());
		}

		g.setColor(Color.YELLOW);

		for (Celestial cel : game.getCelestials()) {
			g.drawString(cel.getName(), (int) cel.getPosition().getX(), (int) cel.getPosition().getY());
		}
	}

	public static void main(String[] args) {
		Ui ui = new Ui();
		JFrame frame = new JFrame("Constellation Perseus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(1080, 760));

		frame.add(ui);
		frame.setVisible(true);

		while (true) {
			try {
				Thread.sleep(250);

				ui.game.tick();
				System.out.println(ui.game);

				ui.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
