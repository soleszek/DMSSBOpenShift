package com.oleszeksylwester.dmssb.DMSSB.utils;

import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("userValidator")
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User u = (User) o;
        /*String role = (String) o;*/

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.firstName", "Field Name is required");
        if(u.getFirstName().length() < 4){
            errors.rejectValue("firstName", "error.firstName", "Field Name must have at least 3 signs");
        } else if(u.getFirstName().length() > 35){
            errors.rejectValue("firstName", "error.firstName", "Field Name can't have more then 35 signs");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.lastName", "Field Last name is required and must have at least 3 signs");
        if(u.getLastName().length() < 4){
            errors.rejectValue("firstName", "error.firstName", "Field Last name must have at least 3 signs");
        } else if(u.getLastName().length() > 35){
            errors.rejectValue("firstName", "error.firstName", "Field Last name can't have more then 35 signs");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "Field Login is required and must have at least 3 signs");
        if(u.getUsername().length() < 4){
            errors.rejectValue("firstName", "error.firstName", "Field Login must have at least 3 signs");
        } else if(u.getUsername().length() > 20){
            errors.rejectValue("firstName", "error.firstName", "Field Login can't have more then 20 signs");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Field Password is required and must have at least 6 signs");
        if(u.getPassword().length() < 7){
            errors.rejectValue("firstName", "error.firstName", "Field Password field must have at least 6 signs");
        } else if(u.getPassword().length() > 20){
            errors.rejectValue("firstName", "error.firstName", "Field Password can't have more then 20 signs");
        }

        /*if(role.equals("select")){
            errors.rejectValue("role", "error.role", "Choose role");
        }*/
    }
}
