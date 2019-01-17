package io.jumpco.customer.api.common;

import java.io.Serializable;

import org.dozer.Mapping;

import lombok.Data;


/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @date 24 Oct 2018
 * @version 1.0
 */
@Data
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Mapping("id")
    private Long id;
    private String complexInfo;
    private String streetInfo;
    private String suburb;
    private String city;
    private String province;
    private String country;
    private String postalCode;

}
