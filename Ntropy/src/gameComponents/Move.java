package gameComponents;

public class Move {
	
	private final String playerId;
	private final int token, choice;
	
	public Move(String playerId, int token, int choice) {
		this.playerId = playerId;
		this.token = token;
		this.choice = choice;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	
	public int getToken() {
		return token;
	}
	
	public int getChoice() {
		return choice;
	}

}
