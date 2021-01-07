package Exceptions;
/**
 * Exception for when an array is full
 * @author Iker Pintado
 *
 */
@SuppressWarnings("serial")
public class FullListException extends RuntimeException{

	public FullListException() {
		super();
	
	}

	public FullListException(String message) {
		super(message);
		
	}

}
