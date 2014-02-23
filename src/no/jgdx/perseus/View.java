package no.jgdx.perseus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.MatteBorder;

import no.jgdx.perseus.Game.MainMenuItems;
import no.jgdx.perseus.celestials.Celestial;
import no.jgdx.perseus.celestials.Position;
import no.jgdx.perseus.players.HumanPlayer;
import no.jgdx.perseus.ships.HqShip;
import no.jgdx.perseus.ships.Ship;
import no.jgdx.perseus.ships.harvesters.Harvester;
import no.jgdx.perseus.stations.ShipYard;
import no.jgdx.perseus.stations.SpaceStation;

public class View {

	// main panels
	private ArrayList<JPanel> panels;

	private final JFrame frame;

	private final JPanel top = new JPanel();
	private final JLabel topTitle = new JLabel();

	private final JPanel middle = new JPanel();
	private ArrayList<MainMenuButton> mainMenuButtons;

	// game panel
	private GamePanel gamePanel;

	private final JPanel bottom = new JPanel();

	// layout manager for main panels
	private final GroupLayout layout;

	private GameObject currentlySelectedGameObject = null;

	public View(String title) {

		frame = new JFrame(title);
		File f = new File("bg.jpg");

		try {
			BufferedImage background = ImageIO.read(f);
			frame.setContentPane(new ImagePanel(background));

		} catch (IOException e) {
			System.out.println(e);
		}

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

		// set border and colors of top area
		top.setBorder(new MatteBorder(5, 0, 0, 0, new Color(0, 0, 0, 0)));
		top.setPreferredSize(new Dimension(frame.getWidth(), 75));

		// set border and colors of middle area
		middle.setBorder(new MatteBorder(1, 0, 1, 0, new Color(255, 255, 255, 50)));
		middle.setPreferredSize(new Dimension(frame.getWidth(), 675));

		// set border and colors of bottom area
		bottom.add(new JLabel("Bottom area"));
		bottom.setPreferredSize(new Dimension(frame.getWidth(), 50));

		// add all panels to frame
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(top, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(middle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(bottom, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(top, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(middle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(bottom, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));

		frame.setVisible(true);

		// add main panels, validate, paint
		for (JPanel p : panels) {
			p.setBackground(new Color(0, 0, 0, 100));
			p.setVisible(true);
			p.revalidate();
			p.repaint();
		}

		// tell layout manager to rearrange main panels
		frame.pack();

	}

	// creates main title
	private JLabel createTitle() {
		Font f = new Font("Ubuntu Light", Font.PLAIN, 42);
		topTitle.setFont(f);
		topTitle.setForeground(new Color(255, 255, 255, 255));
		return topTitle;
	}

	// sets main title
	public void setTopTitle(String title) {
		topTitle.setText(title);
	}

	// creates a main menu and puts it into a main panel
	private void renderMainMenuButtons(ArrayList<MainMenuItems> items) {

		this.mainMenuButtons = new ArrayList<MainMenuButton>();

		for (MainMenuItems m : items) {
			MainMenuButton b = new MainMenuButton(m.getLabel(), m);
			b.setBackground(new Color(0, 0, 0, 0));
			mainMenuButtons.add(b);
			middle.add(b);
		}

		middle.setLayout(new GridLayout(items.size(), 0, 10, 10));

	}

	public void showMainMenu(ArrayList<MainMenuItems> items) {
		renderMainMenuButtons(items);

	}

	// puts ActionListener on main menu
	public void addMainMenuListener(ActionListener listener) {
		for (MainMenuButton b : mainMenuButtons) {
			b.addActionListener(listener);
		}
	}

	// class for main menu buttons
	// XXX: use button model
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

	// displays credits
	public void showCredits(List<String> contributors) {
		setTopTitle("Credits");
		middle.removeAll();
		middle.setLayout(new FlowLayout(0, 10, 10));

		for (String c : contributors) {
			JLabel l = new JLabel(c);
			l.setForeground(new Color(255, 255, 255, 255));
			middle.add(l);
		}

	}

	public void showNewGame(List<Ship> ships, List<Celestial> celestials, List<SpaceStation> spaceStations,
			MouseListener mouseListener, final KeyListener keyListener) {
		setTopTitle("Constellation Perseus (ingame etc.)");
		this.gamePanel = new GamePanel(ships, celestials, spaceStations);
		middle.removeAll();
		middle.setLayout(new GridLayout(1, 1));
		middle.add(gamePanel);

		gamePanel.setBackground(new Color(0, 0, 0, 100));
		gamePanel.addMouseListener(mouseListener);
		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(keyListener);
		gamePanel.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "released");

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				if (event.getID() == KeyEvent.KEY_TYPED) {
					keyListener.keyTyped(event);
					return true;
				}
				return false;
			}
		});
	}

	public GameObject getCurrentlySelectedGameObject() {
		return currentlySelectedGameObject;
	}

	public void setCurrentlySelectedGameObject(GameObject currentlySelectedGameObject) {
		this.currentlySelectedGameObject = currentlySelectedGameObject;
	}

	public void tick() {
		frame.repaint();
		if (gamePanel != null) {
			gamePanel.repaint();
		}

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

			// System.out.println("Have " + ships.size() + " ships");

			Game game = Game.getInstance();

			// SHIPS
			for (Ship s : ships) {
				if (getCurrentlySelectedGameObject() == s) {
					drawHighlight(g, (int) s.getPosition().getX(), (int) s.getPosition().getY());
				}
				if (s.getOwner() instanceof HumanPlayer)
					g.setColor(Color.GREEN);
				else
					g.setColor(Color.RED);
				Position pos = game.getPositionOfObject(s);
				int x = (int) pos.getX();
				int y = (int) pos.getY();

				if (s instanceof HqShip)
					g.drawString("⛫  HQ", x, y);

				else if (s instanceof Harvester) {
					int percentage = ((Harvester) s).getPercentage();
					g.drawString("⛴ " + s, x, y);

					// progress bar!
					g.setColor(Color.DARK_GRAY);
					g.drawRect(x, y, 100, 4);

					g.setColor(Color.CYAN);
					g.fillRect(x + 1, y + 1, percentage, 2);

				}

				else {
					if (s.isReadyToJump())
						g.drawString("✈", x, y);
					else {
						String t = String.format("%.1f", (s.getCooldownTimeLeft() / 1000f));
						g.drawString("✈ " + t, x, y);
					}
				}
			}

			// CELESTIALS
			for (Celestial cel : celestials) {
				if (getCurrentlySelectedGameObject() == cel) {
					drawHighlight(g, (int) cel.getPosition().getX(), (int) cel.getPosition().getY());
				}
				g.setColor(Color.YELLOW);
				Position pos = game.getPositionOfObject(cel);
				int x = (int) pos.getX();
				int y = (int) pos.getY();
				g.drawString("★ " + cel.getName(), x, y);
			}

			// SPACE STATIONS
			for (SpaceStation ss : spaceStations) {
				if (getCurrentlySelectedGameObject() == ss) {
					drawHighlight(g, (int) ss.getPosition().getX(), (int) ss.getPosition().getY());
				}
				if (ss.getOwner() instanceof HumanPlayer)
					g.setColor(Color.GREEN);
				else
					g.setColor(Color.RED);

				Position pos = game.getPositionOfObject(ss);
				int x = (int) pos.getX();
				int y = (int) pos.getY();

				if (ss instanceof ShipYard)
					g.drawString("⛶", x, y);
				else
					g.drawString("Station", x, y);
			}
		}
	}

	private void drawHighlight(Graphics g, int x, int y) {
		g.setColor(Color.GRAY);
		g.drawLine(x + 5, y + 5, x + 10, y + 10);
		g.drawLine(x - 5, y + 5, x - 10, y + 10);
		g.drawLine(x + 5, y - 5, x + 10, y - 10);
		g.drawLine(x - 5, y - 5, x - 10, y - 10);
	}

	private class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}
}
