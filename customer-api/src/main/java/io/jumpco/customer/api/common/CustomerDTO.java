/**
 * 
 */
package io.jumpco.customer.api.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @date 24 Oct 2018
 * @version 1.0
 */

@Data
public class CustomerDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String registeredName;

    private ClientTypeDTO clientType;

    private Set<AddressDTO> premises = new HashSet<>();
	
}
