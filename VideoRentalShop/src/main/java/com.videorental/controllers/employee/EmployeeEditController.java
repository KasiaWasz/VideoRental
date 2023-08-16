package com.videorental.controllers.employee;

import com.videorental.entities.employee.Role;
import com.videorental.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/employee-edit")
@Controller
class EmployeeEditController {

    private static final String M_EDIT_FORM = "employeeForm";
    private static final String M_ROLE_LIST = "roles";
    private static final String P_EMPLOYEE_ID = "id";
    private static final String V_EMPLOYEE_EDIT = "employee-edit-view";
    private static final String EMPLOYEE_LIST_URL ="/employee-list";

    private final EmployeeService employeeService;
    private final EmployeeFormFactory employeeFormFactory;
    private final EmployeeValidator employeeValidator;

    @Autowired
    private EmployeeEditController(EmployeeService employeeService,
        EmployeeFormFactory employeeFormFactory,
        EmployeeValidator employeeValidator) {

        Assert.notNull(employeeService, "employeeService must not be null");
        Assert.notNull(employeeFormFactory, "employeeFormFactory must not be null");
        Assert.notNull(employeeValidator, "employeeValidator must not be null");

        this.employeeService = employeeService;
        this.employeeFormFactory = employeeFormFactory;
        this.employeeValidator = employeeValidator;
    }

    @InitBinder(M_EDIT_FORM)
    private void initBinder(WebDataBinder binder) {

        binder.addValidators(employeeValidator);
    }

    @ModelAttribute(M_ROLE_LIST)
    private Role[] getRoles() {

        return Role.values();
    }

    @ModelAttribute(M_EDIT_FORM)
    private EmployeeForm getEmployeeForm(@RequestParam(value = P_EMPLOYEE_ID, required = false) Long id) {

        if (id == null) {

            return new EmployeeForm();
        }

        return employeeFormFactory.create(id);
    }

    @GetMapping
    private String showEmployeeForm() {

        return V_EMPLOYEE_EDIT;
    }

    @PostMapping
    private String addOrUpdateEmployee(@ModelAttribute(M_EDIT_FORM) @Valid EmployeeForm employeeForm,
       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return V_EMPLOYEE_EDIT;
        }

        employeeService.saveOrUpdateEmployee(employeeForm);

        return "redirect:" + EMPLOYEE_LIST_URL;
    }

    @GetMapping("/delete")
    private String deleteEmployee(@RequestParam(P_EMPLOYEE_ID) Long id) {

        employeeService.deleteById(id);

        return "redirect:" + EMPLOYEE_LIST_URL;
    }
}