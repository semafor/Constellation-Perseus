package no.jgdx.perseus;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import no.jgdx.perseus.Game.MainMenuItems;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;
import no.jgdx.perseus.stations.SpaceStation;

public class View {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JPanel> panels;
	
	private final JFrame frame;

	private final JPanel top = new JPanel();
	private final JLabel topTitle = new JLabel();
	
	private final JPanel middle = new JPanel();
	private ArrayList<MainMenuButton> mainMenuButtons;
	
	private final JPanel bottom = new JPanel();
	
	private final GroupLayout layout;
	
	public View(String title) {
		frame = new JFrame(title);
		
		layout = new GroupLayout(frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		
		this.panels = new ArrayList<JPanel>();
		panels.add(top);
		panels.add(middle);
		panels.add(bottom);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(1080, 800));

		setup();

		setTopTitle(title);
	}
	
	private void setup() {
		
		top.add(createTitle());
		top.setBorder(new MatteBorder(5, 0, 0, 0, new Color(0, 0, 0, 0)));
		top.setPreferredSize(new Dimension(frame.getWidth(),75));
		
		middle.setBorder(new MatteBorder(1, 0, 1, 0, new Color(255, 255, 255, 50)));
		middle.setPreferredSize(new Dimension(frame.getWidth(),675));
		
		bottom.add(new JLabel("Bottom area"));
		bottom.setPreferredSize(new Dimension(frame.getWidth(),50));
	
		// add all panels to frame
	    layout.setHorizontalGroup(
	            layout.createParallelGroup()
                	.addComponent(top, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                	.addComponent(middle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                	.addComponent(bottom, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
	    
	    layout.setVerticalGroup(
	            layout.createSequentialGroup()
                	.addComponent(top, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                	.addComponent(middle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addComponent(bottom, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
				
	    frame.pack();
	    frame.setVisible(true);
	    
		// repaint all panels after JFrame was shown	
		for (JPanel p : panels) {
			p.setBackground(new Color(0));
		    p.setVisible(true);
		    p.revalidate();
		    p.repaint();
		}
		
		frame.pack();

	}
	
	private JLabel createTitle() {
		Font f = new Font("Ubuntu Light", Font.PLAIN, 42);
		topTitle.setFont(f);
		topTitle.setForeground(new Color(255, 255, 255, 255));
		return topTitle;
	}
	
	public void setTopTitle(String title) {
		topTitle.setText(title);
	}
	
	private void renderMainMenuButtons(ArrayList<MainMenuItems> items) {
		
		this.mainMenuButtons = new ArrayList<MainMenuButton>();
		
		for(MainMenuItems m : items) {
			MainMenuButton b = new MainMenuButton(m.getLabel(), m);
			mainMenuButtons.add(b);
			middle.add(b);
		}
		
		middle.setLayout(new GridLayout(items.size(), 0, 10, 10));
		
	}
	
	public void showMainMenu(ArrayList<MainMenuItems> items) {
		renderMainMenuButtons(items);
		
	}
	
	public void addMainMenuListener(ActionListener listener) {
		for (MainMenuButton b : mainMenuButtons) {
			b.addActionListener(listener);
		}
	}

	public class MainMenuButton extends JButton {

		private static final long serialVersionUID = 1L;
		
		private MainMenuItems content;
		
		public MainMenuButton(String label, MainMenuItems content) {
			super(label);
			this.content = content;
			setForeground(Color.white);
			setBackground(Color.black);
			setBorder(null);
			Font f = new Font("Ubuntu", Font.PLAIN, 24);
			setFont(f);
			
		}
		
		public MainMenuItems getButtonContent() {
			return content;
		}
	}
	
	public void showCredits(List<String> contributors) {
		setTopTitle("Credits");
		middle.removeAll();
		middle.setLayout(new FlowLayout(0, 10, 10));
		
		for(String c : contributors) {
			JLabel l = new JLabel(c);
			l.setForeground(new Color(255, 255, 255, 255));
			middle.add(l);
		}
		
	}
	
	public void showNewGame(List<Ship> ships, List<Celestial> celestials, List<SpaceStation> spaceStations) {
		setTopTitle("GAME STURTED");
		middle.removeAll();
		middle.setLayout(new GridLayout());
		middle.add(new GamePanel(ships, celestials, spaceStations));
	}

	public void tick() {
		frame.repaint();
	}
	
	private class GamePanel extends JPanel {
		
		private static final long serialVersionUID = 1L;

		private List<Ship> ships;
		
		private List<Celestial> celestials;
		
		private List<SpaceStation> spaceStations;
		
		public GamePanel(List<Ship> ships, List<Celestial> celestials, List<SpaceStation> spaceStations) {
			this.ships = ships;
			this.celestials = celestials;
			this.spaceStations = spaceStations;
			this.setBackground(Color.black);
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);

			// SHIPS
			for (Ship s : ships) {
				g.setColor(Color.RED);
				Position pos = s.getPosition();
				int x = (int) pos.getX();
				int y = (int) pos.getY();

				if (s instanceof HqShip)
					g.drawString("HQ", x, y);

				else if (s instanceof Harvester) {
					int percentage = ((Harvester) s).getPercentage();
					g.drawString("■", x, y);
					g.setColor(Color.CYAN);
					g.drawLine(x, y, x + percentage, y);
				}

				else {
					g.drawString("Δ", x, y);
				}
			}

			// CELESTIALS
			for (Celestial cel : celestials) {
				g.setColor(Color.YELLOW);
				Position pos = cel.getPosition();
				int x = (int) pos.getX();
				int y = (int) pos.getY();
				g.drawString(cel.getName(), x, y);
			}

			// SPACE STATIONS
			for (SpaceStation ss : spaceStations) {
				g.setColor(Color.GREEN);
				Position pos = ss.getPosition();
				int x = (int) pos.getX();
				int y = (int) pos.getY();

				if (ss instanceof ShipYard)
					g.drawString("○", x, y);
				else
					g.drawString("Station", x, y);
			}
		}
	}
	/*
*/
	
}



