package com.videorental.services.employee.payroll;

import com.videorental.dtos.employee.payroll.PayrollDto;
import com.videorental.dtos.shift.ShiftSimpleDto;
import com.videorental.entities.employee.Employee;
import com.videorental.queries.employee.EmployeeQueries;
import com.videorental.queries.shift.ShiftQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PayrollService {

    private final EmployeeQueries employeeQueries;
    private final ShiftQueries shiftQueries;


    @Autowired
    private PayrollService(EmployeeQueries employeeQueries, ShiftQueries shiftQueries) {

        Assert.notNull(employeeQueries, "employeeQueries must not be null");
        Assert.notNull(shiftQueries, "shiftQueries must not be null");

        this.employeeQueries = employeeQueries;
        this.shiftQueries = shiftQueries;
    }

    public List<PayrollDto> getAllPayrolls(LocalDate startDate, LocalDate endDate) {

        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");

        return shiftQueries.getShiftsByDate(startDate, endDate).stream()
                .collect(groupingBy(ShiftSimpleDto::getEmployeeId))
                .entrySet().stream()
                .map(this::createPayrollDto)
                .toList();
    }

    private PayrollDto createPayrollDto(Map.Entry<Long, List<ShiftSimpleDto>> entry) {

        Long employeeId = entry.getKey();
        Employee employee = employeeQueries.getById(employeeId);

        long calculateWorkingHours = entry.getValue().stream()
                .mapToLong(ShiftSimpleDto::getHours)
                .sum();

        BigDecimal hourSalary = employee.getHourSalary();
        BigDecimal salary = hourSalary.multiply(BigDecimal.valueOf(calculateWorkingHours));

        return new PayrollDto(
                employee.getFirstName(),
                employee.getLastName(),
                salary
        );
    }
}
