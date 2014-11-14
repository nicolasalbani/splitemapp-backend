/**
 * 
 */
package com.splitemapp.commons.utils;

public class HashPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1501269931516607538L;

	/**
	 * <p>Constructor for HashPasswordException.</p>
	 */
	public HashPasswordException() {
	}

	/**
	 * <p>Constructor for HashPasswordException.</p>
	 *
	 * @param message a {@link java.lang.String} object.
	 */
	public HashPasswordException(String message) {
		super(message);
	}

	/**
	 * <p>Constructor for HashPasswordException.</p>
	 *
	 * @param cause a {@link java.lang.Throwable} object.
	 */
	public HashPasswordException(Throwable cause) {
		super(cause);
	}

	/**
	 * <p>Constructor for HashPasswordException.</p>
	 *
	 * @param message a {@link java.lang.String} object.
	 * @param cause a {@link java.lang.Throwable} object.
	 */
	public HashPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
}