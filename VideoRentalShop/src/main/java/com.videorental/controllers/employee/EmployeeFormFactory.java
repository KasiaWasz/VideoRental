package com.videorental.controllers.employee;

import com.videorental.entities.employee.Employee;
import com.videorental.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.format.DateTimeFormatter;

@Component
class EmployeeFormFactory {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final EmployeeService employeeService;

    @Autowired
    EmployeeFormFactory(EmployeeService employeeService) {

        Assert.notNull(employeeService, "employeeService must not be null");

        this.employeeService = employeeService;
    }

    EmployeeForm create(Long id) {

        Assert.notNull(id, "id must not be null");

        Employee employee = employeeService.getById(id);

        return new EmployeeForm(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJoinDate().format(DATE_FORMAT),
                employee.getPhoneNumber(),
                employee.getHourSalary(),
                employee.getRole()
        );
    }
}
