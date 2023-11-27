package com.videorental.services.rentals;

import com.videorental.controllers.rentals.RentedMovieForm;
import com.videorental.dtos.rentals.RentedMovieDto;
import com.videorental.dtos.rentals.RentedMovieSimpleDto;
import com.videorental.entities.rentals.RentedMovie;
import com.videorental.queries.rentals.RentedMovieQueries;
import com.videorental.repositories.rentals.RentedMovieRepository;
import com.videorental.services.rentals.history.RentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentedMovieService {

    private final RentedMovieRepository rentedMovieRepository;
    private final RentedMovieQueries rentedMovieQueries;
    private final RentalHistoryService rentalHistoryService;


    @Autowired
    private RentedMovieService(RentedMovieRepository rentedMovieRepository, RentedMovieQueries rentedMovieQueries, RentalHistoryService rentalHistoryService) {
        this.rentalHistoryService = rentalHistoryService;

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

    public List<RentedMovieSimpleDto> getAllRentedMoviesSimpleDto() {

        return rentedMovieQueries.getAllRentedMoviesSimpleDto();
    }

    public RentedMovie getById(Long id) {

        Assert.notNull(id, "is must not be null");

        return rentedMovieQueries.getById(id);
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

        rentalHistoryService.addNewRentalHistory(getById(id));

        rentedMovieRepository.deleteById(id);
    }
}
