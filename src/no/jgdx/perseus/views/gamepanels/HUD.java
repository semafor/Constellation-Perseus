package no.jgdx.perseus.views.gamepanels;

import javax.swing.JPanel;

public class HUD extends JPanel implements GamePanels {

	private static final long serialVersionUID = 1L;

	private final SelectionVisual selectionVisual = new SelectionVisual();

	private final SelectionActions selectionActions = new SelectionActions();

	private final SelectionStatus selectionStatus = new SelectionStatus();

	private final MiniMap miniMap = new MiniMap();

	public HUD() {

		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}
