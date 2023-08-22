package com.videorental.queries.rentals;

import com.videorental.dtos.rentals.RentedMovieDto;
import com.videorental.dtos.rentals.RentedMovieSimpleDto;
import com.videorental.entities.rentals.RentedMovie;
import com.videorental.queries.AbstractQueries;
import com.videorental.queries.client.ClientQueries;
import com.videorental.queries.movie.MovieQueries;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
class RentedMovieQueriesImpl extends AbstractQueries<RentedMovie> implements RentedMovieQueries {

    private final ClientQueries clientQueries;
    private final MovieQueries movieQueries;


    @Autowired
    RentedMovieQueriesImpl(SessionFactory sessionFactory, ClientQueries clientQueries, MovieQueries movieQueries) {

        super(sessionFactory, RentedMovie.class);

        Assert.notNull(clientQueries, "clientQueries must not be null");
        Assert.notNull(movieQueries, "movieQueries must not be null");

        this.clientQueries = clientQueries;
        this.movieQueries = movieQueries;
    }


    public List<RentedMovieDto> getAllRentedMoviesDto() {

        return getAll().stream()
                .map(rentedMovie -> new RentedMovieDto(
                        rentedMovie.getId(),
                        clientQueries.getClientDetailDtoById(rentedMovie.getClientId()).getFirstName(),
                        clientQueries.getClientDetailDtoById(rentedMovie.getClientId()).getLastName(),
                        movieQueries.getById(rentedMovie.getMovieId()).getName(),
                        rentedMovie.getRentDate()
                ))
                .toList();
    }


    public List<RentedMovieSimpleDto> getAllRentedMoviesSimpleDto() {

        return getAll().stream()
                .map(rentedMovie -> new RentedMovieSimpleDto(
                        rentedMovie.getId(),
                        rentedMovie.getClientId(),
                        rentedMovie.getMovieId(),
                        rentedMovie.getRentDate()
                ))
                .toList();
    }
}