package com.videorental.repositories.employee;

import com.videorental.entities.employee.Employee;
import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class EmployeeRepositoryImpl extends AbstractRepository<Employee> implements EmployeeRepository {


    @Autowired
    EmployeeRepositoryImpl(SessionFactory sessionFactory) {

        super(sessionFactory, Employee.class);
    }

}
