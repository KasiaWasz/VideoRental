package com.videorental.queries.movie;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.movie.Movie;
import com.videorental.queries.AbstractQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Random;

@Service
class MovieQueriesImpl extends AbstractQueries<Movie> implements MovieQueries  {

    private static final Random random = new Random();


    @Autowired
   MovieQueriesImpl(SessionFactory sessionFactory) {

        super(sessionFactory, Movie.class);
   }


    public List<MovieDto> getAllMoviesDto() {

       return getAll().stream()
               .map(movie -> new MovieDto(
                       movie.getId(),
                       movie.getName(),
                       movie.getPrice()
               ))
               .toList();
    }

    public List<MovieDto> getAllActiveMoviesDto() {

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
            Root<Movie> root = cq.from(Movie.class);

            cq.select(root)
                    .where(cb.isTrue(root.get("isMovieActive")));

             return session.createQuery(cq)
                    .getResultList().stream()
                    .map(movie -> new MovieDto(
                            movie.getId(),
                            movie.getName(),
                            movie.getPrice()))
                    .toList();
            }
        }

    public Movie getMovieOnSale() {

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
            Root<Movie> root = cq.from(Movie.class);

            cq.select(root)
                    .where(cb.isTrue(root.get("isOnSale")));

            Movie movie = session.createQuery(cq).uniqueResult();

            if (movie != null) {

                return movie;
            } else {

                return null;
            }
        }
    }

    public Long getRandomMovieId() {

       int movieSize = getAllActiveMoviesDto().size();
       return random.nextLong(1, movieSize - 1);
    }
}
