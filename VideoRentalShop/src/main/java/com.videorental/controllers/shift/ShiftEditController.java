package com.videorental.controllers.shift;

import com.videorental.dtos.employee.EmployeeSimpleDto;
import com.videorental.services.employee.EmployeeService;
import com.videorental.services.shift.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/shift-edit")
@Controller
class ShiftEditController {

    private static final String M_EDIT_FORM = "shiftForm";
    private static final String M_EMPLOYEES_LIST = "employees";
    private static final String P_SHIFT_ID = "id";
    private static final String V_SHIFT_EDIT = "shift-edit-view";
    private static final String SHIFT_LIST_URL = "/shift-list";

    private final ShiftService shiftService;
    private final ShiftValidator shiftValidator;
    private final ShiftFormFactory shiftFormFactory;
    private final EmployeeService employeeService;


    @Autowired
    private ShiftEditController(ShiftService shiftService,
        ShiftValidator shiftValidator,
        ShiftFormFactory shiftFormFactory,
        EmployeeService employeeService) {

        Assert.notNull(shiftService, "shiftService must not be null");
        Assert.notNull(shiftValidator, "shiftValidator must not be null");
        Assert.notNull(shiftFormFactory, "shiftFormFactory must not be null");
        Assert.notNull(employeeService, "employeeService must not be null");

        this.shiftService = shiftService;
        this.shiftValidator = shiftValidator;
        this.shiftFormFactory = shiftFormFactory;
        this.employeeService = employeeService;
    }


    @InitBinder(M_EDIT_FORM)
    private void initBinder(WebDataBinder binder) {

        binder.addValidators(shiftValidator);
    }

    @ModelAttribute(M_EMPLOYEES_LIST)
    private List<EmployeeSimpleDto> getAllEmployeesSimpleDto() {

        return employeeService.getAllEmployeeSimpleDto();
    }

    @ModelAttribute(M_EDIT_FORM)
    private ShiftForm getShiftForm(@RequestParam(value= P_SHIFT_ID, required = false) Long id) {

        if (id == null) {

            return new ShiftForm();
        }

        return shiftFormFactory.create(id);
    }

    @GetMapping
    private String showShiftForm() {

        return V_SHIFT_EDIT;
    }

    @PostMapping
    private String addOrUpdateShift(@ModelAttribute(M_EDIT_FORM) @Valid ShiftForm shiftForm,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return V_SHIFT_EDIT;
        }

        shiftService.saveOrUpdateShift(shiftForm);

        return "redirect:" + SHIFT_LIST_URL;
    }

    @GetMapping("/delete")
    private String deleteShift(@RequestParam(P_SHIFT_ID) Long id) {

        shiftService.deleteById(id);

        return "redirect:" + SHIFT_LIST_URL;
    }
}
