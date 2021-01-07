package Exceptions;

@SuppressWarnings("serial")
public class ElementNotFoundException extends Exception {

	/**
	 * 
	 */
	public ElementNotFoundException() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ElementNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ElementNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public ElementNotFoundException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ElementNotFoundException(Throwable cause) {
		super(cause);
		
	}

	

}
