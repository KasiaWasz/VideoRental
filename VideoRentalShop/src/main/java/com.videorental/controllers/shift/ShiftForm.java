package com.videorental.controllers.shift;

import org.springframework.util.Assert;

public class ShiftForm {

    static final String F_EMPLOYEE_ID = "employeeId";
    static final String F_DATE = "date";
    static final String F_HOURS = "hours";

    private Long id;
    private Long employeeId;
    private String date;
    private Long hours;


    public ShiftForm(Long id, Long employeeId, String date, Long hours) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(employeeId, "employeeId must not be null");
        Assert.notNull(date, "date must not be null");
        Assert.notNull(hours, "hours must not be null");

        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.hours = hours;
    }


    public ShiftForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }
}
