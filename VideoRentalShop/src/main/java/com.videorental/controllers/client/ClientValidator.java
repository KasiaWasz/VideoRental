package com.videorental.controllers.client;

import com.videorental.controllers.employee.EmployeeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Character.isLowerCase;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
class ClientValidator implements Validator {

    private static final String FIRSTNAME_LASTNAME_REGEX = "^[a-zA-Z-'ąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+$";
    private static final String PHONE_NUMBER_REGEX = "^\\d{9}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String E_FIELD_REQUIRED = "field.required";
    private static final String E_FIRSTNAME_INVALID = "firstname.invalid";
    private static final String E_LASTNAME_INVALID = "lastname.invalid";
    private static final String E_JOIN_DATE_INVALID = "joinDate.invalid";
    private static final String E_PHONE_NUMBER_INVALID = "phoneNumber.invalid";
    private static final String E_EMAIL_INVALID = "email.invalid";

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    private void validateFirstName(String firstname, Errors errors) {

        if (isBlank(firstname)) {

            errors.rejectValue(ClientForm.F_FIRST_NAME, E_FIELD_REQUIRED);

        }
        else if (isLowerCase(firstname.charAt(0))) {

            errors.rejectValue(ClientForm.F_FIRST_NAME, E_FIRSTNAME_INVALID);
        }
        else if (!firstname.matches(FIRSTNAME_LASTNAME_REGEX)) {

            errors.rejectValue(ClientForm.F_FIRST_NAME, E_FIRSTNAME_INVALID);
        }
    }

    private void validateLastName(String lastname, Errors errors) {

        if (isBlank(lastname)) {

            errors.rejectValue(ClientForm.F_LAST_NAME, E_FIELD_REQUIRED);

        }
        else if (isLowerCase(lastname.charAt(0))) {

            errors.rejectValue(ClientForm.F_LAST_NAME, E_LASTNAME_INVALID);
        }
        else if (!lastname.matches(FIRSTNAME_LASTNAME_REGEX)) {

            errors.rejectValue(ClientForm.F_LAST_NAME, E_LASTNAME_INVALID);
        }
    }

    private void validateRegistrationDate(String registrationDate, Errors errors) {

        if (isBlank(registrationDate)) {

            errors.rejectValue(ClientForm.F_REGISTRATION_DATE, E_FIELD_REQUIRED);

            return;

        }
        try {

            LocalDate.parse(registrationDate, DATE_TIME_FORMATTER);

        } catch (Exception e) {

            errors.rejectValue(ClientForm.F_REGISTRATION_DATE, E_JOIN_DATE_INVALID);
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors) {

        if (isBlank(phoneNumber)) {

            errors.rejectValue(ClientForm.F_PHONE_NUMBER, E_FIELD_REQUIRED);
        }

        else if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {

            errors.rejectValue(ClientForm.F_PHONE_NUMBER, E_PHONE_NUMBER_INVALID);
        }
    }

    private void validateEmail(String email, Errors errors) {

        if (isBlank(email)) {

            errors.rejectValue(ClientForm.F_EMAIL, E_FIELD_REQUIRED);
        }
        else if (!email.matches(EMAIL_REGEX)) {

            errors.rejectValue(ClientForm.F_EMAIL, E_EMAIL_INVALID);
        }
    }

}
