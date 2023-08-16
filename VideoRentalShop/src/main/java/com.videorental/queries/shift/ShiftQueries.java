package com.videorental.queries.shift;

import com.videorental.dtos.shift.ShiftDto;
import com.videorental.dtos.shift.ShiftSimpleDto;
import com.videorental.entities.shift.Shift;

import java.util.List;
import java.util.Optional;

public interface ShiftQueries {

    List<Shift> getAll();
    Shift getById(Long id);
    Optional<Shift> findById(Long id);
    List<ShiftDto> getAllShiftDto();
    List<ShiftSimpleDto> getShiftsByEmployeeId(Long employeeId);
}
