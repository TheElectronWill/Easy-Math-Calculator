package fr.calculator;

/**
 *
 * @author Guillaume
 */
public class MathException extends RuntimeException {

	public MathException() {
	}

	public MathException(String message) {
		super(message);
	}

	public MathException(String message, Throwable cause) {
		super(message, cause);
	}

}
