package no.jgdx.perseus.views;

import java.awt.Graphics;

import javax.swing.JPanel;

public class View extends JPanel {
	
	@Override
	public void paint(Graphics g) {
		/*
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
		*/
	}
	
}
