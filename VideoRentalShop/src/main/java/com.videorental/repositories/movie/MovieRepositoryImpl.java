package com.videorental.repositories.movie;

import com.videorental.entities.movie.Movie;
import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class MovieRepositoryImpl extends AbstractRepository<Movie> implements MovieRepository {


    @Autowired
    MovieRepositoryImpl(SessionFactory sessionFactory) {

        super(sessionFactory, Movie.class);
    }
}
