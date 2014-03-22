package no.jgdx.perseus.views.gamepanels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.jgdx.perseus.Messages;
import no.jgdx.perseus.players.Player;
import no.jgdx.perseus.views.TickablePanel;

/**
 * playerMessagesPanel contains {@link Messages} read and unread to the
 * {@link Player}
 */
public class PlayerMessages extends JPanel implements GamePanels, TickablePanel {

	private static final long serialVersionUID = 1L;

	private final Player player;

	private Messages currentMessage;
	
	private final JLabel messageLabel = new JLabel();

	public PlayerMessages(Player player) {
		
		this.player = player;
		setup();
		
	}

	private void setup() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(messageLabel);

	}

	public void setMessage(Messages message) {
	
		this.currentMessage = message;

	}
	
	public Messages getCurrentMessage() {
		
		if(currentMessage == null) {
			
			for(Messages m : player.getMessages()) {
				if(m.getArchived() == false) {
					setMessage(m);
					break;
				}
			}
			
		}
		
		return currentMessage;
		
	}

	@Override
	public void render() {
		
		String s = "";
		Messages m = getCurrentMessage();
		
		if(m == null) {
			s = "You have no messages at this time";
		} else {
			s = m.getContent();
		}
		
		messageLabel.setText(s);

	}

	@Override
	public void tick() {

		this.render();
		
	}

}
