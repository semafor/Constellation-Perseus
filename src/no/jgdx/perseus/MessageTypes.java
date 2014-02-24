package no.jgdx.perseus;

public enum MessageTypes {
	
	DIPLOMACY("Diplomatic cable"),
	
	WARNING("Proposed sanction"),
	
	THREAT("Military intent"),
	
	SUBMISSION("Formal apology"),
	
	NEWS("News message"),
	
	INTERNAL("Message from advisor");
	
	private String type;
	
	private MessageTypes(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
	
}
