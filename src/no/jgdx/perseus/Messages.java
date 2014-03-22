package no.jgdx.perseus;

import no.jgdx.perseus.players.Player;

public class Messages {

	private final Player to;

	private final Player from;

	private final String content;

	private final MessageTypes type;

	private Boolean archived = false;

	private Boolean read = false;

	private final long createdAt;

	public Messages(Player from, Player to, String content, MessageTypes type) {
		this.from = from;
		this.to = to;
		this.content = content;
		this.type = type;
		this.createdAt = Game.now();
	}

	public Messages(Player to, String content, MessageTypes type) {
		this(to, to, content, type);
	}

	public Messages(String content, MessageTypes type) {
		this(null, null, content, type);
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

	public void archive() {
		this.archived = true;
	}
	
	public Boolean getArchived() {
		return archived;
	}

	public void read() {
		this.read = true;
	}
	
	public Boolean getRead() {
		return read;
	}

	public long getCreatedAt() {
		return this.createdAt;
	}

}
