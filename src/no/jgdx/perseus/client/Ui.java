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

public class Ui extends JPanel {
	private static final long serialVersionUID = 1L;

	private final Game game;

	public Ui() {
		this.game = Game.getInstance();

		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.RED);

		for (Ship s : game.getShips()) {
			if (s instanceof HqShip)
				g.drawString(s.toString(), (int) s.getPosition().getX(),
						(int) s.getPosition().getY());
			else
				g.drawString(s.toString(), (int) s.getPosition().getX(),
						(int) s.getPosition().getY());
		}

		g.setColor(Color.YELLOW);

		for (Celestial cel : game.getCelestials()) {
			g.drawString(cel.getName(), (int) cel.getPosition().getX(),
					(int) cel.getPosition().getY());
		}

		g.setColor(Color.BLUE);

		for (SpaceStation ss : game.getStations()) {
			g.drawString(ss.toString(), (int) ss.getPosition().getX(), (int) ss
					.getPosition().getY());
		}
	}

	public static void main(String[] args) {
		Ui ui = new Ui();
		JFrame frame = new JFrame("Constellation Perseus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(1280, 760));

		frame.add(ui);
		frame.setVisible(true);

		while (true) {
			try {
				Thread.sleep(75);

				ui.game.tick();
				ui.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
