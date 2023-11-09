package com.videorental.employee;

import com.videorental.controllers.employee.EmployeeForm;
import com.videorental.controllers.employee.EmployeeValidator;
import com.videorental.entities.employee.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmployeeValidationTest {

    private EmployeeValidator employeeValidator;
    private EmployeeForm employeeForm;
    private Errors errors;

    @BeforeEach
    public void setup() {

        employeeValidator = new EmployeeValidator();
        employeeForm = new EmployeeForm();
        errors = new BeanPropertyBindingResult(employeeForm, "EmployeeForm");
    }

    @Test
    void shouldFirstNameBeInvalid() throws Exception {

        //given
        employeeForm.setFirstName("test@");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(22.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("firstName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchEmptyFirstName() throws Exception {

        //given
        employeeForm.setFirstName("");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(22.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("firstName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldLastNameBeInvalid() throws Exception {

        employeeForm.setFirstName("Test");
        employeeForm.setLastName("!#Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(22.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("lastName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchEmptyLastName() throws Exception {

        //given
        employeeForm.setFirstName("Test");
        employeeForm.setLastName("");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(22.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("lastName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldJoinDateBeInvalid() throws Exception {

        //given
        employeeForm.setFirstName("Test");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("06-01-2023");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(22.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("joinDate")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldPhoneNumberBeInvalid() throws Exception {

        //given
        employeeForm.setFirstName("Test");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("1111111191");
        employeeForm.setHourSalary(BigDecimal.valueOf(22.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("phoneNumber")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldSalaryBeInvalid() throws Exception {

        //given
        employeeForm.setFirstName("Test");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(0.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("hourSalary")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchEmptyRole() throws Exception {

        //given
        employeeForm.setFirstName("Test");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(20.00));

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.hasFieldErrors("role")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldValidateEmployee() throws Exception {

        //given
        employeeForm.setFirstName("Test");
        employeeForm.setLastName("Test");
        employeeForm.setJoinDate("2023-06-01");
        employeeForm.setPhoneNumber("111111111");
        employeeForm.setHourSalary(BigDecimal.valueOf(20.00));
        employeeForm.setRole(Role.KASJER);

        //when
        employeeValidator.validate(employeeForm, errors);

        //then
        assertThat(errors.getFieldErrorCount()).isZero();
    }
}
