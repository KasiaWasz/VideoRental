package com.videorental.dtos.shift;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class ShiftDto {

    private Long id;
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private LocalDate date;
    private Long hours;


    public ShiftDto(Long id, Long employeeId, String employeeFirstName, String employeeLastName, LocalDate date, Long hours) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(employeeId, "employeeId must not be null");
        Assert.notNull(employeeFirstName, "employeeFirstName must not be null");
        Assert.notNull(employeeLastName, "employeeLastName must not be null");
        Assert.notNull(date, "date must not be null");
        Assert.notNull(hours, "hours must not be null");


        this.id = id;
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.date = date;
        this.hours = hours;
    }


    public ShiftDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getHours() {
        return hours;
    }
}
