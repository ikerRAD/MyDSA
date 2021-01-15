package Exceptions;
/**
 * Exception for when an array is full
 * @author G612050 
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
