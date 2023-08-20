package com.videorental.repositories.client;

import com.videorental.entities.client.RentedMovie;

public interface RentedMovieRepository {

    void saveOrUpdate(RentedMovie rentedMovie);

    void deleteById(Long id);
}
