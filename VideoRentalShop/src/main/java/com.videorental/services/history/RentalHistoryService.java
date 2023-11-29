package com.videorental.services.history;

import com.videorental.dtos.history.RentalHistoryDto;
import com.videorental.entities.history.rentalhistory.RentalHistory;
import com.videorental.entities.rentals.RentedMovie;
import com.videorental.queries.history.rentalhistory.RentalHistoryQueries;
import com.videorental.repositories.history.rentalhistory.RentalHistoryRepository;
import com.videorental.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalHistoryService {

    private final RentalHistoryRepository rentalHistoryRepository;
    private final RentalHistoryQueries rentalHistoryQueries;
    private final MovieService movieService;


    @Autowired
    public RentalHistoryService(RentalHistoryRepository rentalHistoryRepository, RentalHistoryQueries rentalHistoryQueries, MovieService movieService) {
        this.movieService = movieService;
        //this.movieService = movieService;

        Assert.notNull(rentalHistoryRepository, "rentalHistoryRepository must not be null");
        Assert.notNull(rentalHistoryQueries, "rentalHistoryQueries must not be null");

        this.rentalHistoryRepository = rentalHistoryRepository;
        this.rentalHistoryQueries = rentalHistoryQueries;
    }


    public List<RentalHistoryDto> getAllRentalHistoryDto() {

        return rentalHistoryQueries.getAllRentalHistoryDto();
    }

    public void addNewRentalHistory(RentedMovie rentedMovie) {

        LocalDate currentDate = LocalDate.now();
        BigDecimal moviePrice = movieService.getById(rentedMovie.getMovieId()).getPrice();
        BigDecimal daysRented = BigDecimal.valueOf(ChronoUnit.DAYS.between(rentedMovie.getRentDate(), currentDate));

        RentalHistory newRentalHistory = new RentalHistory(
                rentedMovie.getClientId(),
                rentedMovie.getMovieId(),
                rentedMovie.getRentDate(),
                currentDate,
                daysRented,
                moviePrice.multiply(daysRented)
        );

        rentalHistoryRepository.saveOrUpdate(newRentalHistory);
    }
}
