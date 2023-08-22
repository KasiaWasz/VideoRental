package com.videorental.controllers.rentals;

import com.videorental.dtos.client.ClientDto;
import com.videorental.dtos.movie.MovieDto;
import com.videorental.services.client.ClientService;
import com.videorental.services.movie.MovieService;
import com.videorental.services.rentals.RentedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/rental-edit")
@Controller
class RentedMovieEditController {

    private static final String M_EDIT_FORM = "rentedMovieForm";
    private static final String V_RENTED_MOVIE_EDIT = "rented-movie-edit-view";
    private static final String P_RENTAL_ID = "id";
    private static final String RENTAL_LIST_URL ="/rental-list";
    private static final String M_CLIENT_LIST = "clients";
    private static final String M_MOVIE_LIST = "movies";

    private final RentedMovieService rentedMovieService;
    private final ClientService clientService;
    private final MovieService movieService;
    private final RentedMovieValidator rentedMovieValidator;


    @Autowired
    private RentedMovieEditController(RentedMovieValidator rentedMovieValidator,
        ClientService clientService,
        MovieService movieService,
        RentedMovieService rentedMovieService) {

        Assert.notNull(rentedMovieValidator, "rentedMovieValidator must not be null");
        Assert.notNull(clientService, "clientService must not be null");
        Assert.notNull(movieService, "movieService must not be null");
        Assert.notNull(rentedMovieService, "rentedMovieService must not be null");

        this.rentedMovieValidator = rentedMovieValidator;
        this.clientService = clientService;
        this.movieService = movieService;
        this.rentedMovieService = rentedMovieService;
    }


    @InitBinder(M_EDIT_FORM)
    private void initBinder(WebDataBinder binder) {

        binder.addValidators(rentedMovieValidator);
    }

    @ModelAttribute(M_CLIENT_LIST)
    private List<ClientDto> getClients() {

        return clientService.getAllClientsDto();
    }

    @ModelAttribute(M_MOVIE_LIST)
    private List<MovieDto> getMovies() {

        return movieService.getAllMoviesDto();
    }

    @ModelAttribute(M_EDIT_FORM)
    private RentedMovieForm getRentedMovieForm() {

        return new RentedMovieForm();
    }

    @GetMapping
    private String showRentedMovieForm() {

        return V_RENTED_MOVIE_EDIT;
    }

    @PostMapping
    private String addRentedMovie(@ModelAttribute(M_EDIT_FORM) @Valid RentedMovieForm rentedMovieForm,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return V_RENTED_MOVIE_EDIT;
        }

        rentedMovieService.addNewRentedMovie(rentedMovieForm);

        return "redirect:" + RENTAL_LIST_URL;
    }

    @GetMapping("/delete")
    private String deleteEmployee(@RequestParam(P_RENTAL_ID) Long id) {

        rentedMovieService.deleteById(id);

        return "redirect:" + RENTAL_LIST_URL;
    }
}
