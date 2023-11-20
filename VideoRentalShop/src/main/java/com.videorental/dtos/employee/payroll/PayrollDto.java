package com.videorental.dtos.employee.payroll;

import org.springframework.util.Assert;

import java.math.BigDecimal;

public class PayrollDto {

    private String employeeFirstName;

    private String employeeLastName;

    private BigDecimal payroll;


    public PayrollDto(String employeeFirstName, String employeeLastName, BigDecimal payroll) {

        Assert.notNull(employeeFirstName, "employeeFirstName should not be null");
        Assert.notNull(employeeLastName, "employeeLastName should not be null");
        Assert.notNull(payroll, "payroll must not be null");

        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.payroll = payroll;
    }


    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public BigDecimal getPayroll() {
        return payroll;
    }
}
