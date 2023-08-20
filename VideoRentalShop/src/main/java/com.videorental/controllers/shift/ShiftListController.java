package com.videorental.controllers.shift;

import com.videorental.dtos.shift.ShiftDto;
import com.videorental.services.shift.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/shift-list")
@Controller
class ShiftListController {

    private static final String V_SHIFTS_LIST = "shifts-list-view";
    private static final String M_SHIFTS_LIST = "shifts";
    private final ShiftService shiftService;


    @Autowired
    private ShiftListController(ShiftService shiftService) {

        Assert.notNull(shiftService, "shiftService must not be null");

        this.shiftService = shiftService;
    }

    @ModelAttribute(M_SHIFTS_LIST)
    private List<ShiftDto> getAllShifts() {

        return shiftService.getAllShiftsDto();
    }

    @GetMapping
    private String showShiftList() {

        return V_SHIFTS_LIST;
    }
}
