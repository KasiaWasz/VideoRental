package com.videorental.controllers.client;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class ClientForm {

    static final String F_FIRST_NAME = "firstName";
    static final String F_LAST_NAME = "lastName";
    static final String F_REGISTRATION_DATE ="registrationDate";
    static final String F_PHONE_NUMBER = "phoneNumber";
    static final String F_EMAIL = "email";

    private Long id;

    private String firstName;

    private String lastName;

    private String registrationDate;

    private String phoneNumber;

    private String email;


    public ClientForm(Long id,
        String firstName,
        String lastName,
        String registrationDate,
        String phoneNumber,
        String email) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(registrationDate, "registrationDate must not be null");
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.notNull(email, "email must not be null");

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public ClientForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
