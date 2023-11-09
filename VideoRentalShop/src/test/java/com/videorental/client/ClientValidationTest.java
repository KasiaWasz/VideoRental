package com.videorental.client;

import com.videorental.controllers.client.ClientForm;
import com.videorental.controllers.client.ClientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClientValidationTest {

    private ClientValidator clientValidator;
    private ClientForm clientForm;
    private Errors errors;

    @BeforeEach
    public void setUp() {

        clientValidator = new ClientValidator();
        clientForm = new ClientForm();
        errors = new BeanPropertyBindingResult(clientForm, "clientForm");
    }

    @Test
    void shouldFirstNameBeInvalid() throws Exception {

        //given
        clientForm.setFirstName("test@");
        clientForm.setLastName("Test");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("firstName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchEmptyFirstName() throws Exception {

        //given
        clientForm.setFirstName("");
        clientForm.setLastName("Test");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("firstName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldLastNameBeInvalid() throws Exception {

        //given
        clientForm.setFirstName("Test");
        clientForm.setLastName("11Test");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("lastName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchEmptyLastName() throws Exception {

        //given
        clientForm.setFirstName("Test");
        clientForm.setLastName("");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("lastName")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldRegistrationDateBeInvalid() throws Exception {

        //given
        clientForm.setFirstName("Test");
        clientForm.setLastName("Test");
        clientForm.setRegistrationDate("20-06-2021");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("registrationDate")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldPhoneNumberBeInvalid() throws Exception {

        //given
        clientForm.setFirstName("Test");
        clientForm.setLastName("Test");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("11111111123");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("phoneNumber")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchEmptyEmail() throws Exception {

        //given
        clientForm.setFirstName("Test");
        clientForm.setLastName("Test");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasFieldErrors("email")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldValidateClient() throws Exception {

        //given
        clientForm.setFirstName("Test");
        clientForm.setLastName("Test");
        clientForm.setRegistrationDate("2023-06-01");
        clientForm.setPhoneNumber("111111111");
        clientForm.setEmail("test@gmail.com");

        //when
        clientValidator.validate(clientForm, errors);

        //then
        assertThat(errors.hasErrors()).isFalse();
        assertThat(errors.getFieldErrorCount()).isZero();
    }
}
