package com.videorental.queries.history.rentalhistory;


import com.videorental.dtos.history.rentalhistory.RentalHistoryDto;
import com.videorental.entities.history.rentalhistory.RentalHistory;
import com.videorental.queries.AbstractQueries;
import com.videorental.queries.client.ClientQueries;
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
class RentalHistoryQueriesImpl extends AbstractQueries<RentalHistory> implements RentalHistoryQueries {

    private final ClientQueries clientQueries;
    private final MovieQueries movieQueries;


    @Autowired
    RentalHistoryQueriesImpl(SessionFactory sessionFactory, ClientQueries clientQueries, MovieQueries movieQueries) {

        super(sessionFactory, RentalHistory.class);

        Assert.notNull(clientQueries, "clientQueries must not be null");
        Assert.notNull(movieQueries, "movieQueries must not be null");

        this.clientQueries = clientQueries;
        this.movieQueries = movieQueries;
    }


    @Override
    public List<RentalHistory> getByMovieId(Long movieId) {

        Assert.notNull(movieId, "movieId must not be null");

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RentalHistory> cq = cb.createQuery(RentalHistory.class);
            Root<RentalHistory> root = cq.from(RentalHistory.class);

            cq.select(root)
                    .where(cb.equal(root.get("movieId"), movieId));

            return session.createQuery(cq).getResultList().stream().toList();
        }
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
