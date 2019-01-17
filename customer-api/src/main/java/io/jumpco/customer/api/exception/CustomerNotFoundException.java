/**
 * 
 */
package io.jumpco.customer.api.exception;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @date 06 Nov 2018
 * @version 1.0
 */
public class CustomerNotFoundException extends CustomerProcessingException {
		public CustomerNotFoundException(String message) { super(message);}
		public CustomerNotFoundException(String message, Throwable cause) { super(message, cause);}
		public CustomerNotFoundException(Throwable cause) { super(cause);}
}
