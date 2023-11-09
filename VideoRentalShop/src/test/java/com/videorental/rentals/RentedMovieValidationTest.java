package com.videorental.rentals;

import com.videorental.controllers.rentals.RentedMovieForm;
import com.videorental.controllers.rentals.RentedMovieValidator;
import com.videorental.entities.rentals.RentedMovie;
import com.videorental.services.rentals.RentedMovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentedMovieValidationTest {

    @Mock
    private RentedMovieService rentedMovieService;

    @InjectMocks
    private RentedMovieValidator rentedMovieValidator;
    private RentedMovieForm rentedMovieForm;
    private Errors errors;

    @BeforeEach
    public void setup() {

        rentedMovieForm = new RentedMovieForm();
        errors = new BeanPropertyBindingResult(rentedMovieForm, "RentedMovieForm");
    }

    @Test
    void shouldClientBeInvalid() throws Exception {

        //given
        rentedMovieForm.setMovieId(1L);
        rentedMovieForm.setRentDate("2023-06-01");

        //when
        rentedMovieValidator.validate(rentedMovieForm, errors);

        //then
        assertThat(errors.hasFieldErrors("clientId")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldDateBeInvalid() throws Exception {

        //given
        rentedMovieForm.setClientId(1L);
        rentedMovieForm.setMovieId(1L);
        rentedMovieForm.setRentDate("");

        //when
        rentedMovieValidator.validate(rentedMovieForm, errors);

        //then
        assertThat(errors.hasFieldErrors("rentDate")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldNotValidateMovie() throws Exception {

        //given
        List<RentedMovie> rentedMovies = new ArrayList<>();
        rentedMovies.add(new RentedMovie(1L, 1L, LocalDate.parse("2023-06-01")));

        rentedMovieForm.setClientId(2L);
        rentedMovieForm.setMovieId(1L);
        rentedMovieForm.setRentDate("2023-06-01");

        //when
        when(rentedMovieService.getAll()).thenReturn(rentedMovies);
        rentedMovieValidator.validate(rentedMovieForm, errors);

        //then
        assertThat(errors.hasFieldErrors("movieId")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldValidateRental() throws Exception {

        //given
        rentedMovieForm.setClientId(2L);
        rentedMovieForm.setMovieId(1L);
        rentedMovieForm.setRentDate("2023-06-01");

        //when
        rentedMovieValidator.validate(rentedMovieForm, errors);

        //then
        assertThat(errors.getFieldErrorCount()).isZero();
    }
}

