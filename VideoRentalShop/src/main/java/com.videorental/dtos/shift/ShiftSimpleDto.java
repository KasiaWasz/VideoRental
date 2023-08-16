package com.videorental.dtos.shift;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class ShiftSimpleDto {

    private Long id;
    private Long employeeId;
    private LocalDate date;
    private Long hours;


    public ShiftSimpleDto(Long id, Long employeeId, LocalDate date, Long hours) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(employeeId, "employeeId must not be null");
        Assert.notNull(date, "date must not be null");
        Assert.notNull(hours, "hours must not be null");

        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.hours = hours;
    }

    public ShiftSimpleDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getHours() {
        return hours;
    }
}
