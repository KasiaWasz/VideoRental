package com.videorental.queries.rentals.history;


import com.videorental.dtos.rentals.history.RentalHistoryDto;
import com.videorental.entities.rentals.history.RentalHistory;
import com.videorental.queries.AbstractQueries;
import com.videorental.queries.client.ClientQueries;
import com.videorental.queries.movie.MovieQueries;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
class RentalHistoryQueriesImpl extends AbstractQueries<RentalHistory> implements RentalHistoryQueries {

    private final ClientQueries clientQueries;
    private final MovieQueries movieQueries;

    RentalHistoryQueriesImpl(SessionFactory sessionFactory, ClientQueries clientQueries, MovieQueries movieQueries) {

        super(sessionFactory, RentalHistory.class);

        Assert.notNull(clientQueries, "clientQueries must not be null");
        Assert.notNull(movieQueries, "movieQueries must not be null");

        this.clientQueries = clientQueries;
        this.movieQueries = movieQueries;
    }

    @Override
    public List<RentalHistoryDto> getAllRentalHistoryDto() {

        return getAll().stream()
                .map(rentalHistory -> new RentalHistoryDto(
                        rentalHistory.getId(),
                        clientQueries.getClientDetailDtoById(rentalHistory.getClientId()).getFirstName(),
                        clientQueries.getClientDetailDtoById(rentalHistory.getClientId()).getLastName(),
                        movieQueries.getById(rentalHistory.getMovieId()).getName(),
                        rentalHistory.getStartRentDate(),
                        rentalHistory.getEndRentDate(),
                        rentalHistory.getNumberOfDaysRented(),
                        rentalHistory.getTotalPrice()
                ))
                .toList();
    }
}
