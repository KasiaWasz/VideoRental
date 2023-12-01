package com.videorental.queries.history.rentalhistory;

import com.videorental.dtos.history.rentalhistory.RentalHistoryDto;
import com.videorental.entities.history.rentalhistory.RentalHistory;

import java.util.List;
import java.util.Optional;

public interface RentalHistoryQueries {

    RentalHistory getById(Long id);

    List<RentalHistory> getByMovieId(Long movieId);

    List<RentalHistory> getAll();

    Optional<RentalHistory> findById(Long id);

    List<RentalHistoryDto> getAllRentalHistoryDto();
}
