package com.videorental.controllers.rentals;

import com.videorental.entities.rentals.RentedMovie;
import com.videorental.services.rentals.RentedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class RentedMovieValidator implements Validator {

    private static final String E_FIELD_REQUIRED = "field.required";
    private static final String E_RENT_DATE_INVALID = "date.invalid";
    private static final String E_MOVIE_INVALID = "movie.invalid";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final RentedMovieService rentedMovieService;


    @Autowired
    public RentedMovieValidator(RentedMovieService rentedMovieService) {

        Assert.notNull(rentedMovieService, "rentedMovieService must not be null");

        this.rentedMovieService = rentedMovieService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return RentedMovieForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RentedMovieForm rentedMovieForm = (RentedMovieForm) target;

        validateClient(rentedMovieForm.getClientId(), errors);
        validateMovie(rentedMovieForm.getMovieId(), errors);
        validateRentDate(rentedMovieForm.getRentDate(), errors);
    }

    private void validateClient(Long clientId, Errors errors) {

        if (clientId == null) {

            errors.rejectValue(RentedMovieForm.F_CLIENT_ID, E_FIELD_REQUIRED);
        }
    }

    private void validateMovie(Long movieId, Errors errors) {

        List<RentedMovie> rentals = rentedMovieService.getAll();

        if (movieId == null) {

            errors.rejectValue(RentedMovieForm.F_MOVIE_ID, E_FIELD_REQUIRED);
        }
        boolean isRentalNotValid = isRentalIncorrect(movieId, rentals);

        if (isRentalNotValid) {

            errors.rejectValue(RentedMovieForm.F_MOVIE_ID, E_MOVIE_INVALID);
        }
    }

    private boolean isRentalIncorrect(Long movieId, List<RentedMovie> rentals) {

        return rentals.stream()
                .anyMatch(rental ->
                        rental.getMovieId().equals(movieId));
    }

    private void validateRentDate(String rentDate, Errors errors) {

        if (isBlank(rentDate)) {

            errors.rejectValue(RentedMovieForm.F_RENT_DATE, E_FIELD_REQUIRED);

            return;

        }
        try {

            LocalDate.parse(rentDate, DATE_TIME_FORMATTER);

        } catch (Exception e) {

            errors.rejectValue(RentedMovieForm.F_RENT_DATE, E_RENT_DATE_INVALID);
        }
    }
}
