package com.videorental.controllers.history.rentalhistory;

import com.videorental.dtos.history.rentalhistory.RentalHistoryDto;
import com.videorental.services.history.rentalhistory.RentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/rental-history-list")
@Controller
class RentalHistoryListController {

    private static final String M_RENTAL_HISTORY_LIST = "rentalHistory";
    private static final String V_RENTAL_HISTORY_LIST = "rental-history-list-view";

    private final RentalHistoryService rentalHistoryService;


    @Autowired
    private RentalHistoryListController(RentalHistoryService rentalHistoryService) {

        Assert.notNull(rentalHistoryService, "rentalHistoryService must not be null");

        this.rentalHistoryService = rentalHistoryService;
    }


    @ModelAttribute(M_RENTAL_HISTORY_LIST)
    private List<RentalHistoryDto> getRentalHistory() {

        return rentalHistoryService.getAllRentalHistoryDto();
    }

    @GetMapping
    private String showRentalHistoryList() {

        return V_RENTAL_HISTORY_LIST;
    }
}
