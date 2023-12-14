package com.videorental.services.employee;

import com.videorental.controllers.employee.EmployeeForm;
import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.dtos.employee.EmployeeDto;
import com.videorental.dtos.employee.EmployeeSimpleDto;
import com.videorental.dtos.shift.ShiftSimpleDto;
import com.videorental.entities.employee.Employee;
import com.videorental.entities.shift.Shift;
import com.videorental.queries.employee.EmployeeQueries;
import com.videorental.repositories.employee.EmployeeRepository;
import com.videorental.services.shift.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeQueries employeeQueries;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ShiftService shiftService;


    @Autowired
    private EmployeeService(EmployeeQueries employeeQueries,
        EmployeeRepository employeeRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder,
        ShiftService shiftService) {

        Assert.notNull(employeeQueries, "employeeQueries must not be null");
        Assert.notNull(employeeRepository, "employeeRepository must not be null");
        Assert.notNull(shiftService, "shiftService must not be null");

        this.employeeQueries = employeeQueries;
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.shiftService = shiftService;
    }


    public List<EmployeeDto> getAllEmployeesDto() {

        return employeeQueries.getAllEmployeesDto();
    }

    public List<EmployeeSimpleDto> getAllEmployeeSimpleDto() {

        return employeeQueries.getAllEmployeeSimpleDto();
    }

    public List<EmployeeDetailDto> getAllEmployeesDetailsDto() {

        return employeeQueries.getAllEmployeesDetailsDto();
    }

    public EmployeeDetailDto getEmployeeDetailDtoById(Long id) {

        return employeeQueries.getEmployeeDetailDtoById(id);
    }

    public Employee getById(Long id) {

        Assert.notNull(id, "id must not be null");

        return employeeQueries.getById(id);
    }

    public void saveOrUpdateEmployee(EmployeeForm employeeForm) {

        Assert.notNull(employeeForm, "employeeForm must not be null");

        Optional<Employee> existingEmployee = employeeQueries.findById(employeeForm.getId());

        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            updateEmployeeFields(employee, employeeForm);
            employeeRepository.saveOrUpdate(employee);
        } else {
            Employee newEmployee = createNewEmployee(employeeForm);
            employeeRepository.saveOrUpdate(newEmployee);
        }
    }

    private void updateEmployeeFields(Employee employee, EmployeeForm employeeForm) {

        employee.setFirstName(employeeForm.getFirstName());
        employee.setLastName(employeeForm.getLastName());
        employee.setEmail(employeeForm.getEmail());
        employee.setPassword(bCryptPasswordEncoder.encode(employeeForm.getPassword()));
        employee.setPhoneNumber(employeeForm.getPhoneNumber());
        employee.setJoinDate(LocalDate.parse(employeeForm.getJoinDate()));
        employee.setRole(employeeForm.getRole());
        employee.setHourSalary(employeeForm.getHourSalary());
    }

    private Employee createNewEmployee(EmployeeForm employeeForm) {

        return new Employee(
                employeeForm.getFirstName(),
                employeeForm.getLastName(),
                employeeForm.getEmail(),
                bCryptPasswordEncoder.encode(employeeForm.getPassword()),
                LocalDate.parse(employeeForm.getJoinDate()),
                employeeForm.getPhoneNumber(),
                employeeForm.getHourSalary(),
                employeeForm.getRole()
        );

    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        List<Long> employeeshifts = shiftService.getShiftsByEmployeeId(id)
                .stream()
                .map(ShiftSimpleDto::getId)
                .toList();

        employeeshifts.forEach(shiftService::deleteById);

        employeeRepository.deleteById(id);
    }
}
