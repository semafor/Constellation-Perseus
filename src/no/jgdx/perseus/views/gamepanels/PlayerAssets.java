package no.jgdx.perseus.views.gamepanels;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.jgdx.perseus.assets.Allotrope;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.views.TickablePanel;

/**
 * playerAssetsPanel describes the current assets of the {@link Player}
 */
public class PlayerAssets extends JPanel implements GamePanels, TickablePanel {

	private static final long serialVersionUID = 1L;

	private final Player player;
	
	private final Map<Allotrope, JLabel> panels = new HashMap<Allotrope, JLabel>();

	public PlayerAssets(Player player) {
		this.player = player;

		setup();
	}

	private void setup() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		for (Allotrope a : Allotrope.values()) {
			JLabel l = new JLabel();
			add(l);
			panels.put(a, l);
		}
		
	}

	@Override
	public void render() {

		for (Map.Entry<Allotrope, JLabel> entry : panels.entrySet()) {
		    Allotrope al = entry.getKey();
		    JLabel jl = entry.getValue();

	    	jl.setText(al.getAbbreviation() + ": " + player.getTotalsForAllotrope(al) + " ");
		}

	}
	
	@Override
	public void tick() {
		render();
	}

}
