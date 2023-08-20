package com.videorental.repositories.rentals;

import com.videorental.entities.rentals.RentedMovie;

public interface RentedMovieRepository {

    void saveOrUpdate(RentedMovie rentedMovie);

    void deleteById(Long id);
}
