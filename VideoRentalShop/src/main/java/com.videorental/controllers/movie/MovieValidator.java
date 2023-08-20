package com.videorental.controllers.movie;

import com.videorental.controllers.employee.EmployeeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
class MovieValidator implements Validator {

    private static final String E_FIELD_REQUIRED = "field.required";
    private static final String E_PRICE_INVALID = "price.invalid";


    @Override
    public boolean supports(Class<?> clazz) {
        return MovieForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        MovieForm movieForm = (MovieForm) target;

        validateName(movieForm.getName(), errors);
        validatePrice(movieForm.getPrice(), errors);
    }

    private void validateName(String name, Errors errors) {

        if (isBlank(name)) {

            errors.rejectValue(MovieForm.F_NAME, E_FIELD_REQUIRED);
        }
    }

    private void validatePrice(BigDecimal price, Errors errors) {

        if (price == null) {

            errors.rejectValue(MovieForm.F_PRICE, E_FIELD_REQUIRED);
        }
        else if (price.signum() <= 0) {

            errors.rejectValue(MovieForm.F_PRICE, E_PRICE_INVALID);
        }
    }
}
