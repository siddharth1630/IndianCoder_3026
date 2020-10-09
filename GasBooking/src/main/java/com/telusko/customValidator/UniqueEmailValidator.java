package com.telusko.customValidator;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.telusko.entity.Customer;
import com.telusko.service.CustomerService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	 	@Autowired
	    private CustomerService customerService;

	    public void initialize(UniqueEmail unique) {
	        unique.message();
	    }

	    public boolean isValid(String email, ConstraintValidatorContext context) {
	        if (customerService != null && customerService.existsByEmail(email,Customer.class)) {
	            return false;
	        }
	        return true;
	    }
	
}