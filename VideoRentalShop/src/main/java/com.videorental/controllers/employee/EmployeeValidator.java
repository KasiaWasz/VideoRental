package com.videorental.controllers.employee;

import com.videorental.entities.employee.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Character.isLowerCase;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public
class EmployeeValidator implements Validator {

    private static final String FIRSTNAME_LASTNAME_REGEX = "^[a-zA-Z-'ąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+$";
    private static final String PHONE_NUMBER_REGEX = "^\\d{9}$";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String E_FIELD_REQUIRED = "field.required";
    private static final String E_FIRSTNAME_INVALID = "firstname.invalid";
    private static final String E_LASTNAME_INVALID = "lastname.invalid";
    private static final String E_JOIN_DATE_INVALID = "date.invalid";
    private static final String E_PHONE_NUMBER_INVALID = "phoneNumber.invalid";
    private static final String E_HOUR_SALARY_INVALID = "hourSalary.invalid";


    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        EmployeeForm employeeForm = (EmployeeForm) target;

        validateFirstName(employeeForm.getFirstName(), errors);
        validateLastName(employeeForm.getLastName(), errors);
        validateJoinDate(employeeForm.getJoinDate(), errors);
        validatePhoneNumber(employeeForm.getPhoneNumber(), errors);
        validateSalary(employeeForm.getHourSalary(), errors);
        validateRole(employeeForm.getRole(), errors);
    }

    private void validateFirstName(String firstname, Errors errors) {

        if (isBlank(firstname)) {

            errors.rejectValue(EmployeeForm.F_FIRST_NAME, E_FIELD_REQUIRED);

        }
        else if (isLowerCase(firstname.charAt(0))) {

            errors.rejectValue(EmployeeForm.F_FIRST_NAME, E_FIRSTNAME_INVALID);
        }
        else if (!firstname.matches(FIRSTNAME_LASTNAME_REGEX)) {

            errors.rejectValue(EmployeeForm.F_FIRST_NAME, E_FIRSTNAME_INVALID);
        }
    }

    private void validateLastName(String lastname, Errors errors) {

        if (isBlank(lastname)) {

            errors.rejectValue(EmployeeForm.F_LAST_NAME, E_FIELD_REQUIRED);

        }
        else if (isLowerCase(lastname.charAt(0))) {

            errors.rejectValue(EmployeeForm.F_LAST_NAME, E_LASTNAME_INVALID);
        }
        else if (!lastname.matches(FIRSTNAME_LASTNAME_REGEX)) {

            errors.rejectValue(EmployeeForm.F_LAST_NAME, E_LASTNAME_INVALID);
        }
    }

    private void validateJoinDate(String joinDate, Errors errors) {

        if (isBlank(joinDate)) {

            errors.rejectValue(EmployeeForm.F_JOIN_DATE, E_FIELD_REQUIRED);

            return;

        }
        try {

            LocalDate.parse(joinDate, DATE_TIME_FORMATTER);

        } catch (Exception e) {

            errors.rejectValue(EmployeeForm.F_JOIN_DATE, E_JOIN_DATE_INVALID);
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors) {

        if (isBlank(phoneNumber)) {

            errors.rejectValue(EmployeeForm.F_PHONE_NUMBER, E_FIELD_REQUIRED);
        }

        else if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {

            errors.rejectValue(EmployeeForm.F_PHONE_NUMBER, E_PHONE_NUMBER_INVALID);
        }
    }

    private void validateSalary(BigDecimal salary, Errors errors) {

        if (salary == null) {

            errors.rejectValue(EmployeeForm.F_HOUR_SALARY, E_FIELD_REQUIRED);
        }
        else if (salary.signum() <= 0) {

            errors.rejectValue(EmployeeForm.F_HOUR_SALARY, E_HOUR_SALARY_INVALID);
        }
    }

    private void validateRole(Role role, Errors errors) {

        if (role == null) {

            errors.rejectValue(EmployeeForm.F_ROLE, E_FIELD_REQUIRED);
        }
    }
}