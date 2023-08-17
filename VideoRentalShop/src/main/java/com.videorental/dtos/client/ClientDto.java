package com.videorental.dtos.client;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class ClientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Long phoneNumber;

    private String email;


    public ClientDto(Long id, String firstName, String lastName, Long phoneNumber, String email) {

        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.notNull(email, "email must not be null");

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public ClientDto() {
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
