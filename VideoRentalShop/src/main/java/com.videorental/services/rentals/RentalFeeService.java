package com.videorental.services.rentals;

import com.videorental.dtos.rentals.RentalFeeDto;
import com.videorental.dtos.rentals.RentedMovieSimpleDto;
import com.videorental.entities.client.Client;
import com.videorental.services.client.ClientService;
import com.videorental.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalFeeService {

    private final ClientService clientService;
    private final MovieService movieService;
    private final RentedMovieService rentedMovieService;


    @Autowired
    private RentalFeeService(ClientService clientService,
        MovieService movieService,
        RentedMovieService rentedMovieService) {

        Assert.notNull(clientService, "clientService must not be null");
        Assert.notNull(movieService, "movieService must not be null");
        Assert.notNull(rentedMovieService, "rentedMovieService must not be null");

        this.clientService = clientService;
        this.movieService = movieService;
        this.rentedMovieService = rentedMovieService;
    }


    public List<RentalFeeDto> getRentalFeeDto(LocalDate currentDate) {

        List<Client> clients = clientService.getAll();
        List<RentedMovieSimpleDto> rentedMovies = rentedMovieService.getAllRentedMoviesSimpleDto();

        return clients.stream()
                .map(client -> calculateRentalFee(client, rentedMovies, currentDate))
                .collect(Collectors.toList());
    }

    private RentalFeeDto calculateRentalFee(Client client, List<RentedMovieSimpleDto> rentedMovies, LocalDate currentDate) {

        Long clientId = client.getId();
        List<RentedMovieSimpleDto> rentals = rentedMovies.stream()
                .filter(rentedMovie -> rentedMovie.getClientId().equals(clientId))
                .toList();

        BigDecimal totalFee = calculateTotalFee(rentals, currentDate);

        return new RentalFeeDto(
                client.getFirstName(),
                client.getLastName(),
                totalFee
        );
    }

    private BigDecimal calculateTotalFee(List<RentedMovieSimpleDto> rentals, LocalDate currentDate) {

        return rentals.stream()
                .map(rentedMovie -> {
                    LocalDate movieRentDate = rentedMovie.getRentDate();
                    int daysRented = (int) ChronoUnit.DAYS.between(movieRentDate, currentDate);

                    BigDecimal moviePrice = movieService.getById(rentedMovie.getMovieId()).getPrice();
                    return moviePrice.multiply(BigDecimal.valueOf(daysRented));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}