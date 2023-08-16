package com.videorental.controllers.employee;

import com.videorental.entities.employee.Role;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeForm {

    static final String F_FIRST_NAME = "firstName";
    static final String F_LAST_NAME = "lastName";
    static final String F_JOIN_DATE ="joinDate";
    static final String F_PHONE_NUMBER = "phoneNumber";
    static final String F_ROLE = "role";
    static final String F_HOUR_SALARY = "hourSalary";

    private Long id;

    private String firstName;

    private String lastName;

    private String joinDate;

    private String phoneNumber;

    private BigDecimal hourSalary;

    private Role role;


    public EmployeeForm(Long id,
                    String firstName,
                    String lastName,
                    String joinDate,
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


    public EmployeeForm() {
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getHourSalary() {
        return hourSalary;
    }

    public void setHourSalary(BigDecimal hourSalary) {
        this.hourSalary = hourSalary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
