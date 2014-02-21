package no.jgdx.perseus.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.jgdx.perseus.Game;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;
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

		// SHIPS
		g.setColor(Color.RED);

		for (Ship s : game.getShips()) {
			Position pos = s.getPosition();
			int x = (int) pos.getX();
			int y = (int) pos.getY();

			if (s instanceof HqShip)
				g.drawString("HQ", x, y);

			else if (s instanceof Harvester) {
				int percentage = ((Harvester) s).getPercentage();
				g.drawString("Ha", x, y);
				g.drawLine(x, y, x + percentage, y);
			}

			else {
				g.drawString(s.getClass().getSimpleName(), x, y);
			}
		}

		// CELESTIALS
		g.setColor(Color.YELLOW);

		for (Celestial cel : game.getCelestials()) {
			Position pos = cel.getPosition();
			int x = (int) pos.getX();
			int y = (int) pos.getY();
			g.drawString(cel.getName(), x, y);
		}

		// SPACE STATIONS
		g.setColor(Color.GREEN);

		for (SpaceStation ss : game.getStations()) {
			Position pos = ss.getPosition();
			int x = (int) pos.getX();
			int y = (int) pos.getY();

			if (ss instanceof ShipYard)
				g.drawString("Y", x, y);
			else
				g.drawString("Station", x, y);
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
