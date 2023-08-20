package com.videorental.repositories.rentals;

import com.videorental.entities.rentals.RentedMovie;
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
