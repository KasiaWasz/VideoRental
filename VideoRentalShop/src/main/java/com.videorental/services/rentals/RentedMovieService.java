package com.videorental.services.rentals;

import com.videorental.controllers.rentals.RentedMovieForm;
import com.videorental.dtos.rentals.RentedMovieDto;
import com.videorental.entities.rentals.RentedMovie;
import com.videorental.queries.rentals.RentedMovieQueries;
import com.videorental.repositories.rentals.RentedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentedMovieService {

    private final RentedMovieRepository rentedMovieRepository;
    private final RentedMovieQueries rentedMovieQueries;


    @Autowired
    private RentedMovieService(RentedMovieRepository rentedMovieRepository, RentedMovieQueries rentedMovieQueries) {

        Assert.notNull(rentedMovieRepository, "rentedMovieRepository must not be null");
        Assert.notNull(rentedMovieQueries, "rentedMovieQueries must not be null");

        this.rentedMovieRepository = rentedMovieRepository;
        this.rentedMovieQueries = rentedMovieQueries;
    }


    public List<RentedMovieDto> getAllRentedMoviesDto() {

        return rentedMovieQueries.getAllRentedMoviesDto();
    }

    public List<RentedMovie> getAll() {

        return rentedMovieQueries.getAll();
    }

    public void addNewRentedMovie(RentedMovieForm rentedMovieForm) {

        RentedMovie newRentedMovie = new RentedMovie(
            rentedMovieForm.getClientId(),
            rentedMovieForm.getMovieId(),
            LocalDate.parse(rentedMovieForm.getRentDate()));

        rentedMovieRepository.saveOrUpdate(newRentedMovie);
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        rentedMovieRepository.deleteById(id);
    }
}
