package com.videorental.controllers.shift;

import com.videorental.entities.shift.Shift;
import com.videorental.services.shift.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.format.DateTimeFormatter;

@Component
class ShiftFormFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ShiftService shiftService;


    @Autowired
    private ShiftFormFactory(ShiftService shiftService) {

        Assert.notNull(shiftService, "shiftService must not be null");

        this.shiftService = shiftService;
    }


    ShiftForm create(Long id) {

        Assert.notNull(id, "id must not be null");

        Shift shift = shiftService.getById(id);

        return new ShiftForm(
                shift.getId(),
                shift.getEmployeeId(),
                shift.getDate().format(DATE_TIME_FORMATTER),
                shift.getHours()
        );
    }
}
