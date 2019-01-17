/**
 * 
 */
package io.jumpco.customer.api.client.intf;

import org.springframework.jms.JmsException;

import io.jumpco.customer.api.common.CustomerDTO;
import lombok.NonNull;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @date 24 Oct 2018
 * @version 1.0
 */
public interface CustomerService {
	/**
	 * This methood is used to create a Customer
	 * 
	 * @param customerDTO
	 * @return
	 * @throws JmsException
	 */
    public CustomerDTO manageCustomer(@NonNull CustomerDTO customerDTO)  throws JmsException;

}
