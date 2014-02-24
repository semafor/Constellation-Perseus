package no.jgdx.perseus;

import no.jgdx.perseus.players.Player;

public class Messages {

	private final Player to;

	private final Player from;

	private final String content;

	private final MessageTypes type;

	public Messages(Player from, Player to, String content, MessageTypes type) {
		this.from = from;
		this.to = to;
		this.content = content;
		this.type = type;
	}

	public Messages(Player to, String content, MessageTypes type) {
		this(to, to, content, type);
	}

	public MessageTypes getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public Player getTo() {
		return to;
	}

	public Player getFrom() {
		return from;
	}

}
