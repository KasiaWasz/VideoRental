package com.videorental.controllers.movie;


import com.videorental.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/movie-edit")
@Controller
class MovieEditController {

    private static final String M_EDIT_FORM = "movieForm";
    private static final String P_MOVIE_ID = "id";
    private static final String V_MOVIE_EDIT = "movie-edit-view";
    private static final String V_ERROR_RENTED = "error-rented-view";
    private static final String MOVIE_LIST_URL ="/movie-list";

    private final MovieService movieService;
    private final MovieFormFactory movieFormFactory;
    private final MovieValidator movieValidator;
    private final MovieIdValidator movieIdValidator;


    @Autowired
    private MovieEditController(MovieService movieService,
                                MovieFormFactory movieFormFactory,
                                MovieValidator movieValidator,
                                MovieIdValidator movieIdValidator) {

        Assert.notNull(movieService, "movieService must not be null");
        Assert.notNull(movieFormFactory, "movieFormFactory must not be null");
        Assert.notNull(movieValidator, "movieValidator must not be null");

        this.movieService = movieService;
        this.movieFormFactory = movieFormFactory;
        this.movieValidator = movieValidator;
        this.movieIdValidator = movieIdValidator;
    }


    @InitBinder(M_EDIT_FORM)
    private void initBinder(WebDataBinder binder) {

        binder.addValidators(movieValidator);
    }

    @ModelAttribute(M_EDIT_FORM)
    private MovieForm getMovieForm(@RequestParam(value = P_MOVIE_ID, required = false) Long id) {

        if (id == null) {

            return new MovieForm();
        }

        return movieFormFactory.create(id);
    }

    @GetMapping
    private String showMovieForm() {

        return V_MOVIE_EDIT;
    }

    @PostMapping
    private String addOrUpdateMovie(@ModelAttribute(M_EDIT_FORM) @Valid MovieForm movieForm,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return V_MOVIE_EDIT;
        }

        movieService.saveOrUpdateMovie(movieForm);

        return "redirect:" + MOVIE_LIST_URL;
    }

    @GetMapping("/deactivate")
    private String deactivateMovie(@RequestParam(P_MOVIE_ID) Long id) {


       if (movieIdValidator.isMovieRented(id)) {

           return V_ERROR_RENTED;
      }

        movieService.setMovieActive(id);

        return "redirect:" + MOVIE_LIST_URL;
    }
}