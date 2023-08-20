package com.videorental.controllers.employee;

import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.dtos.employee.EmployeeDto;
import com.videorental.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/employee-list")
@Controller
class EmployeeListController {

    private static final String M_EMPLOYEE_LIST = "employees";
    private static final String M_EMPLOYEE_DETAILS_LIST = "employeesDetails";
    private static final String V_EMPLOYEES_LIST = "employee-list-view";
    private static final String V_EMPLOYEES_LIST_DETAIL = "employee-list-detail-view";
    private static final String P_EMPLOYEE_ID = "id";
    private final EmployeeService employeeService;


    @Autowired
    private EmployeeListController(EmployeeService employeeService) {

        Assert.notNull(employeeService, "employeeService must not be null");

        this.employeeService = employeeService;
    }


    @ModelAttribute(M_EMPLOYEE_LIST)
    private List<EmployeeDto> getEmployees() {

        return employeeService.getAllEmployeesDto();
    }

    @ModelAttribute(M_EMPLOYEE_DETAILS_LIST)
    private List<EmployeeDetailDto> getAllEmployeesDetailsDto() {

        return employeeService.getAllEmployeesDetailsDto();
    }

    @GetMapping
    private String showEmployeeList() {

        return V_EMPLOYEES_LIST;
    }

    @GetMapping("/details")
    private String showEmployeeDetail(@RequestParam(value = P_EMPLOYEE_ID) Long id, Model model) {

        model.addAttribute("showDetails", true);
        model.addAttribute("employeeDetail", employeeService.getEmployeeDetailDtoById(id));

        return V_EMPLOYEES_LIST;
    }

    @GetMapping("/employees-details")
    private String showDetailedEmployeeList() {

        return V_EMPLOYEES_LIST_DETAIL;
    }
}
