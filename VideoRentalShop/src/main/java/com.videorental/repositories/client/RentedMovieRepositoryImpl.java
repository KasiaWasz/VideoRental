package com.videorental.repositories.client;

import com.videorental.entities.client.RentedMovie;
import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class RentedMovieRepositoryImpl extends AbstractRepository<RentedMovie> implements RentedMovieRepository {


    @Autowired
    RentedMovieRepositoryImpl(SessionFactory sessionFactory) {

        super(sessionFactory, RentedMovie.class);
    }
}
