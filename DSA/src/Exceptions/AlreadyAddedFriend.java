package Exceptions;

@SuppressWarnings("serial")
public class AlreadyAddedFriend extends Exception{

	/**
	 * 
	 */
	public AlreadyAddedFriend() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AlreadyAddedFriend(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AlreadyAddedFriend(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public AlreadyAddedFriend(String message) {
		super(message);
	
	}

	/**
	 * @param cause
	 */
	public AlreadyAddedFriend(Throwable cause) {
		super(cause);
		
	}
	
}
