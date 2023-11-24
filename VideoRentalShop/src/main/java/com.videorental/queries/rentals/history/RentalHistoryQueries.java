package com.videorental.queries.rentals.history;

import com.videorental.dtos.rentals.history.RentalHistoryDto;
import com.videorental.entities.rentals.history.RentalHistory;

import java.util.List;
import java.util.Optional;

public interface RentalHistoryQueries {

    RentalHistory getById(Long id);

    List<RentalHistory> getAll();

    Optional<RentalHistory> findById(Long id);

    List<RentalHistoryDto> getAllRentalHistoryDto();
}
