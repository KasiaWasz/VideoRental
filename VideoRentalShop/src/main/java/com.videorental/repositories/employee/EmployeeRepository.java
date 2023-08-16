package com.videorental.repositories.employee;

import com.videorental.entities.employee.Employee;

public interface EmployeeRepository {

    void saveOrUpdate(Employee employee);

    void deleteById(Long id);
}
