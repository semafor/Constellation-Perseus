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
import no.jgdx.perseus.ships.ColonialViper;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.BasicOxygenHarvester;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;
import no.jgdx.perseus.stations.SpaceStation;

public class Ui extends JPanel {
	private static final long serialVersionUID = 1L;

	private final Game game;

	public Ui() {
		this.game = new Game();

		setup();

		setBackground(Color.BLACK);
	}

	private void setup() {
		Ship w1 = new ColonialViper(new Position(200, 100, 50));

		Star sol = Star.SOL;
		sol.setPosition(new Position(400, 400, 400));

		game.addGameObject(w1);

		game.addGameObject(sol);

		Moon earth = new Moon(1, 10, 130, "Earth", sol.getPosition().add(new Position(10, 10, 10)), sol);

		Moon moon = new Moon(1, 35, 60, "Moon", earth.getPosition().add(new Position(10, 10, 20)), earth);

		game.addGameObject(earth);
		game.addGameObject(moon);

		HqShip hq = new HqShip("HeadQuarter", Position.ORIGIN);
		hq.setStar(sol);
		game.addGameObject(hq);

		Harvester oxMin = new BasicOxygenHarvester(hq.getPosition());
		hq.addHarvester(oxMin);
		game.addGameObject(oxMin);

		ShipYard yard = new ShipYard(hq.getPosition(), game);
		game.addGameObject(yard);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.RED);

		for (Ship s : game.getShips()) {
			if (s instanceof HqShip)
				g.drawString(s.toString(), (int) s.getPosition().getX(), (int) s.getPosition().getY() - 10);
			else
				g.drawString(s.toString(), (int) s.getPosition().getX(), (int) s.getPosition().getY());
		}

		g.setColor(Color.YELLOW);

		for (Celestial cel : game.getCelestials()) {
			g.drawString(cel.getName(), (int) cel.getPosition().getX(), (int) cel.getPosition().getY() + 10);
		}

		g.setColor(Color.BLUE);

		for (SpaceStation ss : game.getStations()) {
			g.drawString(ss.toString(), (int) ss.getPosition().getX(), (int) ss.getPosition().getY() + 20);
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
				// System.out.println(ui.game);

				ui.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
