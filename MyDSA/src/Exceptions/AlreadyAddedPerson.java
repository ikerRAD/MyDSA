package Exceptions;

@SuppressWarnings("serial")
public class AlreadyAddedPerson extends Exception{

	/**
	 * 
	 */
	public AlreadyAddedPerson() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AlreadyAddedPerson(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AlreadyAddedPerson(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public AlreadyAddedPerson(String message) {
		super(message);
	
	}

	/**
	 * @param cause
	 */
	public AlreadyAddedPerson(Throwable cause) {
		super(cause);
		
	}
	
}
