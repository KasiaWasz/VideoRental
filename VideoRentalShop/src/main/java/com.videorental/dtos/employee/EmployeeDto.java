package com.videorental.dtos.employee;

import org.springframework.util.Assert;

public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;


    public EmployeeDto(Long id, String firstName, String lastName, String phoneNumber) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(phoneNumber, "phoneNumber must not be null");

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
