package com.videorental.movie;

import com.videorental.controllers.movie.MovieForm;
import com.videorental.controllers.movie.MovieValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;

class MovieValidationTest {

    private MovieValidator movieValidator;
    private MovieForm movieForm;
    private Errors errors;

    @BeforeEach
    public void setup() {

        movieValidator = new MovieValidator();
        movieForm = new MovieForm();
        errors = new BeanPropertyBindingResult(movieForm, "MovieForm");
    }

    @Test
    void shouldCatchEmptyName() throws Exception {

        //given
        movieForm.setName("");
        movieForm.setPrice(BigDecimal.valueOf(12.00));

        //when
        movieValidator.validate(movieForm, errors);

        //then
        assertThat(errors.hasFieldErrors("name")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldCatchInvalidPrice() throws Exception {

        //given
        movieForm.setName("To");
        movieForm.setPrice(BigDecimal.valueOf(-12.00));

        //when
        movieValidator.validate(movieForm, errors);

        //then
        assertThat(errors.hasFieldErrors("price")).isTrue();
        assertThat(errors.getFieldErrorCount()).isEqualTo(1);
    }

    @Test
    void shouldValidateMovie() throws Exception {

        //given
        movieForm.setName("To");
        movieForm.setPrice(BigDecimal.valueOf(12.00));

        //when
        movieValidator.validate(movieForm, errors);

        //then
        assertThat(errors.getFieldErrorCount()).isZero();
    }
}
