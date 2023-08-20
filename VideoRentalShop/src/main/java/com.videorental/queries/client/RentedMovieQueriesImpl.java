package com.videorental.queries.client;

import com.videorental.dtos.client.RentedMovieDto;
import com.videorental.entities.client.RentedMovie;
import com.videorental.queries.AbstractQueries;
import com.videorental.queries.movie.MovieQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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


    @Override
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

    @Override
    public List<RentedMovieDto> getMoviesByClientId(Long clientId) {

        Assert.notNull(clientId, "clientId must not be null");

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RentedMovie> cq = cb.createQuery(RentedMovie.class);
            Root<RentedMovie> root = cq.from(RentedMovie.class);

            cq.select(root)
                    .where(cb.equal(root.get("clientId"), clientId));

            return session.createQuery(cq)
                    .getResultList().stream()
                    .map(rentedMovie -> new RentedMovieDto(
                        rentedMovie.getId(),
                        clientQueries.getClientDetailDtoById(rentedMovie.getClientId()).getFirstName(),
                        clientQueries.getClientDetailDtoById(rentedMovie.getClientId()).getLastName(),
                        movieQueries.getById(rentedMovie.getMovieId()).getName(),
                        rentedMovie.getRentDate()
                    ))
                    .toList();
        }
    }
}