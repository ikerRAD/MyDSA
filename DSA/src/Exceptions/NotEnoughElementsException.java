package Exceptions;

@SuppressWarnings("serial")
public class NotEnoughElementsException extends RuntimeException{

	/**
	 * 
	 */
	public NotEnoughElementsException() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NotEnoughElementsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotEnoughElementsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public NotEnoughElementsException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public NotEnoughElementsException(Throwable cause) {
		super(cause);
	
	}

}
