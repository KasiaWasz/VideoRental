package com.videorental.queries.employee;

import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.dtos.employee.EmployeeDto;
import com.videorental.dtos.employee.EmployeeSimpleDto;
import com.videorental.entities.employee.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeQueries {

    Employee getById(Long id);

    List<Employee> getAll();

    Optional<Employee> findById(Long id);

    List<EmployeeDto> getAllEmployeesDto();

    List<EmployeeSimpleDto> getAllEmployeeSimpleDto();

    List<EmployeeDetailDto> getAllEmployeesDetailsDto();

    EmployeeDetailDto getEmployeeDetailDtoById(Long id);

    EmployeeDetailDto getEmployeeDetailDtoByJoinDate(LocalDate joinDate);

    List<EmployeeSimpleDto> getManagers();
}
