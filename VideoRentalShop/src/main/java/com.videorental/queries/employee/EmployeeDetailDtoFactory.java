package com.videorental.queries.employee;

import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.entities.employee.Employee;
import org.springframework.stereotype.Component;

@Component
class EmployeeDetailDtoFactory {

    EmployeeDetailDto create(Employee employee) {

        if (employee == null) {

            return null;
        }
        return new EmployeeDetailDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJoinDate(),
                employee.getPhoneNumber(),
                employee.getHourSalary(),
                employee.getRole()
        );
    }
}
