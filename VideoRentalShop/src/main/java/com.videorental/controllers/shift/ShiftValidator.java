package com.videorental.controllers.shift;

import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.dtos.shift.ShiftDto;
import com.videorental.services.employee.EmployeeService;
import com.videorental.services.shift.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
class ShiftValidator implements Validator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String E_FIELD_REQUIRED = "field.required";
    private static final String E_EMPLOYEE_ID_INVALID = "employeeId.invalid";
    private static final String E_DATE_INVALID = "date.invalid";
    private static final String E_SHIFT_DATE_INVALID = "employee.shift.date.invalid";
    private static final String E_HOURS_INVALID = "hours.invalid";
    private final ShiftService shiftService;
    private final EmployeeService employeeService;


    @Autowired
    private ShiftValidator(ShiftService shiftService, EmployeeService employeeService) {

        Assert.notNull(shiftService, "shiftService must not be null");
        Assert.notNull(employeeService, "employeeService must not be null");

        this.shiftService = shiftService;
        this.employeeService = employeeService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return ShiftForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ShiftForm shiftForm = (ShiftForm) target;

        validateEmployee(shiftForm.getEmployeeId(), errors);
        validateHours(shiftForm.getHours(), errors);
        validateShift(shiftForm.getId(), shiftForm.getEmployeeId(), shiftForm.getDate(), errors);
    }

    private void validateShift(Long id, Long employeeId, String date, Errors errors) {

        EmployeeDetailDto employee = employeeService.getEmployeeDetailDtoById(employeeId);
        List<ShiftDto> shifts = shiftService.getAllShiftsDto();

        if (isBlank(date)) {

            return;

        }
        try {

            LocalDate targetDate = LocalDate.parse(date, DATE_TIME_FORMATTER);

            boolean isShiftNotValid = isShiftIncorrect(id, employeeId, targetDate, shifts);

            if (isShiftNotValid) {

                errors.rejectValue(ShiftForm.F_EMPLOYEE_ID, E_EMPLOYEE_ID_INVALID);
            }
            else if (employee.getJoinDate().isAfter(targetDate)) {

                errors.rejectValue(ShiftForm.F_DATE, E_SHIFT_DATE_INVALID);
            }

        } catch (Exception e) {

            errors.rejectValue(ShiftForm.F_DATE, E_DATE_INVALID);
        }
    }

    private boolean isShiftIncorrect(Long id, Long employeeId, LocalDate targetDate, List<ShiftDto> shifts) {

        return shifts.stream()
                .anyMatch(shift ->
                                shift.getDate().isEqual(targetDate)
                                && shift.getEmployeeId().equals(employeeId)
                                && !Objects.equals(shift.getId(), id));
    }

    private void validateHours(Long hours, Errors errors) {

        if (hours == null) {

            errors.rejectValue(ShiftForm.F_HOURS, E_FIELD_REQUIRED);

        } else if (hours <= 0) {

            errors.rejectValue(ShiftForm.F_HOURS, E_HOURS_INVALID);

        } else if (hours > 8) {

            errors.rejectValue(ShiftForm.F_HOURS, E_HOURS_INVALID);
        }
    }

    private void validateEmployee(Long employeeId, Errors errors) {

        if (employeeId == null) {

            errors.rejectValue(ShiftForm.F_EMPLOYEE_ID, E_EMPLOYEE_ID_INVALID);
        }
    }
}
