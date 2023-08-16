package com.videorental.queries.shift;

import com.videorental.dtos.shift.ShiftSimpleDto;
import com.videorental.entities.shift.Shift;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ShiftSimpleDtoFactory {

    ShiftSimpleDto create(Shift shift) {

        Assert.notNull(shift, "shift must not be null");

        return new ShiftSimpleDto(
                shift.getId(),
                shift.getEmployeeId(),
                shift.getDate(),
                shift.getHours()
        );
    }
}
