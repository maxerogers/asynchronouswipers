package gameComponents.src;

/**
 * Objects of this class represent the concept of a square or tile 
 * for manipulation by a BoardModel in the Ntropy app. 
 * @author Nolan
 * 
 */
public class Square {
	/**
	 *  Used to represent the state of a square. ZERO represents marked
	 *  by 0, ONE represents marked by 1, and OFF represents unmarked.
	 */
	public enum State {
		ZERO, ONE, OFF;
	}
	private State state;
	
	/**
	 * A convenience constructor for objects of the class Square. Squares
	 * are started without being marked by default.
	 */
	public Square() {
		this(State.ZERO);
	}
	
	/**
	 * A constructor for objects of the class Square. 
	 * @param state The state in which the Square should be initialised.
	 */
	public Square(State state) {
		this.state = state; 
	}
	
	/**
	 * Changes the state of this Square
	 * @param the state to which the square should be changed.
	 */
	public void changeState(State state) {
		this.state = state;
	}
	
	/**
	 * Returns the state of this Square.
	 * @return the state of this Square.
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Determines if this Square is marked.
	 * @return A boolean that is true if the square is marked and false otherwise
	 */
	public boolean isMarked() {
		return (state != State.OFF);
	}	
}
