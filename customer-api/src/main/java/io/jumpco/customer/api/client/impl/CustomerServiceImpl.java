/**
 * 
 */
package io.jumpco.customer.api.client.impl;

import java.io.ByteArrayOutputStream;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jumpco.customer.api.client.intf.CustomerService;
import io.jumpco.customer.api.common.CustomerDTO;
import io.jumpco.customer.api.exception.CustomerProcessingException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @date 24 Oct 2018
 * @version 1.0
 */
@Slf4j
@RestController
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private JmsMessagingTemplate jmsTempplate;
	private ObjectMapper mapper = new ObjectMapper();
	
	private final static String MANAGE_CUSTOMER = "MANAGE_CUSTOMER";
	
    @Autowired
    ApplicationContext appContext;
	public CustomerServiceImpl() {}
	
	public CustomerServiceImpl(JmsMessagingTemplate jmsTempplate, ObjectMapper mapper) {
		this.jmsTempplate = jmsTempplate;
		this.mapper = mapper;
	} 
	
	
	
	@PostMapping("/createCustomer")
	@ResponseBody
	public void createCustomer(@RequestBody CustomerDTO customer) {
		
		manageCustomer(customer);
		
	}
	
    public CustomerDTO manageCustomer(@NonNull CustomerDTO customerDTO) throws  CustomerProcessingException {
    	try { 
    		CustomerDTO message = jmsTempplate.convertSendAndReceive(MANAGE_CUSTOMER, customerDTO, customerDTO.getClass(), (session) ->{
        		return session; 
        	});
        	return message; //convertResponse(message, CustomerDTO.class);
    	}catch(MessagingException e) {
    		throw new CustomerProcessingException(String.format("Exception managing customer : [%s]", e.getMessage()),e);
    	}	
    }
    
    
    public <T> T convertResponse(Message message, Class<T> cls) throws JMSException{
    	
    	StringBuilder response = new StringBuilder();
    	
    	if(message instanceof TextMessage) {
    		TextMessage textMessage = (TextMessage)message;
    		if(StringUtils.isEmpty(textMessage.getText())) {
    			return null;
    		}
    		response.append(textMessage.getText());
    	}else if(message instanceof BytesMessage) {
    		BytesMessage bytesMessage = (BytesMessage)message;
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		byte[] buffer = new byte[8192];
    		while(true) {
    			int len = bytesMessage.readBytes(buffer);
    			if(len > 0) {
    				bos.write( buffer, 0 , len);
    			}else if(len < 0) {
    				break;
    			}
    		}
    		if(bos.size() == 0) {
    			return null;
    		}
    		response.append(new String(bos.toByteArray()));
    	}
    	final String responseText = response.toString();
    	if(message.propertyExists("EXCEPTION") && message.getBooleanProperty("EXCEPTION")){
    		throw new CustomerProcessingException(responseText);
    	}
    	return  mapper.convertValue(responseText, cls);
    }
    
    
    
    
}