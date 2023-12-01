package com.videorental.controllers.rentals;

import com.videorental.dtos.rentals.RentalFeeDto;
import com.videorental.services.rentals.RentalFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/rental-fee-list")
@Controller
class RentalFeeListController {

    private static final String M_RENTAL_FEE_LIST = "rentalFee";
    private static final String V_RENTAL_FEE = "rental-fee-list-view";

    private final RentalFeeService rentalFeeService;


    @Autowired
    private RentalFeeListController(RentalFeeService rentalFeeService) {

        Assert.notNull(rentalFeeService, "rentalFeeService must not be null");

        this.rentalFeeService = rentalFeeService;
    }


    @ModelAttribute(M_RENTAL_FEE_LIST)
    private List<RentalFeeDto> rentalFee() {


        LocalDate currentDate = LocalDate.now();

        return rentalFeeService.getRentalFeeDto(currentDate);
    }

    @GetMapping
    private String showRentalFeeList() {

        return V_RENTAL_FEE;
    }
}