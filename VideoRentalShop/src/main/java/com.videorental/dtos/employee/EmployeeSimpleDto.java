package com.videorental.dtos.employee;

import org.springframework.util.Assert;

public class EmployeeSimpleDto {

    private Long id;
    private String firstName;
    private String lastName;


    public EmployeeSimpleDto(Long id, String firstName, String lastName) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(firstName, "firstname must not be null");
        Assert.notNull(lastName, "lastName must not be null");

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
