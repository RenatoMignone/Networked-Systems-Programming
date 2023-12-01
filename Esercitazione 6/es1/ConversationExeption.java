package es1;

public class ConversationExeption extends RuntimeException {
	public ConversationExeption() {}

	public ConversationExeption(String msg) {
		super(msg);
	}
}
