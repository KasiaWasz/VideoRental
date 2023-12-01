package com.videorental.queries.history.archivemovie;

import com.videorental.dtos.history.archivemovie.ArchiveMovieDto;
import com.videorental.entities.history.archivemovie.ArchiveMovie;
import com.videorental.queries.AbstractQueries;
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
class ArchiveMovieQueriesImpl extends AbstractQueries<ArchiveMovie> implements ArchiveMovieQueries {


    @Autowired
    ArchiveMovieQueriesImpl(SessionFactory sessionFactory) {

        super(sessionFactory, ArchiveMovie.class);
    }


    @Override
    public ArchiveMovie getByMovieId(Long movieId) {

        Assert.notNull(movieId, "movieId must not be null");

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ArchiveMovie> cq = cb.createQuery(ArchiveMovie.class);
            Root<ArchiveMovie> root = cq.from(ArchiveMovie.class);

            cq.select(root)
                    .where(cb.equal(root.get("movieId"), movieId));

            return session.createQuery(cq).uniqueResult();
        }
    }

    @Override
    public List<ArchiveMovieDto> getAllArchiveMoviesDto() {

        return getAll().stream()
                .map(archiveMovie -> new ArchiveMovieDto(
                        archiveMovie.getId(),
                        archiveMovie.getMovieId(),
                        archiveMovie.getName(),
                        archiveMovie.getPrice()))
                .toList();
    }
}
