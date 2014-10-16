package code;

/**
 * Exception thrown when unit or yagura type does not exist.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class InvalidTypeException extends RuntimeException {

	/**
	 * General exception
	 */
	public InvalidTypeException() {}
	
	/**
	 * Exception with message.
	 * @param message message to show in error
	 */
	public InvalidTypeException(String message) {
		super(message);
	}
	
}
