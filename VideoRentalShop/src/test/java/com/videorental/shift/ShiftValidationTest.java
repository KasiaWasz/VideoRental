package com.videorental.shift;

import com.videorental.controllers.shift.ShiftForm;
import com.videorental.controllers.shift.ShiftValidator;
import com.videorental.dtos.employee.EmployeeDetailDto;
import com.videorental.dtos.shift.ShiftDto;
import com.videorental.entities.employee.Employee;
import com.videorental.entities.employee.Role;
import com.videorental.entities.rentals.RentedMovie;
import com.videorental.services.employee.EmployeeService;
import com.videorental.services.shift.ShiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShiftValidationTest {

    @Mock
    private ShiftService shiftService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private ShiftValidator shiftValidator;
    private ShiftForm shiftForm;
    private Errors errors;

    @BeforeEach
    public void setup() {

        shiftForm = new ShiftForm();
        errors = new BeanPropertyBindingResult(shiftForm, "ShiftForm");
    }

    @Test
    void shouldCatchEmptyEmployee() throws Exception {

        //given
        List<ShiftDto> shifts = new ArrayList<>();
        shifts.add(new ShiftDto(1L, 1L, "Andrzej", "Andrzej", LocalDate.parse("2022-08-01"), 4L));
        shiftForm.setId(1L);
        shiftForm.setEmployeeId(null);
        shiftForm.setDate("2022-06-01");
        shiftForm.setHours(4L);

        //when
        when(shiftService.getAllShiftsDto()).thenReturn(shifts);
        shiftValidator.validate(shiftForm, errors);

        //then
        assertThat(errors.hasFieldErrors("employeeId")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(2);
    }

    @Test
    void shouldCatchIncorrectHours() throws Exception {

        //given
        EmployeeDetailDto employee = new EmployeeDetailDto(2L, "Katarzyna", "Edyt", LocalDate.parse("2023-06-01"), "123456789", BigDecimal.valueOf(30.00), Role.KASJER);
        List<ShiftDto> shifts = new ArrayList<>();
        shifts.add(new ShiftDto(1L, 1L, "Andrzej", "Andrzej", LocalDate.parse("2022-08-01"), 4L));
        shiftForm.setEmployeeId(2L);
        shiftForm.setDate("2023-06-01");
        shiftForm.setHours(10L);

        //when
        when(shiftService.getAllShiftsDto()).thenReturn(shifts);
        when(employeeService.getEmployeeDetailDtoById(shiftForm.getEmployeeId())).thenReturn(employee);
        shiftValidator.validate(shiftForm, errors);

        //then
        assertThat(errors.hasFieldErrors("hours")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchIncorrectShift() throws Exception {

        //given
        EmployeeDetailDto employee = new EmployeeDetailDto(2L, "Katarzyna", "Edyt", LocalDate.parse("2023-06-01"), "123456789", BigDecimal.valueOf(30.00), Role.KASJER);
        List<ShiftDto> shifts = new ArrayList<>();
        shifts.add(new ShiftDto(1L, 1L, "Andrzej", "Andrzej", LocalDate.parse("2022-08-01"), 4L));
        shiftForm.setEmployeeId(2L);
        shiftForm.setDate("2022-06-01");
        shiftForm.setHours(6L);

        //when
        when(shiftService.getAllShiftsDto()).thenReturn(shifts);
        when(employeeService.getEmployeeDetailDtoById(shiftForm.getEmployeeId())).thenReturn(employee);
        shiftValidator.validate(shiftForm, errors);

        //then
        assertThat(errors.hasFieldErrors("date")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldValidateShift() throws Exception {

        //given
        EmployeeDetailDto employee = new EmployeeDetailDto(2L, "Katarzyna", "Edyt", LocalDate.parse("2023-06-01"), "123456789", BigDecimal.valueOf(30.00), Role.KASJER);
        List<ShiftDto> shifts = new ArrayList<>();
        shifts.add(new ShiftDto(1L, 1L, "Andrzej", "Andrzej", LocalDate.parse("2022-08-01"), 4L));
        shiftForm.setEmployeeId(2L);
        shiftForm.setDate("2023-06-04");
        shiftForm.setHours(6L);

        //when
        when(shiftService.getAllShiftsDto()).thenReturn(shifts);
        when(employeeService.getEmployeeDetailDtoById(shiftForm.getEmployeeId())).thenReturn(employee);
        shiftValidator.validate(shiftForm, errors);

        //then
        assertThat(errors.getFieldErrorCount()).isZero();
    }
}
