package com.videorental.queries.employee;

import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.dtos.employee.EmployeeDto;
import com.videorental.dtos.employee.EmployeeSimpleDto;
import com.videorental.entities.employee.Employee;
import com.videorental.entities.employee.Role;
import com.videorental.queries.AbstractQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class EmployeeQueriesImpl extends AbstractQueries<Employee> implements EmployeeQueries {


    private final EmployeeSimpleDtoFactory employeeSimpleDtoFactory;
    private final EmployeeDetailDtoFactory employeeDetailDtoFactory;

    @Autowired
    EmployeeQueriesImpl(SessionFactory sessionFactory,
                               EmployeeSimpleDtoFactory employeeSimpleDtoFactory,
                               EmployeeDetailDtoFactory employeeDetailDtoFactory) {

        super(sessionFactory, Employee.class);

        Assert.notNull(employeeSimpleDtoFactory, "employeeSimpleDtoFactory must not be null");
        Assert.notNull(employeeDetailDtoFactory, "employeeDetailDtoFactory must not be null");

        this.employeeSimpleDtoFactory = employeeSimpleDtoFactory;
        this.employeeDetailDtoFactory = employeeDetailDtoFactory;
    }


    public List<EmployeeDto> getAllEmployeesDto() {

        return getAll().stream()
                .map(employee -> new EmployeeDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getPhoneNumber()
                ))
                .toList();
    }

    public EmployeeDetailDto getEmployeeDetailDtoById(Long id) {

        Assert.notNull(id, "id must not be null");

        Employee employee = getById(id);

        return employeeDetailDtoFactory.create(employee);
    }

    public List<EmployeeDetailDto> getAllEmployeesDetailsDto() {

        return getAll().stream()
                .map(employee -> new EmployeeDetailDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJoinDate(),
                        employee.getPhoneNumber(),
                        employee.getHourSalary(),
                        employee.getRole()
                ))
                .toList();
    }

    public List<EmployeeSimpleDto> getAllEmployeeSimpleDto() {

        return getAll().stream()
                .map(employee -> new EmployeeSimpleDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName()
                ))
                .toList();
    }

    public List<EmployeeSimpleDto> getManagers() {

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> root = cq.from(Employee.class);

            cq.select(root)
                    .where(cb.equal(root.get("role"), Role.MANAGER));

            return session.createQuery(cq)
                    .getResultList().stream()
                    .map(employeeSimpleDtoFactory::create)
                    .toList();
        }
    }

    public EmployeeDetailDto getEmployeeDetailDtoByJoinDate(LocalDate joinDate) {

        Assert.notNull(joinDate, "joinDate must not be null");

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> root = cq.from(Employee.class);

            cq.select(root)
                    .where(cb.greaterThanOrEqualTo(root.get("joinDate"), joinDate));

            Employee employee = session.createQuery(cq).uniqueResult();

            if (employee != null) {

                return employeeDetailDtoFactory.create(employee);
            }
            else {

                return null;
            }
        }
    }

    public EmployeeSimpleDto getEmployeeDtoById(Long id) {

        Assert.notNull(id, "id must not be null");

        Employee employee = getById(id);

        return employeeSimpleDtoFactory.create(employee);
    }

    public Optional<EmployeeSimpleDto> findEmployeeDtoById(Long id) {

        Assert.notNull(id, "id must not be null");

        Optional<Employee> employee = findById(id);

        return employee.map(employeeSimpleDtoFactory::create);
    }
}
