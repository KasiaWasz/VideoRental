package com.videorental.services.shift;

import com.videorental.controllers.shift.ShiftForm;
import com.videorental.dtos.shift.ShiftDto;
import com.videorental.dtos.shift.ShiftSimpleDto;
import com.videorental.entities.shift.Shift;
import com.videorental.queries.shift.ShiftQueries;
import com.videorental.repositories.shift.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShiftService {

    private final ShiftQueries shiftQueries;
    private final ShiftRepository shiftRepository;


    @Autowired
    private ShiftService(ShiftQueries shiftQueries, ShiftRepository shiftRepository) {

        Assert.notNull(shiftQueries, "shiftQueries must not be null");
        Assert.notNull(shiftRepository, "shiftRepository must not be null");

        this.shiftQueries = shiftQueries;
        this.shiftRepository = shiftRepository;
    }


    public List<ShiftDto> getAllShiftsDto() {

        return shiftQueries.getAllShiftDto();
    }

    public List<ShiftSimpleDto> getShiftsByEmployeeId(Long employeeId) {

        Assert.notNull(employeeId, "employeeId must not be null");

        return shiftQueries.getShiftsByEmployeeId(employeeId);
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        shiftRepository.deleteById(id);
    }

    public Shift getById(Long id) {

        Assert.notNull(id, "id must not be null");

        return shiftQueries.getById(id);
    }

    public void saveOrUpdateShift(ShiftForm shiftForm) {

        Assert.notNull(shiftForm, "shiftForm must not be null");

        Shift shift = shiftQueries.findById(shiftForm.getId())
                .orElseGet(Shift::new);

        shift.setEmployeeId(shiftForm.getEmployeeId());
        shift.setDate(LocalDate.parse(shiftForm.getDate()));
        shift.setHours(shiftForm.getHours());

        shiftRepository.saveOrUpdate(shift);
    }
}
