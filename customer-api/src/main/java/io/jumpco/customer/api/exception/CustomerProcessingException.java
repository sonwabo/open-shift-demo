/**
 * 
 */
package io.jumpco.customer.api.exception;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @date 07 Nov 2018
 * @version 1.0
 */
public class CustomerProcessingException extends RuntimeException {
	public CustomerProcessingException(String message) { super(message);}
	public CustomerProcessingException(String message, Throwable cause) { super(message, cause);}
	public CustomerProcessingException(Throwable cause) { super(cause);}
}
