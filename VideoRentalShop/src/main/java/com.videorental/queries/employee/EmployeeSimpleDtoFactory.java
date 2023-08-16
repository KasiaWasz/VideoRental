package com.videorental.queries.employee;

import com.videorental.dtos.employee.EmployeeSimpleDto;
import com.videorental.entities.employee.Employee;
import org.springframework.stereotype.Component;

@Component
class EmployeeSimpleDtoFactory {

    EmployeeSimpleDto create(Employee employee) {

        if (employee == null) {
            return null;
        }
        return new EmployeeSimpleDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName()
        );
    }
}
