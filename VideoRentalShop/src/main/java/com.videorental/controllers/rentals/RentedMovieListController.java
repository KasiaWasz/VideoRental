package com.videorental.controllers.rentals;

import com.videorental.dtos.client.ClientDto;
import com.videorental.dtos.movie.MovieDto;
import com.videorental.dtos.rentals.RentedMovieDto;
import com.videorental.services.client.ClientService;
import com.videorental.services.movie.MovieService;
import com.videorental.services.rentals.RentedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/rental-list")
@Controller
class RentedMovieListController {

    private static final String M_RENTAL_LIST = "rentals";
    private static final String V_RENTED_MOVIE_LIST = "rental-list-view";

    private final RentedMovieService rentedMovieService;

    @Autowired
    private RentedMovieListController(RentedMovieService rentedMovieService) {

        Assert.notNull(rentedMovieService, "rentedMovieService must not be null");

        this.rentedMovieService = rentedMovieService;
    }

    @ModelAttribute(M_RENTAL_LIST)
    private List<RentedMovieDto> getAllRentedMoviesDto() {

        return rentedMovieService.getAllRentedMoviesDto();
    }


    @GetMapping
    private String showRentedMovieList() {

        return V_RENTED_MOVIE_LIST;
    }
}
