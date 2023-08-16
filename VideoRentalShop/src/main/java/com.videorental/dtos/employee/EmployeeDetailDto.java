package com.videorental.dtos.employee;

import com.videorental.entities.employee.Role;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDetailDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate joinDate;

    private String phoneNumber;

    private BigDecimal hourSalary;

    private Role role;

    public EmployeeDetailDto(Long id,
                             String firstName,
                             String lastName,
                             LocalDate joinDate,
                             String phoneNumber,
                             BigDecimal hourSalary,
                             Role role) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(joinDate, "joinDate must not be null");
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.notNull(hourSalary, "hourSalary must not be null");
        Assert.notNull(role, "role must not be null");

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = joinDate;
        this.phoneNumber = phoneNumber;
        this.hourSalary = hourSalary;
        this.role = role;
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

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BigDecimal getHourSalary() {
        return hourSalary;
    }

    public Role getRole() {
        return role;
    }
}
